package com.mines.CurvaABCZ.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.CurvaABCZ.Model.TipoCurvaAbcz;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TipoCurvaAbczRepositoryImplGCF implements TipoCurvaAbczRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public TipoCurvaAbcz obterPorIdTipoCurvaAbcz(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<TipoCurvaAbcz>() {
            @Override
            public TipoCurvaAbcz mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                TipoCurvaAbcz tipoCurvaAbcz = new TipoCurvaAbcz();

                tipoCurvaAbcz.setId(rs.getInt("id"));
                tipoCurvaAbcz.setDescricao(rs.getString("descricao"));
                tipoCurvaAbcz.setTipoCurva(rs.getString("tipocurva"));

                Integer entidadeEstoqueId = rs.getInt("entidadeestoque");
                Empresa entidadeEstoque = empresaRepo.obterPorIdEmpresa(entidadeEstoqueId);
                tipoCurvaAbcz.setEntidadeEstoque(entidadeEstoque);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                tipoCurvaAbcz.setEntidadeGestora(entidadeGestora);

                return tipoCurvaAbcz;
            }
        });

    }
    
    public List<TipoCurvaAbcz> obterTodosTiposCurvaAbcz(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<TipoCurvaAbcz>() {
            @Override
            public TipoCurvaAbcz mapRow(ResultSet rs, int rownumber) throws SQLException {

                TipoCurvaAbcz tipoCurvaAbcz = new TipoCurvaAbcz();

                tipoCurvaAbcz.setId(rs.getInt("id"));
                tipoCurvaAbcz.setDescricao(rs.getString("descricao"));
                tipoCurvaAbcz.setTipoCurva(rs.getString("tipocurva"));

                Integer entidadeEstoqueId = rs.getInt("entidadeestoque");
                Empresa entidadeEstoque = empresaRepo.obterPorIdEmpresa(entidadeEstoqueId);
                tipoCurvaAbcz.setEntidadeEstoque(entidadeEstoque);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                tipoCurvaAbcz.setEntidadeGestora(entidadeGestora);

                return tipoCurvaAbcz;
            }
        }, entidade);

    }
    
}


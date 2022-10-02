package com.mines.Empresa.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Model.TipoFilial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TipoFilialRepositoryImplGCF implements TipoFilialRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public TipoFilial obterPorIdTipoFilial(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<TipoFilial>() {
            @Override
            public TipoFilial mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                TipoFilial tipoFilial = new TipoFilial();

                tipoFilial.setId(rs.getInt("id"));
                tipoFilial.setDescricao(rs.getString("descricao"));
                tipoFilial.setVendeProduto(rs.getBoolean("vendeproduto"));

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);

                tipoFilial.setEntidadeGestora(entidadeGestora);

                return tipoFilial;
            }
        });

    }
    
    public List<TipoFilial> obterTodosTipoFiliais(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<TipoFilial>() {
            @Override
            public TipoFilial mapRow(ResultSet rs, int rownumber) throws SQLException {

                TipoFilial tipoFilial = new TipoFilial();

                tipoFilial.setId(rs.getInt("id"));
                tipoFilial.setDescricao(rs.getString("descricao"));
                tipoFilial.setVendeProduto(rs.getBoolean("vendeproduto"));

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);

                tipoFilial.setEntidadeGestora(entidadeGestora);

                return tipoFilial;
            }
        }, entidade);

    }
    
}
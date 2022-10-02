package com.mines.CurvaABCZ.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.CurvaABCZ.Model.CurvaMargemContribuicao;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CurvaMargemContribuicaoRepositoryImplGCF implements CurvaMargemContribuicaoRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public CurvaMargemContribuicao obterPorIdCurvaMargemContribuicao(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<CurvaMargemContribuicao>() {
            @Override
            public CurvaMargemContribuicao mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                CurvaMargemContribuicao curvaMargemContribuicao = new CurvaMargemContribuicao();

                curvaMargemContribuicao.setId(rs.getInt("id"));
                curvaMargemContribuicao.setMargem(rs.getDouble("margem"));
                curvaMargemContribuicao.setFrequencia(rs.getDouble("frequencia"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                curvaMargemContribuicao.setEntidadeGestora(entidadeGestora);
                
                return curvaMargemContribuicao;
            }
        });

    }

    public List<CurvaMargemContribuicao> obterTodosCurvasMargemContribuicao(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<CurvaMargemContribuicao>() {
            @Override
            public CurvaMargemContribuicao mapRow(ResultSet rs, int rownumber) throws SQLException {

                CurvaMargemContribuicao curvaMargemContribuicao = new CurvaMargemContribuicao();

                curvaMargemContribuicao.setId(rs.getInt("id"));
                curvaMargemContribuicao.setMargem(rs.getDouble("margem"));
                curvaMargemContribuicao.setFrequencia(rs.getDouble("frequencia"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                curvaMargemContribuicao.setEntidadeGestora(entidadeGestora);
                
                return curvaMargemContribuicao;
            }
        }, entidade);

    }
   
}
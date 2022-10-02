package com.mines.CondicaoPagto.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.CondicaoPagto.Model.PrazoParcela;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PrazoParcelaRepositoryImplWinthor implements PrazoParcelaRepositoryWinthor {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public PrazoParcela obterPorIdPrazoParcela(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<PrazoParcela>() {
            @Override
            public PrazoParcela mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                PrazoParcela prazoParcela = new PrazoParcela();

                prazoParcela.setId(rs.getInt("id"));
                prazoParcela.setTitulo(rs.getString("titulo"));
                prazoParcela.setDias(rs.getInt("dias"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                prazoParcela.setEntidadeGestora(entidadeGestora);

                return prazoParcela;
            }
        });

    }
    
    public List<PrazoParcela> obterTodosPrazoParcelas(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<PrazoParcela>() {
            @Override
            public PrazoParcela mapRow(ResultSet rs, int rownumber) throws SQLException {

                PrazoParcela prazoParcela = new PrazoParcela();

                prazoParcela.setId(rs.getInt("id"));
                prazoParcela.setTitulo(rs.getString("titulo"));
                prazoParcela.setDias(rs.getInt("dias"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                prazoParcela.setEntidadeGestora(entidadeGestora);

                return prazoParcela;
            }
        }, entidade);

    }
    
}


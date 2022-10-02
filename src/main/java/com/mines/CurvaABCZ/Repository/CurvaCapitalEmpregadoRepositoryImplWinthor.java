package com.mines.CurvaABCZ.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.CurvaABCZ.Model.CurvaCapitalEmpregado;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CurvaCapitalEmpregadoRepositoryImplWinthor implements CurvaCapitalEmpregadoRepositoryWinthor {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public CurvaCapitalEmpregado obterPorIdCurvaCapitalEmpregado(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<CurvaCapitalEmpregado>() {
            @Override
            public CurvaCapitalEmpregado mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                CurvaCapitalEmpregado curvaCapitalEmpregado = new CurvaCapitalEmpregado();

                curvaCapitalEmpregado.setId(rs.getInt("id"));
                curvaCapitalEmpregado.setValorCapital(rs.getDouble("valorcapital"));
                curvaCapitalEmpregado.setFrequencia(rs.getDouble("frequencia"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                curvaCapitalEmpregado.setEntidadeGestora(entidadeGestora);
                
                return curvaCapitalEmpregado;
            }
        });
    } 
    
    public List<CurvaCapitalEmpregado> obterTodosCurvasCapitalEmpregado(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<CurvaCapitalEmpregado>() {
            @Override
            public CurvaCapitalEmpregado mapRow(ResultSet rs, int rownumber) throws SQLException {

                CurvaCapitalEmpregado curvaCapitalEmpregado = new CurvaCapitalEmpregado();

                curvaCapitalEmpregado.setId(rs.getInt("id"));
                curvaCapitalEmpregado.setValorCapital(rs.getDouble("valorcapital"));
                curvaCapitalEmpregado.setFrequencia(rs.getDouble("frequencia"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                curvaCapitalEmpregado.setEntidadeGestora(entidadeGestora);
                
                return curvaCapitalEmpregado;
            }
        }, entidade);

    }
    
}


package com.mines.CurvaABCZ.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.CurvaABCZ.Model.CurvaGiroVendaDia;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CurvaGiroVendaDiaRepositoryImplGCF implements CurvaGiroVendaDiaRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public CurvaGiroVendaDia obterPorIdCurvaGiroVendaDia(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<CurvaGiroVendaDia>() {
            @Override
            public CurvaGiroVendaDia mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                CurvaGiroVendaDia curvaGiroVendaDia = new CurvaGiroVendaDia();

                curvaGiroVendaDia.setId(rs.getInt("id"));
                curvaGiroVendaDia.setValorGiroDia(rs.getDouble("valorgirodia"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                curvaGiroVendaDia.setEntidadeGestora(entidadeGestora);
                
                return curvaGiroVendaDia;
            }
        });

    }


    
    public List<CurvaGiroVendaDia> obterTodosCurvasGiroVendaDia(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<CurvaGiroVendaDia>() {
            @Override
            public CurvaGiroVendaDia mapRow(ResultSet rs, int rownumber) throws SQLException {

                CurvaGiroVendaDia curvaGiroVendaDia = new CurvaGiroVendaDia();

                curvaGiroVendaDia.setId(rs.getInt("id"));
                curvaGiroVendaDia.setValorGiroDia(rs.getDouble("valorgirodia"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                curvaGiroVendaDia.setEntidadeGestora(entidadeGestora);
                
                return curvaGiroVendaDia;
            }
        }, entidade);

    }

    
}

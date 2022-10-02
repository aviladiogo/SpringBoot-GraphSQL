package com.mines.CurvaABCZ.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.CurvaABCZ.Model.CurvaGiroVendaDia;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CurvaGiroVendaDiaRepositoryImpl implements CurvaGiroVendaDiaRepository {

    private static String SELECT_ALL = " select * from dx_curvagirovendadia where entidadegestora = ?";
    private static String SELECT_ONE = " select * from dx_curvagirovendadia where id = ? ";
    private static String INSERT = " insert into dx_curvagirovendadia (id, valorgirodia, entidadegestora) "
            + " values (nextval('dx_curvagirovendadia_id_seq'),?,?) ";
    private static String UPDATE = " update dx_curvagirovendadia set valorgirodia = ? "
            + " where id = ? ";
    private static String DELETE = " delete from dx_curvagirovendadia where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public CurvaGiroVendaDia atualizarCurvaGiroVendaDia(CurvaGiroVendaDia curvaGiroVendaDia) {
       
        jdbcTemplate.update(UPDATE, new Object[] {curvaGiroVendaDia.getValorGiroDia(), 
            curvaGiroVendaDia.getId()});

        return curvaGiroVendaDia;

    }

    
    public Boolean deletarCurvaGiroVendaDia(CurvaGiroVendaDia curvaGiroVendaDia) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {curvaGiroVendaDia.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
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
    
    public CurvaGiroVendaDia salvarCurvaGiroVendaDia(CurvaGiroVendaDia curvaGiroVendaDia) {

        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setDouble(1, curvaGiroVendaDia.getValorGiroDia());
                ps.setInt(2, curvaGiroVendaDia.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        curvaGiroVendaDia.setId(id);
            
        return curvaGiroVendaDia;

    }

    
}

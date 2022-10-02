package com.mines.CurvaABCZ.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.CurvaABCZ.Model.CurvaCapitalEmpregado;
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
public class CurvaCapitalEmpregadoRepositoryImpl implements CurvaCapitalEmpregadoRepository {

    private static String SELECT_ALL = " select * from dx_curvacapitalempregado where entidadegestora = ?";
    private static String SELECT_ONE = " select * from dx_curvacapitalempregado where id = ? ";
    private static String INSERT = " insert into dx_curvacapitalempregado (id, valorcapital, frequencia, "
            + " entidadegestora) values (nextval('dx_curvacapitalempregado_id_seq'),?,?,?) ";
    private static String UPDATE = " update dx_curvacapitalempregado set valorcapital = ?, frequencia = ? "
            + " where id = ? ";
    private static String DELETE = " delete from dx_curvacapitalempregado where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public CurvaCapitalEmpregado atualizarCurvaCapitalEmpregado(CurvaCapitalEmpregado curvaCapitalEmpregado) {
       
        jdbcTemplate.update(UPDATE, new Object[] {curvaCapitalEmpregado.getValorCapital(), 
            curvaCapitalEmpregado.getFrequencia(), curvaCapitalEmpregado.getId()});

        return curvaCapitalEmpregado;

    }

    
    public Boolean deletarCurvaCapitalEmpregado(CurvaCapitalEmpregado curvaCapitalEmpregado) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {curvaCapitalEmpregado.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
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

    public CurvaCapitalEmpregado salvarCurvaCapitalEmpregado(CurvaCapitalEmpregado curvaCapitalEmpregado) {

        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setDouble(1, curvaCapitalEmpregado.getValorCapital());
                ps.setDouble(2, curvaCapitalEmpregado.getFrequencia());
                ps.setInt(3, curvaCapitalEmpregado.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        curvaCapitalEmpregado.setId(id);
            
        return curvaCapitalEmpregado;

    }

    
}


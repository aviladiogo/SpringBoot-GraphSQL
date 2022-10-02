package com.mines.CondicaoPagto.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.CondicaoPagto.Model.PrazoParcela;
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
public class PrazoParcelaRepositoryImpl implements PrazoParcelaRepository {

    private static String SELECT_ALL = " select * from dx_prazoparcela where entidadegestora = ?";
    private static String SELECT_ONE = " select * from dx_prazoparcela where id = ? ";
    private static String INSERT = " insert into dx_prazoparcela (id, titulo, dias, entidadegestora) "
            + " values (nextval('dx_prazoparcela_id_seq'),?,?,?) ";
    private static String UPDATE = " update dx_prazoparcela set titulo = ?, dias = ? where id = ? ";
    private static String DELETE = " delete from dx_prazoparcela where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public PrazoParcela atualizarPrazoParcela(PrazoParcela prazoParcela) {
       
        jdbcTemplate.update(UPDATE, new Object[] {prazoParcela.getTitulo(),prazoParcela.getDias(), 
        prazoParcela.getId()});

        return prazoParcela;

    }

    
    public Boolean deletarPrazoParcela(PrazoParcela prazoParcela) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {prazoParcela.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
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
    
    public PrazoParcela salvarPrazoParcela(PrazoParcela prazoParcela) {

        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, prazoParcela.getTitulo());
                ps.setInt(2, prazoParcela.getDias());
                ps.setInt(3, prazoParcela.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        prazoParcela.setId(id);
            
        return prazoParcela;

    }
    
}


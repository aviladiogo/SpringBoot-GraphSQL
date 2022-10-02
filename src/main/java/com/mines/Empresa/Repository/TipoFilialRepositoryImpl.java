package com.mines.Empresa.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Model.TipoFilial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class TipoFilialRepositoryImpl implements TipoFilialRepository {

    private static String SELECT_ALL = " select * from dx_tipofilial where entidadegestora = ? "
            + " order by descricao ";
    private static String SELECT_ONE = " select * from dx_tipofilial where id = ? ";
    private static String INSERT = " insert into dx_tipofilial (id, descricao, vendeProduto, entidadegestora)"
            + " values (nextval('dx_tipofilial_id_seq'),?,?,?) ";
    private static String UPDATE = " update dx_tipofilial set descricao = ?, vendeProduto = ?"
            + "where id = ? ";
    private static String DELETE = " delete from dx_tipofilial where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public TipoFilial atualizarTipoFilial(TipoFilial tipoFilial) {
       
        jdbcTemplate.update(UPDATE, new Object[] {tipoFilial.getDescricao(), tipoFilial.getVendeProduto(), 
            tipoFilial.getId()});

        return tipoFilial;

    }

    public Boolean deletarTipoFilial(TipoFilial tipoFilial) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {tipoFilial.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
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
    
    public TipoFilial salvarTipoFilial(TipoFilial tipoFilial) {
        
        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, tipoFilial.getDescricao());
                ps.setBoolean(2, tipoFilial.getVendeProduto());
                ps.setInt(3, tipoFilial.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        tipoFilial.setId(id);
            
        return tipoFilial;

    }
    
}
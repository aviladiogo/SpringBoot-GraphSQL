package com.mines.Produto.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Produto.Model.Marca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class MarcaRepositoryImpl implements MarcaRepository {

    private static String SELECT_ALL = " select * from dx_marca where entidadegestora = ? "
            + " order by descricao ";
    private static String SELECT_ONE = " select * from dx_marca where id = ? ";
    private static String INSERT = " insert into dx_marca (id, descricao, entidadegestora)"
            + " values (nextval('dx_marca_id_seq'),?,?) ";
    private static String UPDATE = " update dx_marca set descricao = ? "
            + "where id = ? ";
    private static String DELETE = " delete from dx_marca where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Marca atualizarMarca(Marca marca) {
       
        jdbcTemplate.update(UPDATE, new Object[] {marca.getDescricao(), marca.getId()});

        return marca;

    }

    public Boolean deletarMarca(Marca marca) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {marca.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    public Marca obterPorIdMarca(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Marca>() {
            @Override
            public Marca mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Marca marca = new Marca();

                marca.setId(rs.getInt("id"));
                marca.setDescricao(rs.getString("descricao"));
                    
                return marca;
            }
        });

    }
    
    public List<Marca> obterTodosMarcas(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Marca>() {
            @Override
            public Marca mapRow(ResultSet rs, int rownumber) throws SQLException {

                Marca marca = new Marca();

                marca.setId(rs.getInt("id"));
                marca.setDescricao(rs.getString("descricao"));
                    
                return marca;
            }
        }, entidade);

    }

    public Marca salvarMarca(Marca marca) {
        
        KeyHolder holder = new GeneratedKeyHolder();
        
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, marca.getDescricao());
                    ps.setInt(2, marca.getEntidadeGestora().getId());
                    return ps;
                }
            }, holder);
    
            Integer id = (Integer) holder.getKeys().get("id");
            marca.setId(id);
                
            return marca;

    }
    
}


package com.mines.Produto.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Produto.Model.Marca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MarcaRepositoryImplGCF implements MarcaRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
    
}


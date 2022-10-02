package com.mines.CurvaABCZ.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.CurvaABCZ.Model.CurvaAbcz;
import com.mines.CurvaABCZ.Model.ItemClasseProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ItemClasseProdutoRepositoryImplWinthor implements ItemClasseProdutoRepositoryWinthor {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CurvaAbczRepository curvaAbczRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public List<ItemClasseProduto> obterPorIdItemClasseProduto(Integer id) {
        
        return jdbcTemplate.query(SELECT_ONE, new RowMapper<ItemClasseProduto>() {
            @Override
            public ItemClasseProduto mapRow(ResultSet rs, int rownumber) throws SQLException {

                ItemClasseProduto itemClasseProduto = new ItemClasseProduto();

                itemClasseProduto.setClasseProduto(rs.getInt("classeproduto"));

                Integer curvaAbczId = rs.getInt("curvaabcz");
                CurvaAbcz curvaAbcz = curvaAbczRepo.obterPorIdCurvaABCZ(curvaAbczId);
                itemClasseProduto.setCurvaAbcz(curvaAbcz);

                return itemClasseProduto;
            }
        }, id);

    }
    
    public List<ItemClasseProduto> obterTodosItemClasseProduto() {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<ItemClasseProduto>() {
            @Override
            public ItemClasseProduto mapRow(ResultSet rs, int rownumber) throws SQLException {

                ItemClasseProduto itemClasseProduto = new ItemClasseProduto();

                itemClasseProduto.setClasseProduto(rs.getInt("classeproduto"));

                Integer curvaAbczId = rs.getInt("curvaabcz");
                CurvaAbcz curvaAbcz = curvaAbczRepo.obterPorIdCurvaABCZ(curvaAbczId);
                itemClasseProduto.setCurvaAbcz(curvaAbcz);

                return itemClasseProduto;
            }
        });

    }
    
}



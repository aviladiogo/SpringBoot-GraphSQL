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
public class ItemClasseProdutoRepositoryImpl implements ItemClasseProdutoRepository {

    private static String SELECT_ALL = " select * from dx_itemclasseproduto ";
    private static String SELECT_ONE = " select * from dx_itemclasseproduto where classeproduto = ? ";
    private static String INSERT = " insert into dx_itemclasseproduto (classeproduto, curvaabcz) values (?,?) ";
    private static String DELETE = " delete from dx_itemclasseproduto where classeproduto = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CurvaAbczRepository curvaAbczRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    
    public Boolean deletarItemClasseProduto(Integer classeProdutoId) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {classeProdutoId});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
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

    public ItemClasseProduto salvarItemClasseProduto(ItemClasseProduto itemClasseProduto) {
        

        jdbcTemplate.update(INSERT, new Object[] {itemClasseProduto.getClasseProduto(), 
            itemClasseProduto.getCurvaAbcz().getId()});

        return itemClasseProduto;

    }

    
}



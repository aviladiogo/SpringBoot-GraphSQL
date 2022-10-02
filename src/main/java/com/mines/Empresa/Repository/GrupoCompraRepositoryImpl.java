package com.mines.Empresa.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Model.GrupoCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class GrupoCompraRepositoryImpl implements GrupoCompraRepository {

    private static String SELECT_ALL = " select * from dx_grupocompra where entidadegestora = ? "
            + " order by descricao ";
    private static String SELECT_ONE = " select * from dx_grupocompra where id = ? ";
    private static String INSERT = " insert into dx_grupocompra (id, descricao, externo, entidadegestora)"
            + " values (nextval('dx_grupocompra_id_seq'),?,?,?) ";
    private static String UPDATE = " update dx_grupocompra set descricao = ?, externo = ?"
            + "where id = ? ";
    private static String DELETE = " delete from dx_grupocompra where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public GrupoCompra atualizarGrupoCompra(GrupoCompra grupoCompra) {
       
        jdbcTemplate.update(UPDATE, new Object[] {grupoCompra.getDescricao(), grupoCompra.getExterno(), 
            grupoCompra.getId()});

        return grupoCompra;

    }

    public Boolean deletarGrupoCompra(GrupoCompra grupoCompra) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {grupoCompra.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    
    public GrupoCompra obterPorIdGrupoCompra(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<GrupoCompra>() {
            @Override
            public GrupoCompra mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                GrupoCompra grupoCompra = new GrupoCompra();

                grupoCompra.setId(rs.getInt("id"));
                grupoCompra.setDescricao(rs.getString("descricao"));
                grupoCompra.setExterno(rs.getInt("externo"));

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);

                grupoCompra.setEntidadeGestora(entidadeGestora);

                return grupoCompra;
            }
        });

    }
    
    public List<GrupoCompra> obterTodosGrupoCompras(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<GrupoCompra>() {
            @Override
            public GrupoCompra mapRow(ResultSet rs, int rownumber) throws SQLException {

                GrupoCompra grupoCompra = new GrupoCompra();

                grupoCompra.setId(rs.getInt("id"));
                grupoCompra.setDescricao(rs.getString("descricao"));
                grupoCompra.setExterno(rs.getInt("externo"));

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                grupoCompra.setEntidadeGestora(entidadeGestora);

                return grupoCompra;
            }
        }, entidade);

    }

    public GrupoCompra salvarGrupoCompra(GrupoCompra grupoCompra) {
        
        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, grupoCompra.getDescricao());
                ps.setInt(2, grupoCompra.getExterno());
                ps.setInt(3, grupoCompra.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        grupoCompra.setId(id);
            
        return grupoCompra;

    }
    
}

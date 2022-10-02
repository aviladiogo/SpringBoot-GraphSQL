package com.mines.Empresa.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Model.GrupoCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class GrupoCompraRepositoryImplGCF implements GrupoCompraRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
    
}

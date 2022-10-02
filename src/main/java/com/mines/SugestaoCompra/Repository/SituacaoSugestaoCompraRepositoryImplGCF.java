package com.mines.SugestaoCompra.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.SugestaoCompra.Model.SituacaoSugestaoCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SituacaoSugestaoCompraRepositoryImplGCF implements SituacaoSugestaoCompraRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public SituacaoSugestaoCompra obterPorIdSituacaoSugestaoCompra(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<SituacaoSugestaoCompra>() {
            @Override
            public SituacaoSugestaoCompra mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                SituacaoSugestaoCompra situacaoSugestaoCompra = new SituacaoSugestaoCompra();

                situacaoSugestaoCompra.setId(rs.getInt("id"));
                situacaoSugestaoCompra.setDescricao(rs.getString("descricao"));
                situacaoSugestaoCompra.setPermiteEditar(rs.getInt("permiteeditar"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                situacaoSugestaoCompra.setEntidadeGestora(entidadeGestora);

                return situacaoSugestaoCompra;
            }
        });

    }
    
    public List<SituacaoSugestaoCompra> obterTodosSituacoesSugestaoCompras(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<SituacaoSugestaoCompra>() {
            @Override
            public SituacaoSugestaoCompra mapRow(ResultSet rs, int rownumber) throws SQLException {

                SituacaoSugestaoCompra situacaoSugestaoCompra = new SituacaoSugestaoCompra();

                situacaoSugestaoCompra.setId(rs.getInt("id"));
                situacaoSugestaoCompra.setDescricao(rs.getString("descricao"));
                situacaoSugestaoCompra.setPermiteEditar(rs.getInt("permiteeditar"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                situacaoSugestaoCompra.setEntidadeGestora(entidadeGestora);

                return situacaoSugestaoCompra;
            }
        }, entidade);

    }
    
}


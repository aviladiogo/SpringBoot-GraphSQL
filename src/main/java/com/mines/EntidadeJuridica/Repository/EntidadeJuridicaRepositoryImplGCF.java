package com.mines.EntidadeJuridica.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.EntidadeJuridica.Model.EntidadeJuridica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EntidadeJuridicaRepositoryImplGCF implements EntidadeJuridicaRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public EntidadeJuridica obterPorIdEntidadeJuridica(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<EntidadeJuridica>() {
            @Override
            public EntidadeJuridica mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                EntidadeJuridica entidadeJuridica = new EntidadeJuridica();

                entidadeJuridica.setId(rs.getInt("id"));
                entidadeJuridica.setDescricao(rs.getString("descricao"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                entidadeJuridica.setEntidadeGestora(entidadeGestora);

                return entidadeJuridica;
            }
        });

    }
    
    public List<EntidadeJuridica> obterTodosEntidadesJuridicas(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<EntidadeJuridica>() {
            @Override
            public EntidadeJuridica mapRow(ResultSet rs, int rownumber) throws SQLException {

                EntidadeJuridica entidadeJuridica = new EntidadeJuridica();

                entidadeJuridica.setId(rs.getInt("id"));
                entidadeJuridica.setDescricao(rs.getString("descricao"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                entidadeJuridica.setEntidadeGestora(entidadeGestora);

                return entidadeJuridica;
            }
        }, entidade);

    }

}


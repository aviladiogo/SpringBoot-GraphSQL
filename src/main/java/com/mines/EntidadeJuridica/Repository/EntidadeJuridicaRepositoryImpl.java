package com.mines.EntidadeJuridica.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.EntidadeJuridica.Model.EntidadeJuridica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class EntidadeJuridicaRepositoryImpl implements EntidadeJuridicaRepository {

    private static String SELECT_ALL = " select * from dx_entidadejuridica where entidadegestora = ? "
            + " order by descricao";
    private static String SELECT_ONE = " select * from dx_entidadejuridica where id = ? ";
    private static String INSERT = " insert into dx_entidadejuridica (id, descricao, entidadegestora) "
            + " values (nextval('dx_entidadejuridica_id_seq'),?,?) ";
    private static String UPDATE = " update dx_entidadejuridica set descricao = ? where id = ? ";
    private static String DELETE = " delete from dx_entidadejuridica where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public EntidadeJuridica atualizarEntidadeJuridica(EntidadeJuridica entidadeJuridica) {
       
        jdbcTemplate.update(UPDATE, new Object[] {entidadeJuridica.getDescricao(), entidadeJuridica.getId()});

        return entidadeJuridica;

    }

    
    public Boolean deletarEntidadeJuridica(EntidadeJuridica entidadeJuridica) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {entidadeJuridica.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
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

    public EntidadeJuridica salvarEntidadeJuridica(EntidadeJuridica entidadeJuridica) {
        
        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, entidadeJuridica.getDescricao());
                ps.setInt(2, entidadeJuridica.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        entidadeJuridica.setId(id);
            
        return entidadeJuridica;

    }
    
}


package com.mines.Produto.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.Produto.Model.Unidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UnidadeRepositoryImpl implements UnidadeRepository {

    private static String SELECT_ALL = " select * from dx_unidade where entidadegestora = ? "
            + " order by descricao";
    private static String SELECT_ONE = " select * from dx_unidade where id = ? ";
    private static String INSERT = " insert into dx_unidade (id, descricao, entidadegestora)"
            + " values (nextval('dx_unidade_id_seq'),?,?) ";
    private static String UPDATE = " update dx_unidade set descricao = ? "
            + "where id = ? ";
    private static String DELETE = " delete from dx_unidade where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Unidade atualizarUnidade(Unidade unidade) {
       
        jdbcTemplate.update(UPDATE, new Object[] {unidade.getDescricao(), unidade.getId()});

        return unidade;

    }

    public Boolean deletarUnidade(Unidade unidade) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {unidade.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    public Unidade obterPorIdUnidade(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Unidade>() {
            @Override
            public Unidade mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Unidade unidade = new Unidade();

                unidade.setId(rs.getInt("id"));
                unidade.setDescricao(rs.getString("descricao"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                unidade.setEntidadeGestora(entidadeGestora);
                    
                return unidade;
            }
        });

    }
    
    public List<Unidade> obterTodosUnidades(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Unidade>() {
            @Override
            public Unidade mapRow(ResultSet rs, int rownumber) throws SQLException {

                Unidade unidade = new Unidade();

                unidade.setId(rs.getInt("id"));
                unidade.setDescricao(rs.getString("descricao"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                unidade.setEntidadeGestora(entidadeGestora);
                    
                return unidade;
            }
        }, entidade);

    }

    public Unidade salvarUnidade(Unidade unidade) {

        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, unidade.getDescricao());
                ps.setInt(2, unidade.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        unidade.setId(id);
            
        return unidade;

    }
    
}

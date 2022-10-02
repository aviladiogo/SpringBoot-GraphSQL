package com.mines.Colaborador.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Colaborador.Model.Funcao;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class FuncaoRepositoryImpl implements FuncaoRepository {

    private static String SELECT_ALL = " select * from dx_funcao where entidadegestora = ?";
    private static String SELECT_ONE = " select * from dx_funcao where id = ? ";
    private static String INSERT = " insert into dx_funcao (id, titulo, entidadegestora) "
            + " values (nextval('dx_funcao_id_seq'),?,?) ";
    private static String UPDATE = " update dx_funcao set titulo = ? "
            + " where id = ? ";
    private static String DELETE = " delete from dx_funcao where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Funcao atualizarFuncao(Funcao funcao) {
       
        jdbcTemplate.update(UPDATE, new Object[] {funcao.getTitulo(), funcao.getId()});

        return funcao;

    }

    
    public Boolean deletarFuncao(Funcao funcao) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {funcao.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    
    public Funcao obterPorIdFuncao(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Funcao>() {
            @Override
            public Funcao mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Funcao funcao = new Funcao();

                funcao.setId(rs.getInt("id"));
                funcao.setTitulo(rs.getString("titulo"));

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                funcao.setEntidadeGestora(entidadeGestora);

                return funcao;
            }
        });

    }

    public List<Funcao> obterTodosFuncoes(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Funcao>() {
            @Override
            public Funcao mapRow(ResultSet rs, int rownumber) throws SQLException {

                Funcao funcao = new Funcao();

                funcao.setId(rs.getInt("id"));
                funcao.setTitulo(rs.getString("titulo"));

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                funcao.setEntidadeGestora(entidadeGestora);

                return funcao;
            }
        }, entidade);

    }

    public Funcao salvarFuncao(Funcao funcao) {

        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, funcao.getTitulo());
                ps.setInt(2, funcao.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        funcao.setId(id);

        return funcao;

    }

    
}



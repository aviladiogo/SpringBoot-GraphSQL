package com.mines.ClassificacaoMercadologica.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import com.mines.ClassificacaoMercadologica.Model.Departamento;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Model.PessoaJuridica;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;
import com.mines.EntidadeJuridica.Repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class DepartamentoRepositoryImpl implements DepartamentoRepository {

    private static String SELECT_ALL = " select * from dx_departamento where entidade = ? order by descricao";
    private static String SELECT_ONE = " select * from dx_departamento where id = ? ";
    private static String INSERT = " insert into dx_departamento (id, descricao, registro, usuario, "
            + " entidade) "
            + " values (nextval('dx_departamento_id_seq'),?,?,?,?) ";
    private static String UPDATE = " update dx_departamento set descricao = ? "
            + " where id = ? ";
    private static String DELETE = " delete from dx_departamento where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Departamento atualizarDepartamento(Departamento departamento) {
       
        jdbcTemplate.update(UPDATE, new Object[] {departamento.getDescricao(), departamento.getId()});

        return departamento;

    }

    
    public Boolean deletarDepartamento(Departamento departamento) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {departamento.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    
    public Departamento obterPorIdDepartamento(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Departamento>() {
            @Override
            public Departamento mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Departamento departamento = new Departamento();

                departamento.setId(rs.getInt("id"));
                departamento.setDescricao(rs.getString("descricao"));
                departamento.setRegistro(rs.getDate("registro"));

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                departamento.setUsuario(usuario);

                Integer entidadeId = rs.getInt("entidade");
                PessoaJuridica entidade = pessoaJuridicaRepo.obterPorIdPessoaJuridica(entidadeId);
                departamento.setEntidade(entidade);

                return departamento;
            }
        });

    }
    
    public List<Departamento> obterTodosDepartamentos(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Departamento>() {
            @Override
            public Departamento mapRow(ResultSet rs, int rownumber) throws SQLException {

                Departamento departamento = new Departamento();

                departamento.setId(rs.getInt("id"));
                departamento.setDescricao(rs.getString("descricao"));
                departamento.setRegistro(rs.getDate("registro"));

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                departamento.setUsuario(usuario);

                Integer entidadeId = rs.getInt("entidade");
                PessoaJuridica entidade = pessoaJuridicaRepo.obterPorIdPessoaJuridica(entidadeId);
                departamento.setEntidade(entidade);

                return departamento;
            }
        }, entidade);

    }

    public Departamento salvarDepartamento(Departamento departamento) {

        KeyHolder holder = new GeneratedKeyHolder();
        
        Date registro = new Date();
        java.sql.Date registroSQL = new java.sql.Date(registro.getTime());

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, departamento.getDescricao());
                ps.setDate(2, registroSQL);
                ps.setInt(3, departamento.getUsuario().getId());
                ps.setInt(4, departamento.getEntidade().getId());

                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        departamento.setId(id);
        
        return departamento;

    }

    
}


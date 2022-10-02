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
import com.mines.ClassificacaoMercadologica.Model.Secao;
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
public class SecaoRepositoryImpl implements SecaoRepository {

    private static String SELECT_ALL = " select * from dx_secao where entidade = ? order by descricao";
    private static String SELECT_ALL_SECTION = " select * from dx_secao where entidade = ? " 
            + " and departamento=? order by descricao";
    private static String SELECT_ONE = " select * from dx_secao where id = ? ";
    private static String INSERT = " insert into dx_secao (id, descricao, departamento, registro, usuario, "
            + " entidade) "
            + " values (nextval('dx_secao_id_seq'),?,?,?,?,?) ";
    private static String UPDATE = " update dx_secao set descricao = ? "
            + " where id = ? ";
    private static String DELETE = " delete from dx_secao where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepo;

    @Autowired
    private DepartamentoRepository departamentoRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Secao atualizarSecao(Secao secao) {
       
        jdbcTemplate.update(UPDATE, new Object[] {secao.getDescricao(), secao.getId()});

        return secao;

    }

    
    public Boolean deletarSecao(Secao secao) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {secao.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    
    public Secao obterPorIdSecao(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Secao>() {
            @Override
            public Secao mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Secao secao = new Secao();

                secao.setId(rs.getInt("id"));
                secao.setDescricao(rs.getString("descricao"));
                secao.setRegistro(rs.getDate("registro"));

                Integer departamentoId = rs.getInt("departamento");
                Departamento departamento = departamentoRepo.obterPorIdDepartamento(departamentoId);
                secao.setDepartamento(departamento);

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                secao.setUsuario(usuario);

                Integer entidadeId = rs.getInt("entidade");
                PessoaJuridica entidade = pessoaJuridicaRepo.obterPorIdPessoaJuridica(entidadeId);
                secao.setEntidade(entidade);

                return secao;
            }
        });

    }

    public List<Secao> obterTodosSecoes(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Secao>() {
            @Override
            public Secao mapRow(ResultSet rs, int rownumber) throws SQLException {

                Secao secao = new Secao();

                secao.setId(rs.getInt("id"));
                secao.setDescricao(rs.getString("descricao"));
                secao.setRegistro(rs.getDate("registro"));

                Integer departamentoId = rs.getInt("departamento");
                Departamento departamento = departamentoRepo.obterPorIdDepartamento(departamentoId);
                secao.setDepartamento(departamento);

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                secao.setUsuario(usuario);

                Integer entidadeId = rs.getInt("entidade");
                PessoaJuridica entidade = pessoaJuridicaRepo.obterPorIdPessoaJuridica(entidadeId);
                secao.setEntidade(entidade);

                return secao;
            }
        }, entidade);

    }
    
    public List<Secao> obterSecoesPorDepartamento(Integer entidade, Integer departamento) {
        
        return jdbcTemplate.query(SELECT_ALL_SECTION, new RowMapper<Secao>() {
            @Override
            public Secao mapRow(ResultSet rs, int rownumber) throws SQLException {

                Secao secao = new Secao();

                secao.setId(rs.getInt("id"));
                secao.setDescricao(rs.getString("descricao"));
                secao.setRegistro(rs.getDate("registro"));

                Integer departamentoId = rs.getInt("departamento");
                Departamento departamento = departamentoRepo.obterPorIdDepartamento(departamentoId);
                secao.setDepartamento(departamento);

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                secao.setUsuario(usuario);

                Integer entidadeId = rs.getInt("entidade");
                PessoaJuridica entidade = pessoaJuridicaRepo.obterPorIdPessoaJuridica(entidadeId);
                secao.setEntidade(entidade);

                return secao;
            }
        }, entidade, departamento);

    }

    public Secao salvarSecao(Secao secao) {
        
        KeyHolder holder = new GeneratedKeyHolder();
        
        Date registro = new Date();
        java.sql.Date registroSQL = new java.sql.Date(registro.getTime());

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, secao.getDescricao());
                ps.setInt(2, secao.getDepartamento().getId());
                ps.setDate(3, registroSQL);
                ps.setInt(4, secao.getUsuario().getId());
                ps.setInt(5, secao.getEntidade().getId());

                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        secao.setId(id);
        
        return secao;

    }

    
}


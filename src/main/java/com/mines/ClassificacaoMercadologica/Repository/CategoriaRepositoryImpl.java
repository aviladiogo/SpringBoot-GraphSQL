package com.mines.ClassificacaoMercadologica.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import com.mines.ClassificacaoMercadologica.Model.Categoria;
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
public class CategoriaRepositoryImpl implements CategoriaRepository {

    private static String SELECT_ALL = " select * from dx_categoria where entidade = ? order by descricao";
    private static String SELECT_ALL_CATEGORY = " select * from dx_categoria where entidade = ? and secao=? "
            + "order by descricao ";
    private static String SELECT_ONE = " select * from dx_categoria where id = ? ";
    private static String INSERT = " insert into dx_categoria (id, descricao, secao, registro, usuario, "
            + " entidade) "
            + " values (nextval('dx_categoria_id_seq'),?,?,?,?,?) ";
    private static String UPDATE = " update dx_categoria set descricao = ? "
            + " where id = ? ";
    private static String DELETE = " delete from dx_categoria where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepo;

    @Autowired
    private SecaoRepository secaoRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Categoria atualizarCategoria(Categoria categoria) {
       
        jdbcTemplate.update(UPDATE, new Object[] {categoria.getDescricao(), categoria.getId()});

        return categoria;

    }

    
    public Boolean deletarCategoria(Categoria categoria) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {categoria.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    
    public Categoria obterPorIdCategoria(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Categoria>() {
            @Override
            public Categoria mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Categoria categoria = new Categoria();

                categoria.setId(rs.getInt("id"));
                categoria.setDescricao(rs.getString("descricao"));
                categoria.setRegistro(rs.getDate("registro"));

                Integer secaoId = rs.getInt("secao");
                Secao secao = secaoRepo.obterPorIdSecao(secaoId);
                categoria.setSecao(secao);

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                categoria.setUsuario(usuario);

                Integer entidadeId = rs.getInt("entidade");
                PessoaJuridica entidade = pessoaJuridicaRepo.obterPorIdPessoaJuridica(entidadeId);
                categoria.setEntidade(entidade);

                return categoria;
            }
        });

    }

    public List<Categoria> obterTodosCategorias(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Categoria>() {
            @Override
            public Categoria mapRow(ResultSet rs, int rownumber) throws SQLException {

                Categoria categoria = new Categoria();

                categoria.setId(rs.getInt("id"));
                categoria.setDescricao(rs.getString("descricao"));
                categoria.setRegistro(rs.getDate("registro"));

                Integer secaoId = rs.getInt("secao");
                Secao secao = secaoRepo.obterPorIdSecao(secaoId);
                categoria.setSecao(secao);

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                categoria.setUsuario(usuario);

                Integer entidadeId = rs.getInt("entidade");
                PessoaJuridica entidade = pessoaJuridicaRepo.obterPorIdPessoaJuridica(entidadeId);
                categoria.setEntidade(entidade);

                return categoria;
            }
        }, entidade);

    }
    
    public List<Categoria> obterCategoriasPorSecao(Integer entidade, Integer secao) {
        
        return jdbcTemplate.query(SELECT_ALL_CATEGORY, new RowMapper<Categoria>() {
            @Override
            public Categoria mapRow(ResultSet rs, int rownumber) throws SQLException {

                Categoria categoria = new Categoria();

                categoria.setId(rs.getInt("id"));
                categoria.setDescricao(rs.getString("descricao"));
                categoria.setRegistro(rs.getDate("registro"));

                Integer secaoId = rs.getInt("secao");
                Secao secao = secaoRepo.obterPorIdSecao(secaoId);
                categoria.setSecao(secao);

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                categoria.setUsuario(usuario);

                Integer entidadeId = rs.getInt("entidade");
                PessoaJuridica entidade = pessoaJuridicaRepo.obterPorIdPessoaJuridica(entidadeId);
                categoria.setEntidade(entidade);

                return categoria;
            }
        }, entidade, secao);

    }

    public Categoria salvarCategoria(Categoria categoria) {
        
        KeyHolder holder = new GeneratedKeyHolder();
        
        Date registro = new Date();
        java.sql.Date registroSQL = new java.sql.Date(registro.getTime());

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, categoria.getDescricao());
                ps.setInt(2, categoria.getSecao().getId());
                ps.setDate(3, registroSQL);
                ps.setInt(4, categoria.getUsuario().getId());
                ps.setInt(5, categoria.getEntidade().getId());

                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        categoria.setId(id);
        
        return categoria;

    }

    
}

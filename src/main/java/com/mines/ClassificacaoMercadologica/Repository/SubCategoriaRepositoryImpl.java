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
import com.mines.ClassificacaoMercadologica.Model.SubCategoria;
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
public class SubCategoriaRepositoryImpl implements SubCategoriaRepository {

    private static String SELECT_ALL = " select * from dx_subcategoria where entidade = ? " 
            + " order by descricao";
    private static String SELECT_ALL_SUBCATEGORY = " select * from dx_subcategoria where entidade = ? " 
            + " and categoria=? order by descricao";
    private static String SELECT_ONE = " select * from dx_subcategoria where id = ? ";
    private static String INSERT = " insert into dx_subcategoria (id, descricao, categoria, registro, usuario, "
            + " entidade) "
            + " values (nextval('dx_subcategoria_id_seq'),?,?,?,?,?) ";
    private static String UPDATE = " update dx_subcategoria set descricao = ? "
            + " where id = ? ";
    private static String DELETE = " delete from dx_subcategoria where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepo;

    @Autowired
    private CategoriaRepository categoriaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public SubCategoria atualizarSubCategoria(SubCategoria subCategoria) {
       
        jdbcTemplate.update(UPDATE, new Object[] {subCategoria.getDescricao(), subCategoria.getId()});

        return subCategoria;

    }

    
    public Boolean deletarSubCategoria(SubCategoria subCategoria) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {subCategoria.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    
    public SubCategoria obterPorIdSubCategoria(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<SubCategoria>() {
            @Override
            public SubCategoria mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                SubCategoria subCategoria = new SubCategoria();

                subCategoria.setId(rs.getInt("id"));
                subCategoria.setDescricao(rs.getString("descricao"));
                subCategoria.setRegistro(rs.getDate("registro"));

                Integer categoriaId = rs.getInt("categoria");
                Categoria categoria = categoriaRepo.obterPorIdCategoria(categoriaId);
                subCategoria.setCategoria(categoria);

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                subCategoria.setUsuario(usuario);

                Integer entidadeId = rs.getInt("entidade");
                PessoaJuridica entidade = pessoaJuridicaRepo.obterPorIdPessoaJuridica(entidadeId);
                subCategoria.setEntidade(entidade);

                return subCategoria;
            }
        });

    }

    public List<SubCategoria> obterTodosSubCategorias(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<SubCategoria>() {
            @Override
            public SubCategoria mapRow(ResultSet rs, int rownumber) throws SQLException {

                SubCategoria subCategoria = new SubCategoria();

                subCategoria.setId(rs.getInt("id"));
                subCategoria.setDescricao(rs.getString("descricao"));
                subCategoria.setRegistro(rs.getDate("registro"));

                Integer categoriaId = rs.getInt("categoria");
                Categoria categoria = categoriaRepo.obterPorIdCategoria(categoriaId);
                subCategoria.setCategoria(categoria);

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                subCategoria.setUsuario(usuario);

                Integer entidadeId = rs.getInt("entidade");
                PessoaJuridica entidade = pessoaJuridicaRepo.obterPorIdPessoaJuridica(entidadeId);
                subCategoria.setEntidade(entidade);

                return subCategoria;
            }
        }, entidade);

    }
    
    public List<SubCategoria> obterSubCategoriasPorCategoria(Integer entidade, Integer categoria) {
        
        return jdbcTemplate.query(SELECT_ALL_SUBCATEGORY, new RowMapper<SubCategoria>() {
            @Override
            public SubCategoria mapRow(ResultSet rs, int rownumber) throws SQLException {

                SubCategoria subCategoria = new SubCategoria();

                subCategoria.setId(rs.getInt("id"));
                subCategoria.setDescricao(rs.getString("descricao"));
                subCategoria.setRegistro(rs.getDate("registro"));

                Integer categoriaId = rs.getInt("categoria");
                Categoria categoria = categoriaRepo.obterPorIdCategoria(categoriaId);
                subCategoria.setCategoria(categoria);

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                subCategoria.setUsuario(usuario);

                Integer entidadeId = rs.getInt("entidade");
                PessoaJuridica entidade = pessoaJuridicaRepo.obterPorIdPessoaJuridica(entidadeId);
                subCategoria.setEntidade(entidade);

                return subCategoria;
            }
        }, entidade, categoria);

    }

    public SubCategoria salvarSubCategoria(SubCategoria subCategoria) {
        
        KeyHolder holder = new GeneratedKeyHolder();
        
        Date registro = new Date();
        java.sql.Date registroSQL = new java.sql.Date(registro.getTime());

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, subCategoria.getDescricao());
                ps.setInt(2, subCategoria.getCategoria().getId());
                ps.setDate(3, registroSQL);
                ps.setInt(4, subCategoria.getUsuario().getId());
                ps.setInt(5, subCategoria.getEntidade().getId());

                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        subCategoria.setId(id);
        
        return subCategoria;

    }

    
}

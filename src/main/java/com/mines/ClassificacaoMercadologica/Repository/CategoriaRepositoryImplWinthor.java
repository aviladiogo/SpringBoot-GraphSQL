package com.mines.ClassificacaoMercadologica.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriaRepositoryImplWinthor implements CategoriaRepositoryWinthor {

    private static String SELECT_ALL = " ";
    private static String SELECT_ALL_CATEGORY = " ";
    private static String SELECT_ONE = " ";
    
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
    
}

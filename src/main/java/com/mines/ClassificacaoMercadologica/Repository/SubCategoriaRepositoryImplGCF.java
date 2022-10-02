package com.mines.ClassificacaoMercadologica.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SubCategoriaRepositoryImplGCF implements SubCategoriaRepositoryGCF {

    private static String SELECT_ALL = " " 
            + " order by descricao";
    private static String SELECT_ALL_SUBCATEGORY = " ";
    private static String SELECT_ONE = " ";
    
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

}

package com.mines.Seguranca.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;
import com.mines.Seguranca.Model.Perfil;
import com.mines.Seguranca.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepositoryImplGCF implements UsuarioRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private PerfilRepository perfilRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Usuario obterPorIdUsuario(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] { id }, new RowMapper<Usuario>() {
            @Override
            public Usuario mapRow(ResultSet rs, int rownumber) throws SQLException {

                Usuario usuario = new Usuario();

                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setAtivo(rs.getBoolean("ativo"));

                Integer pessoaFisicaId = rs.getInt("id");
                PessoaFisica pessoaFisica = pessoaFisicaRepo.obterPorIdPessoaFisica(pessoaFisicaId);
                usuario.setPessoaFisicaId(pessoaFisica);

                Integer perfilId = rs.getInt("perfil");
                Perfil perfil = perfilRepo.obterPorIdPerfil(perfilId);
                usuario.setPerfil(perfil);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                usuario.setEntidadeGestora(entidadeGestora);

                return usuario;
            }
        });

    }

    public List<Usuario> obterTodosUsuarios(Integer entidade) {

        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Usuario>() {
            @Override
            public Usuario mapRow(ResultSet rs, int rownumber) throws SQLException {

                Usuario usuario = new Usuario();

                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setAtivo(rs.getBoolean("ativo"));

                Integer pessoaFisicaId = rs.getInt("id");
                PessoaFisica pessoaFisica = pessoaFisicaRepo.obterPorIdPessoaFisica(pessoaFisicaId);
                usuario.setPessoaFisicaId(pessoaFisica);

                Integer perfilId = rs.getInt("perfil");
                Perfil perfil = perfilRepo.obterPorIdPerfil(perfilId);
                usuario.setPerfil(perfil);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                usuario.setEntidadeGestora(entidadeGestora);

                return usuario;
            }
        }, entidade);

    }

}

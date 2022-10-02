package com.mines.Seguranca.Repository;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;
import com.mines.Seguranca.Model.Perfil;
import com.mines.Seguranca.Model.Usuario;
import com.mines.Seguranca.Service.SegurancaWebSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private static String SELECT_ALL = " select * from dx_usuario where entidadegestora = ? ";
    private static String SELECT_ONE = " select * from dx_usuario where id = ? ";
    private static String SELECT_BY_LOGIN = " select * from dx_usuario where login ilike ? ";
    private static String INSERT = " insert into dx_usuario (id, login, senha, ativo, perfil, entidadegestora)"
            + " values (?,?,?,?,?,?) ";
    private static String UPDATE = " update dx_usuario set login = ?, ativo = ? where id = ? ";
    private static String DELETE = " delete from dx_usuario where id = ? ";

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

    public Usuario atualizarUsuario(Usuario usuario) {

        jdbcTemplate.update(UPDATE,
                new Object[] { usuario.getLogin(), usuario.getAtivo(), usuario.getPessoaFisicaId().getId() });

        return usuario;

    }

    public Boolean deletarUsuario(Usuario usuario) {

        Boolean ret = false;

        Integer rows = jdbcTemplate.update(DELETE, new Object[] { usuario.getPessoaFisicaId().getId() });

        if (rows > 0) {
            ret = true;
        }

        return ret;

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

    public Usuario obterUsuarioPorLogin(String login){
        
        return jdbcTemplate.queryForObject(SELECT_BY_LOGIN, new Object[] { login }, new RowMapper<Usuario>() {
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

    public Usuario verificarLoginExistente(String login){

        Usuario usuario = null;
        try{
            usuario = obterUsuarioPorLogin(login);
        }
        catch(Exception e){
            return usuario;
        }
        return usuario;
    }

    public Usuario validarLogin(String login, String senha) {

        Usuario usuario = null;
        Usuario usuarioValidado = obterUsuarioPorLogin(login);

        SegurancaWebSecurity segurancaService = new SegurancaWebSecurity();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if(usuarioValidado!=null){
            String hashPassword = usuarioValidado.getSenha();
            if(passwordEncoder.matches(senha, hashPassword)){
                usuario = usuarioValidado;
                System.out.println(segurancaService.generateToken(usuario));
            }
        }
        return usuario;   
    }

    public Usuario inserirUsuario(Usuario usuario) throws Exception {

        KeyHolder holder = new GeneratedKeyHolder();

        int strength = 10; // work factor of bcrypt
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        String senhaCriptografada = bCryptPasswordEncoder.encode(usuario.getSenha());

        if(verificarLoginExistente(usuario.getLogin())!=null){
            throw new Exception("Login já existente no banco, por favor insira algum outro");
        }

        PessoaFisica pessoaFisicaId = pessoaFisicaRepo.obterPorIdPessoaFisica(usuario.getPessoaFisicaId().getId());
        usuario.setPessoaFisicaId(pessoaFisicaId);

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, usuario.getPessoaFisicaId().getId());
                ps.setString(2, usuario.getLogin());
                ps.setString(3, senhaCriptografada);
                ps.setBoolean(4, usuario.getAtivo());
                ps.setInt(5, usuario.getPerfil().getId());
                ps.setInt(6, usuario.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        return usuario;

    }

}

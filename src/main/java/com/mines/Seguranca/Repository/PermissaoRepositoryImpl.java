package com.mines.Seguranca.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.Seguranca.Model.Perfil;
import com.mines.Seguranca.Model.Permissao;
import com.mines.Seguranca.Model.Transacao;
import com.mines.Seguranca.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class PermissaoRepositoryImpl implements PermissaoRepository {

    private static String SELECT_ALL = " select * from dx_permissao where entidadegestora = ? "
            + " order by descricao ";
    private static String SELECT_ONE = " select * from dx_permissao where id = ? ";
    private static String INSERT_P = " insert into dx_permissao (id, descricao, perfil, transacao, "
            + " entidadegestora) values (nextval('dx_permissao_id_seq'),?,?,?,?) ";
    private static String INSERT_U = " insert into dx_permissao (id, descricao, usuario, transacao, "
            + " entidadegestora) values (nextval('dx_permissao_id_seq'),?,?,?,?) ";
    private static String UPDATE = " update dx_permissao set descricao = ? where id = ?";
    private static String DELETE = " delete from dx_permissao where id = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PerfilRepository perfilRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private TransacaoRepository transacaoRepo;
    
    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Permissao atualizarPermissao(Permissao permissao) {

        jdbcTemplate.update(UPDATE, new Object[] { permissao.getDescricao(), permissao.getId() });

        return permissao;

    }

    public Boolean deletarPermissao(Permissao permissao) {

        Boolean ret = false;

        Integer rows = jdbcTemplate.update(DELETE, new Object[] { permissao.getId() });

        if (rows > 0) {
            ret = true;
        }

        return ret;

    }

    public Permissao obterPorIdPermissao(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] { id }, new RowMapper<Permissao>() {
            @Override
            public Permissao mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Permissao permissao = new Permissao();

                permissao.setId(rs.getInt("id"));
                permissao.setDescricao(rs.getString("descricao"));
                
                Integer perfilId = rs.getInt("perfil");
                if(perfilId != 0){
                    Perfil perfil = perfilRepo.obterPorIdPerfil(perfilId);
                    permissao.setPerfil(perfil);
                }

                Integer usuarioId = rs.getInt("usuario");
                if(usuarioId != 0){
                    Usuario usuario = usuarioRepo.obterPorIdUsuario(usuarioId);;
                    permissao.setUsuario(usuario);
                }

                Integer transacaoId = rs.getInt("transacao");
                Transacao transacao = transacaoRepo.obterPorIdTransacao(transacaoId);
                permissao.setTransacao(transacao);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                permissao.setEntidadeGestora(entidadeGestora);
                
                return permissao;
            }
        });

    }

    public List<Permissao> obterTodosPermissoes(Integer entidade) {

        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Permissao>() {
            @Override
            public Permissao mapRow(ResultSet rs, int rownumber) throws SQLException {

                Permissao permissao = new Permissao();
                
                permissao.setId(rs.getInt("id"));
                permissao.setDescricao(rs.getString("descricao"));
                
                Integer perfilId = rs.getInt("perfil");
                if(perfilId != 0){
                    Perfil perfil = perfilRepo.obterPorIdPerfil(perfilId);
                    permissao.setPerfil(perfil);
                }

                Integer usuarioId = rs.getInt("usuario");
                if(usuarioId != 0){
                    Usuario usuario = usuarioRepo.obterPorIdUsuario(usuarioId);;
                    permissao.setUsuario(usuario);
                }

                Integer transacaoId = rs.getInt("transacao");
                Transacao transacao = transacaoRepo.obterPorIdTransacao(transacaoId);
                permissao.setTransacao(transacao);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                permissao.setEntidadeGestora(entidadeGestora);
                
                return permissao;
            }
        }, entidade);

    }

    public Permissao salvarPermissao(Permissao permissao) {

        KeyHolder holder = new GeneratedKeyHolder();

        if(permissao.getPerfil().getId()!=null){
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(INSERT_P, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, permissao.getDescricao());
                    ps.setInt(2, permissao.getPerfil().getId());
                    ps.setInt(3, permissao.getTransacao().getId());
                    ps.setInt(4, permissao.getEntidadeGestora().getId());
                    return ps;
                }
            }, holder);
        }
        else if(permissao.getUsuario().getPessoaFisicaId()!=null){
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(INSERT_U, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, permissao.getDescricao());
                    ps.setInt(2, permissao.getUsuario().getPessoaFisicaId().getId());
                    ps.setInt(3, permissao.getTransacao().getId());
                    ps.setInt(4, permissao.getEntidadeGestora().getId());
                    return ps;
                }
            }, holder);
        }
        
        Integer id = (Integer) holder.getKeys().get("id");
        permissao.setId(id);

        return permissao;

    }

}

package com.mines.Seguranca.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PermissaoRepositoryImplWinthor implements PermissaoRepositoryWinthor {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";

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

}

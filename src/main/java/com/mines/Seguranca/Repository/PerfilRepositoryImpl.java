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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class PerfilRepositoryImpl implements PerfilRepository {

    private static String SELECT_ALL = " select * from dx_perfil where entidadegestora = ? " 
            + " order by descricao ";
    private static String SELECT_ONE = " select * from dx_perfil where id = ? ";
    private static String INSERT = " insert into dx_perfil (id, descricao, ativo, entidadegestora)"
            + " values (nextval('dx_perfil_id_seq'),?,?,?) ";
    private static String UPDATE = " update dx_perfil set descricao = ?, ativo = ? where id = ? ";
    private static String DELETE = " delete from dx_perfil where id = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Perfil atualizarPerfil(Perfil perfil) {

        jdbcTemplate.update(UPDATE, new Object[] { perfil.getDescricao(), perfil.getAtivo(), perfil.getId() });

        return perfil;

    }

    public Boolean deletarPerfil(Perfil perfil) {

        Boolean ret = false;

        Integer rows = jdbcTemplate.update(DELETE, new Object[] { perfil.getId() });

        if (rows > 0) {
            ret = true;
        }

        return ret;

    }

    public Perfil obterPorIdPerfil(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] { id }, new RowMapper<Perfil>() {
            @Override
            public Perfil mapRow(ResultSet rs, int rownumber) throws SQLException {

                Perfil perfil = new Perfil();

                perfil.setId(rs.getInt("id"));
                perfil.setDescricao(rs.getString("descricao"));
                perfil.setAtivo(rs.getBoolean("ativo"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                perfil.setEntidadeGestora(entidadeGestora);

                return perfil;
            }
        });

    }

    public List<Perfil> obterTodosPerfis(Integer entidade) {

        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Perfil>() {
            @Override
            public Perfil mapRow(ResultSet rs, int rownumber) throws SQLException {

                Perfil perfil = new Perfil();

                perfil.setId(rs.getInt("id"));
                perfil.setDescricao(rs.getString("descricao"));
                perfil.setAtivo(rs.getBoolean("ativo"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                perfil.setEntidadeGestora(entidadeGestora);

                return perfil;
            }
        }, entidade);

    }

    public Perfil salvarPerfil(Perfil perfil) {

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, perfil.getDescricao());
                ps.setBoolean(2, perfil.getAtivo());
                ps.setInt(3, perfil.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        perfil.setId(id);

        return perfil;

    }

}

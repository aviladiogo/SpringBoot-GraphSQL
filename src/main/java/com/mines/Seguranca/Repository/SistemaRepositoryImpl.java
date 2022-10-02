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
import com.mines.Seguranca.Model.Sistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class SistemaRepositoryImpl implements SistemaRepository {

    private static String SELECT_ALL = " select * from dx_sistema where entidadegestora = ? " 
            + " order by descricao ";
    private static String SELECT_ONE = " select * from dx_sistema where id = ? ";
    private static String INSERT = " insert into dx_sistema (id, descricao, ativo, entidadegestora)"
            + " values (nextval('dx_sistema_id_seq'),?,?,?) ";
    private static String UPDATE = " update dx_sistema set descricao = ?, ativo = ? where id = ? ";
    private static String DELETE = " delete from dx_sistema where id = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Sistema atualizarSistema(Sistema sistema) {

        jdbcTemplate.update(UPDATE, new Object[] { sistema.getDescricao(), sistema.getAtivo(), sistema.getId() });

        return sistema;

    }

    public Boolean deletarSistema(Sistema sistema) {

        Boolean ret = false;

        Integer rows = jdbcTemplate.update(DELETE, new Object[] { sistema.getId() });

        if (rows > 0) {
            ret = true;
        }

        return ret;

    }

    public Sistema obterPorIdSistema(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] { id }, new RowMapper<Sistema>() {
            @Override
            public Sistema mapRow(ResultSet rs, int rownumber) throws SQLException {

                Sistema sistema = new Sistema();

                sistema.setId(rs.getInt("id"));
                sistema.setDescricao(rs.getString("descricao"));
                sistema.setAtivo(rs.getBoolean("ativo"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                sistema.setEntidadeGestora(entidadeGestora);

                return sistema;
            }
        });

    }

    public List<Sistema> obterTodosSistemas(Integer entidade) {

        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Sistema>() {
            @Override
            public Sistema mapRow(ResultSet rs, int rownumber) throws SQLException {

                Sistema sistema = new Sistema();

                sistema.setId(rs.getInt("id"));
                sistema.setDescricao(rs.getString("descricao"));
                sistema.setAtivo(rs.getBoolean("ativo"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                sistema.setEntidadeGestora(entidadeGestora);

                return sistema;
            }
        }, entidade);

    }

    public Sistema salvarSistema(Sistema sistema) {

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, sistema.getDescricao());
                ps.setBoolean(2, sistema.getAtivo());
                ps.setInt(3, sistema.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        sistema.setId(id);

        return sistema;

    }

}

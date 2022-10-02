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
import com.mines.Seguranca.Model.Modulo;
import com.mines.Seguranca.Model.Sistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ModuloRepositoryImpl implements ModuloRepository {

    private static String SELECT_ALL = " select * from dx_modulo where entidadegestora = ? " 
            + " order by descricao ";
    private static String SELECT_ONE = " select * from dx_modulo where id = ? ";
    private static String INSERT = " insert into dx_modulo (id, descricao, ativo, sistema, entidadegestora)"
            + " values (nextval('dx_modulo_id_seq'),?,?,?,?) ";
    private static String UPDATE = " update dx_modulo set descricao = ?, ativo = ? where id = ? ";
    private static String DELETE = " delete from dx_modulo where id = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SistemaRepository sistemaRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Modulo atualizarModulo(Modulo modulo) {

        jdbcTemplate.update(UPDATE, new Object[] { modulo.getDescricao(), modulo.getAtivo(), modulo.getId() });

        return modulo;

    }

    public Boolean deletarModulo(Modulo modulo) {

        Boolean ret = false;

        Integer rows = jdbcTemplate.update(DELETE, new Object[] { modulo.getId() });

        if (rows > 0) {
            ret = true;
        }

        return ret;

    }

    public Modulo obterPorIdModulo(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] { id }, new RowMapper<Modulo>() {
            @Override
            public Modulo mapRow(ResultSet rs, int rownumber) throws SQLException {

                Modulo modulo = new Modulo();

                modulo.setId(rs.getInt("id"));
                modulo.setDescricao(rs.getString("descricao"));
                modulo.setAtivo(rs.getBoolean("ativo"));

                Integer sistemaId = rs.getInt("sistema");
                Sistema sistema = sistemaRepo.obterPorIdSistema(sistemaId);
                modulo.setSistema(sistema);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                modulo.setEntidadeGestora(entidadeGestora);

                return modulo;
            }
        });

    }

    public List<Modulo> obterTodosModulos(Integer entidade) {

        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Modulo>() {
            @Override
            public Modulo mapRow(ResultSet rs, int rownumber) throws SQLException {

                Modulo modulo = new Modulo();

                modulo.setId(rs.getInt("id"));
                modulo.setDescricao(rs.getString("descricao"));
                modulo.setAtivo(rs.getBoolean("ativo"));

                Integer sistemaId = rs.getInt("sistema");
                Sistema sistema = sistemaRepo.obterPorIdSistema(sistemaId);
                modulo.setSistema(sistema);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                modulo.setEntidadeGestora(entidadeGestora);

                return modulo;
            }
        }, entidade);

    }

    public Modulo salvarModulo(Modulo modulo) {

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, modulo.getDescricao());
                ps.setBoolean(2, modulo.getAtivo());
                ps.setInt(3, modulo.getSistema().getId());
                ps.setInt(4, modulo.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        modulo.setId(id);

        return modulo;

    }

}

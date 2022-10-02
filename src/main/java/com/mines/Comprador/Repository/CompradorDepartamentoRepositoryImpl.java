package com.mines.Comprador.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.ClassificacaoMercadologica.Model.Departamento;
import com.mines.ClassificacaoMercadologica.Repository.DepartamentoRepository;
import com.mines.Comprador.Model.CompradorDepartamento;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CompradorDepartamentoRepositoryImpl implements CompradorDepartamentoRepository {

    private static String SELECT_ALL = " select * from dx_compradordepartamento where entidadegestora = ? ";
    private static String SELECT_ONE = " select * from dx_compradordepartamento where comprador = ? ";
    private static String INSERT = " insert into dx_compradordepartamento (comprador, limiteCompra, ativo, departamento, "
            + " entidadegestora) values (?,?,?,?,?) ";
    private static String UPDATE = " update dx_compradordepartamento set limitecompra = ?, ativo = ? "
            + " where comprador = ? ";
    private static String DELETE = " delete from dx_compradordepartamento where comprador = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DepartamentoRepository departamentoRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public CompradorDepartamento atualizarCompradorDepartamento(CompradorDepartamento compradorDepartamento) {

        jdbcTemplate.update(UPDATE,
                new Object[] { compradorDepartamento.getLimiteCompra(), compradorDepartamento.getAtivo(), compradorDepartamento.getComprador() });

        return compradorDepartamento;

    }

    public Boolean deletarCompradorDepartamento(CompradorDepartamento compradorDepartamento) {

        Boolean ret = false;

        Integer rows = jdbcTemplate.update(DELETE, new Object[] { compradorDepartamento.getComprador() });

        if (rows > 0) {
            ret = true;
        }

        return ret;

    }

    public CompradorDepartamento obterPorIdCompradorDepartamento(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] { id }, new RowMapper<CompradorDepartamento>() {
            @Override
            public CompradorDepartamento mapRow(ResultSet rs, int rownumber) throws SQLException {

                CompradorDepartamento compradorDepartamento = new CompradorDepartamento();

                compradorDepartamento.setComprador(rs.getInt("comprador"));
                compradorDepartamento.setLimiteCompra(rs.getDouble("limitecompra"));
                compradorDepartamento.setAtivo(rs.getInt("ativo"));

                Integer departamentoId = rs.getInt("departamento");
                Departamento departamento = departamentoRepo.obterPorIdDepartamento(departamentoId);
                compradorDepartamento.setDepartamento(departamento);

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                compradorDepartamento.setEntidadeGestora(entidadeGestora);

                return compradorDepartamento;
            }
        });

    }

    public List<CompradorDepartamento> obterTodosCompradoresDepartamento(Integer entidade) {

        return jdbcTemplate.query(SELECT_ALL, new RowMapper<CompradorDepartamento>() {
            @Override
            public CompradorDepartamento mapRow(ResultSet rs, int rownumber) throws SQLException {

                CompradorDepartamento compradorDepartamento = new CompradorDepartamento();

                compradorDepartamento.setComprador(rs.getInt("comprador"));
                compradorDepartamento.setLimiteCompra(rs.getDouble("limitecompra"));
                compradorDepartamento.setAtivo(rs.getInt("ativo"));

                Integer departamentoId = rs.getInt("departamento");
                Departamento departamento = departamentoRepo.obterPorIdDepartamento(departamentoId);
                compradorDepartamento.setDepartamento(departamento);

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                compradorDepartamento.setEntidadeGestora(entidadeGestora);

                return compradorDepartamento;
            }
        }, entidade);

    }

    public CompradorDepartamento salvarCompradorDepartamento(CompradorDepartamento compradorDepartamento) {

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, compradorDepartamento.getComprador());
                ps.setDouble(2, compradorDepartamento.getLimiteCompra());
                ps.setInt(3, compradorDepartamento.getAtivo());
                ps.setInt(4, compradorDepartamento.getDepartamento().getId());
                ps.setInt(5, compradorDepartamento.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        //Integer comprador = (Integer) holder.getKeys().get("comprador");
        //compradorDepartamento.setComprador(comprador);

        return compradorDepartamento;

    }

}

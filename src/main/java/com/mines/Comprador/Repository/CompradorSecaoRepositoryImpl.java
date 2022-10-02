package com.mines.Comprador.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.ClassificacaoMercadologica.Model.Secao;
import com.mines.ClassificacaoMercadologica.Repository.SecaoRepository;
import com.mines.Comprador.Model.CompradorSecao;
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
public class CompradorSecaoRepositoryImpl implements CompradorSecaoRepository {

    private static String SELECT_ALL = " select * from dx_compradorsecao where entidadegestora = ? ";
    private static String SELECT_ONE = " select * from dx_compradorsecao where comprador = ? ";
    private static String INSERT = " insert into dx_compradorsecao (comprador, limiteCompra, ativo, secao, "
            + " entidadegestora) values (?,?,?,?,?) ";
    private static String UPDATE = " update dx_compradorsecao set limitecompra = ?, ativo = ? "
            + " where comprador = ? ";
    private static String DELETE = " delete from dx_compradorsecao where comprador = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SecaoRepository SecaoRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public CompradorSecao atualizarCompradorSecao(CompradorSecao compradorSecao) {

        jdbcTemplate.update(UPDATE,
                new Object[] { compradorSecao.getLimiteCompra(), compradorSecao.getAtivo(), compradorSecao.getComprador() });

        return compradorSecao;

    }

    public Boolean deletarCompradorSecao(CompradorSecao compradorSecao) {

        Boolean ret = false;

        Integer rows = jdbcTemplate.update(DELETE, new Object[] { compradorSecao.getComprador() });

        if (rows > 0) {
            ret = true;
        }

        return ret;

    }

    public CompradorSecao obterPorIdCompradorSecao(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] { id }, new RowMapper<CompradorSecao>() {
            @Override
            public CompradorSecao mapRow(ResultSet rs, int rownumber) throws SQLException {

                CompradorSecao compradorSecao = new CompradorSecao();

                compradorSecao.setComprador(rs.getInt("comprador"));
                compradorSecao.setLimiteCompra(rs.getDouble("limitecompra"));
                compradorSecao.setAtivo(rs.getInt("ativo"));

                Integer secaoId = rs.getInt("secao");
                Secao secao = SecaoRepo.obterPorIdSecao(secaoId);
                compradorSecao.setSecao(secao);

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                compradorSecao.setEntidadeGestora(entidadeGestora);

                return compradorSecao;
            }
        });

    }

    public List<CompradorSecao> obterTodosCompradoresSecao(Integer entidade) {

        return jdbcTemplate.query(SELECT_ALL, new RowMapper<CompradorSecao>() {
            @Override
            public CompradorSecao mapRow(ResultSet rs, int rownumber) throws SQLException {

                CompradorSecao compradorSecao = new CompradorSecao();

                compradorSecao.setComprador(rs.getInt("comprador"));
                compradorSecao.setLimiteCompra(rs.getDouble("limitecompra"));
                compradorSecao.setAtivo(rs.getInt("ativo"));

                Integer secaoId = rs.getInt("secao");
                Secao secao = SecaoRepo.obterPorIdSecao(secaoId);
                compradorSecao.setSecao(secao);

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                compradorSecao.setEntidadeGestora(entidadeGestora);

                return compradorSecao;
            }
        }, entidade);

    }

    public CompradorSecao salvarCompradorSecao(CompradorSecao compradorSecao) {

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, compradorSecao.getComprador());
                ps.setDouble(2, compradorSecao.getLimiteCompra());
                ps.setInt(3, compradorSecao.getAtivo());
                ps.setInt(4, compradorSecao.getSecao().getId());
                ps.setInt(5, compradorSecao.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        //Integer comprador = (Integer) holder.getKeys().get("comprador");
        //compradorSecao.setComprador(comprador);

        return compradorSecao;

    }

}


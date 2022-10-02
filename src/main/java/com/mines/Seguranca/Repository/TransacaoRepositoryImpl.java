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
import com.mines.Seguranca.Model.Transacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class TransacaoRepositoryImpl implements TransacaoRepository {

    private static String SELECT_ALL = " select * from dx_transacao where entidadegestora = ? "
            + " order by descricao ";
    private static String SELECT_ONE = " select * from dx_transacao where id = ? ";
    private static String INSERT = " insert into dx_transacao (id, descricao, ativo, modulo, entidadegestora)"
            + " values (nextval('dx_transacao_id_seq'),?,?,?,?) ";
    private static String UPDATE = " update dx_transacao set descricao = ?, ativo = ? where id = ? ";
    private static String DELETE = " delete from dx_transacao where id = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ModuloRepository moduloRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Transacao atualizarTransacao(Transacao transacao) {

        jdbcTemplate.update(UPDATE, new Object[] { transacao.getDescricao(), transacao.getAtivo(), transacao.getId() });

        return transacao;

    }

    public Boolean deletarTransacao(Transacao transacao) {

        Boolean ret = false;

        Integer rows = jdbcTemplate.update(DELETE, new Object[] { transacao.getId() });

        if (rows > 0) {
            ret = true;
        }

        return ret;

    }

    public Transacao obterPorIdTransacao(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] { id }, new RowMapper<Transacao>() {
            @Override
            public Transacao mapRow(ResultSet rs, int rownumber) throws SQLException {

                Transacao transacao = new Transacao();

                transacao.setId(rs.getInt("id"));
                transacao.setDescricao(rs.getString("descricao"));
                transacao.setAtivo(rs.getBoolean("ativo"));

                Integer moduloId = rs.getInt("modulo");
                Modulo modulo = moduloRepo.obterPorIdModulo(moduloId);
                transacao.setModulo(modulo);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                transacao.setEntidadeGestora(entidadeGestora);

                return transacao;
            }
        });

    }

    public List<Transacao> obterTodosTransacoes(Integer entidade) {

        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Transacao>() {
            @Override
            public Transacao mapRow(ResultSet rs, int rownumber) throws SQLException {

                Transacao transacao = new Transacao();

                transacao.setId(rs.getInt("id"));
                transacao.setDescricao(rs.getString("descricao"));
                transacao.setAtivo(rs.getBoolean("ativo"));

                Integer moduloId = rs.getInt("modulo");
                Modulo modulo = moduloRepo.obterPorIdModulo(moduloId);
                transacao.setModulo(modulo);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                transacao.setEntidadeGestora(entidadeGestora);

                return transacao;
            }
        }, entidade);

    }
    
    public Transacao salvarTransacao(Transacao transacao) {

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, transacao.getDescricao());
                ps.setBoolean(2, transacao.getAtivo());
                ps.setInt(3, transacao.getModulo().getId());
                ps.setInt(4, transacao.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        transacao.setId(id);

        return transacao;

    }

}

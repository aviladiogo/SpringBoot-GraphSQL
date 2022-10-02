package com.mines.Seguranca.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.Seguranca.Model.Modulo;
import com.mines.Seguranca.Model.Transacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TransacaoRepositoryImplWinthor implements TransacaoRepositoryWinthor {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ModuloRepository moduloRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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

}


package com.mines.TabelaPreco.Repository;

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
import com.mines.Fornecedor.Model.Fornecedor;
import com.mines.Fornecedor.Repository.FornecedorRepository;
import com.mines.TabelaPreco.Model.TabelaPreco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class TabelaPrecoRepositoryImpl implements TabelaPrecoRepository {

    private static String SELECT_ALL = " select * from dx_tabelapreco where entidadegestora = ?";
    private static String SELECT_ALL_FORNECEDOR_POR_TABELA_PRECO = " select * from dx_tabelapreco_fornecedor "
            + " where tabelapreco = ?";
    private static String SELECT_ONE = " select * from dx_tabelapreco where id = ? ";
    private static String INSERT = " insert into dx_tabelapreco (id, responsavel, promocao, titulo, "
            + " iniciovalidade, terminovalidade, ativo, entidadegestora) "
            + " values (nextval('dx_tabelapreco_id_seq'),?,?,?,?,?,?,?) ";
    private static String INSERT_FORNECEDOR = " insert into dx_tabelapreco_fornecedor (tabelapreco, "
            + " fornecedor) values (?,?)";
    private static String UPDATE = " update dx_tabelapreco set ativo = ?, promocao = ?, titulo = ? "
            + " where id = ? ";
    private static String DELETE = " delete from dx_tabelapreco where id = ? ";
    private static String DELETE_FORNECEDOR = "delete from dx_tabelapreco_fornecedor where tabelapreco = ?";

    @Autowired
    private JdbcTemplate jbdcTemplate;

    @Autowired
    private FornecedorRepository fornecedorRepo;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    public TabelaPreco atualizarTabelaPreco(TabelaPreco tabelaPreco) {

        jbdcTemplate.update(UPDATE, new Object[] { tabelaPreco.getAtivo(), tabelaPreco.getPromocao(),
                tabelaPreco.getTitulo(), tabelaPreco.getId() });

        return tabelaPreco;
    }

    public Boolean deletarTabelaPreco(TabelaPreco tabelaPreco) {

        Boolean ret = false;

        Integer rows = jbdcTemplate.update(DELETE, new Object[] { tabelaPreco.getId() });

        if (rows > 0) {
            ret = true;
        }

        return ret;

    }

    public Boolean deletarTabelaPrecoFornecedor(TabelaPreco tabelaPreco) {

        Boolean ret = false;

        Integer rows = jbdcTemplate.update(DELETE_FORNECEDOR, new Object[] { tabelaPreco.getId()});

        if (rows > 0) {
            ret = true;
        }

        return ret;

    }

    public TabelaPreco obterPorIdTabelaPreco(Integer id) {

        return jbdcTemplate.queryForObject(SELECT_ONE, new Object[] { id }, new RowMapper<TabelaPreco>() {
            @Override
            public TabelaPreco mapRow(ResultSet rs, int rownumber) throws SQLException {

                TabelaPreco tabelaPreco = new TabelaPreco();

                tabelaPreco.setId(rs.getInt("id"));
                tabelaPreco.setAtivo(rs.getBoolean("ativo"));
                tabelaPreco.setPromocao(rs.getBoolean("promocao"));
                tabelaPreco.setTitulo(rs.getString("titulo"));
                tabelaPreco.setInicioValidade(rs.getDate("iniciovalidade"));
                tabelaPreco.setTerminoValidade(rs.getDate("terminovalidade"));
                if (obterPorIdTabelaPrecoFornecedor(tabelaPreco.getId()) != null) {
                    tabelaPreco.setFornecedores(obterPorIdTabelaPrecoFornecedor(tabelaPreco.getId()));
                }

                Integer responsavelId = rs.getInt("responsavel");
                PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                tabelaPreco.setResponsavel(responsavel);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                tabelaPreco.setEntidadeGestora(entidadeGestora);

                return tabelaPreco;
            }
        });

    }

    public List<TabelaPreco> obterTodosTabelasPreco(Integer entidade) {

        return jbdcTemplate.query(SELECT_ALL, new RowMapper<TabelaPreco>() {
            @Override
            public TabelaPreco mapRow(ResultSet rs, int rownumber) throws SQLException {

                TabelaPreco tabelaPreco = new TabelaPreco();

                tabelaPreco.setId(rs.getInt("id"));
                tabelaPreco.setAtivo(rs.getBoolean("ativo"));
                tabelaPreco.setPromocao(rs.getBoolean("promocao"));
                tabelaPreco.setTitulo(rs.getString("titulo"));
                tabelaPreco.setInicioValidade(rs.getDate("iniciovalidade"));
                tabelaPreco.setTerminoValidade(rs.getDate("terminovalidade"));
                if (obterPorIdTabelaPrecoFornecedor(tabelaPreco.getId()) != null) {
                    tabelaPreco.setFornecedores(obterPorIdTabelaPrecoFornecedor(tabelaPreco.getId()));
                }

                Integer responsavelId = rs.getInt("responsavel");
                PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                tabelaPreco.setResponsavel(responsavel);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                tabelaPreco.setEntidadeGestora(entidadeGestora);

                return tabelaPreco;
            }
        }, entidade);
    }

    public List<Fornecedor> obterPorIdTabelaPrecoFornecedor(Integer tabelapreco) {

        return jbdcTemplate.query(SELECT_ALL_FORNECEDOR_POR_TABELA_PRECO, new RowMapper<Fornecedor>() {
            @Override
            public Fornecedor mapRow(ResultSet rs, int rownumber) throws SQLException {

                Integer fornecedorId = rs.getInt("fornecedor");
                Fornecedor fornecedor = fornecedorRepo.obterPorIdFornecedor(fornecedorId);

                return fornecedor;
            }
        }, tabelapreco);

    }

    public TabelaPreco salvarTabelaPreco(TabelaPreco tabelaPreco) {

        tabelaPreco.getInicioValidade().setDate(tabelaPreco.getInicioValidade().getDate() + 1);
        tabelaPreco.getTerminoValidade().setDate(tabelaPreco.getTerminoValidade().getDate() + 1);

        KeyHolder holder = new GeneratedKeyHolder();

        jbdcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, tabelaPreco.getResponsavel().getId());
                ps.setBoolean(2, tabelaPreco.getPromocao());
                ps.setString(3, tabelaPreco.getTitulo());
                java.sql.Date inicioValidade = new java.sql.Date(tabelaPreco.getInicioValidade().getTime());
                ps.setDate(4, inicioValidade);
                java.sql.Date terminoValidade = new java.sql.Date(tabelaPreco.getTerminoValidade().getTime());
                ps.setDate(5, terminoValidade);
                ps.setBoolean(6, tabelaPreco.getAtivo());
                ps.setInt(7, tabelaPreco.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        tabelaPreco.setId(id);

        return tabelaPreco;
    }

    public void salvarTabelaPrecoFornecedor(Integer agendaCompra, List<Integer> listaFornecedores) {

        for (int i = 0; i < listaFornecedores.size(); i++) {
            jbdcTemplate.update(INSERT_FORNECEDOR, new Object[] { agendaCompra, listaFornecedores.get(i) });
        }

    }
}

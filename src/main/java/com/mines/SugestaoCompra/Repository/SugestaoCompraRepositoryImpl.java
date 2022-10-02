package com.mines.SugestaoCompra.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import com.mines.CurvaABCZ.Model.CurvaAbcz;
import com.mines.CurvaABCZ.Repository.CurvaAbczRepository;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;
import com.mines.Fornecedor.Model.Fornecedor;
import com.mines.Fornecedor.Repository.FornecedorRepository;
import com.mines.SugestaoCompra.Model.FiltroSugestaoCompraInput;
import com.mines.SugestaoCompra.Model.SituacaoSugestaoCompra;
import com.mines.SugestaoCompra.Model.SugestaoCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class SugestaoCompraRepositoryImpl implements SugestaoCompraRepository {

    private static String SELECT_ALL = " select * from dx_sugestaocompra where entidadegestora = ?";
    private static String SELECT_ALL_FORNECEFOR_POR_SUGESTAO_COMPRA = " select fornecedor from dx_sugestaocompra_fornecedor "
            + " where sugestaocompra = ?";
    private static String SELECT_ONE = " select * from dx_sugestaocompra where id = ? ";
    private static String SELECT_ALL_FILTER = "select * from dx_sugestaocompra "
            + " where entidadegestora = ? and usuario = ? and ";
    private static String INSERT = " insert into dx_sugestaocompra (id, curvacalculo, coberturaDiasA, "
            + " coberturaDiasB, coberturaDiasC, coberturaDiasZ, prazoentregacd, prazoentregaloja, "
            + " prazoentregatotal, opcaogirodia, condicaopagto, obterestoquecd, girodiaminimo, compragrupo, "
            + " registro, usuario, situacao, entidadegestora)"
            + " values (nextval('dx_sugestaocompra_id_seq'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
    private static String INSERT_FORNECEDOR = " insert into dx_sugestaocompra_fornecedor (sugestaocompra, "
            + " fornecedor) values (?,?)";
    private static String UPDATE = " update dx_sugestaocompra set coberturadiasa = ?, coberturadiasb = ?,"
            + " coberturadiasc = ?, coberturadiasz = ?, prazoentregacd = ?, prazoentregaloja = ?, "
            + " prazoentregatotal = ?, opcaogirodia = ?, condicaopagto = ?, obterestoquecd = ?, girodiaminimo = ?, "
            + " compragrupo = ? where id = ? ";
    private static String DELETE = " delete from dx_sugestaocompra where id = ? ";
    private static String DELETE_FORNECEDOR = "delete from dx_sugestaocompra_fornecedor where sugestaocompra = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CurvaAbczRepository curvaAbczRepo;

    @Autowired
    private FornecedorRepository fornecedorRepo;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private SituacaoSugestaoCompraRepository situacaoSugestaoCompraRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public SugestaoCompra atualizarSugestaoCompra(SugestaoCompra sugestaoCompra) {

        jdbcTemplate.update(UPDATE,
                new Object[] { sugestaoCompra.getCoberturaDiasA(), sugestaoCompra.getCoberturaDiasB(),
                        sugestaoCompra.getCoberturaDiasC(), sugestaoCompra.getCoberturaDiasZ(),
                        sugestaoCompra.getPrazoEntregaCD(), sugestaoCompra.getPrazoEntregaLoja(),
                        sugestaoCompra.getPrazoEntregaTotal(), sugestaoCompra.getOpcaoGiroDia(),
                        sugestaoCompra.getCondicaoPagto(), sugestaoCompra.getObterEstoqueCD(),
                        sugestaoCompra.getGiroDiaMinimo(), sugestaoCompra.getCompraGrupo(), sugestaoCompra.getId() });

        return sugestaoCompra;
    }

    public Boolean deletarSugestaoCompra(SugestaoCompra sugestaoCompra) {

        Boolean ret = false;

        Integer rows = jdbcTemplate.update(DELETE, new Object[] { sugestaoCompra.getId() });

        if (rows > 0) {
            ret = true;
        }

        return ret;

    }

    public Boolean deletarSugestaoCompraFornecedor(SugestaoCompra sugestaoCompra) {

        Boolean ret = false;

        Integer rows = jdbcTemplate.update(DELETE_FORNECEDOR, new Object[] { sugestaoCompra.getId() });

        if (rows > 0) {
            ret = true;
        }

        return ret;

    }

    public SugestaoCompra obterPorIdSugestaoCompra(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] { id }, new RowMapper<SugestaoCompra>() {
            @Override
            public SugestaoCompra mapRow(ResultSet rs, int rownumber) throws SQLException {

                SugestaoCompra sugestaoCompra = new SugestaoCompra();

                sugestaoCompra.setId(rs.getInt("id"));

                Integer curvaCalculoId = rs.getInt("curvacalculo");
                CurvaAbcz curvaCalculo = curvaAbczRepo.obterPorIdCurvaABCZ(curvaCalculoId);
                sugestaoCompra.setCurvaCalculo(curvaCalculo);

                sugestaoCompra.setCoberturaDiasA(rs.getInt("coberturadiasa"));
                sugestaoCompra.setCoberturaDiasB(rs.getInt("coberturadiasb"));
                sugestaoCompra.setCoberturaDiasC(rs.getInt("coberturadiasc"));
                sugestaoCompra.setCoberturaDiasZ(rs.getInt("coberturadiasz"));

                sugestaoCompra.setFornecedores(obterTodosFornecedoresPorSugestaoCompra(sugestaoCompra.getId()));

                sugestaoCompra.setPrazoEntregaCD(rs.getInt("prazoentregacd"));
                sugestaoCompra.setPrazoEntregaLoja(rs.getInt("prazoentregaloja"));
                sugestaoCompra.setPrazoEntregaTotal(rs.getInt("prazoentregatotal"));
                sugestaoCompra.setOpcaoGiroDia(rs.getInt("opcaogirodia"));
                sugestaoCompra.setCondicaoPagto(rs.getInt("condicaopagto"));
                sugestaoCompra.setObterEstoqueCD(rs.getInt("obterestoquecd"));
                sugestaoCompra.setGiroDiaMinimo(rs.getDouble("girodiaminimo"));
                sugestaoCompra.setCompraGrupo(rs.getInt("compragrupo"));
                sugestaoCompra.setRegistro(rs.getDate("registro"));

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                sugestaoCompra.setUsuario(usuario);

                Integer situacaoId = rs.getInt("situacao");
                SituacaoSugestaoCompra situacao = situacaoSugestaoCompraRepo
                        .obterPorIdSituacaoSugestaoCompra(situacaoId);
                sugestaoCompra.setSituacao(situacao);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                sugestaoCompra.setEntidadeGestora(entidadeGestora);

                return sugestaoCompra;
            }
        });

    }

    public List<SugestaoCompra> obterTodosSugestaoCompra(Integer entidade) {

        return jdbcTemplate.query(SELECT_ALL, new RowMapper<SugestaoCompra>() {
            @Override
            public SugestaoCompra mapRow(ResultSet rs, int rownumber) throws SQLException {

                SugestaoCompra sugestaoCompra = new SugestaoCompra();

                sugestaoCompra.setId(rs.getInt("id"));

                Integer curvaCalculoId = rs.getInt("curvacalculo");
                CurvaAbcz curvaCalculo = curvaAbczRepo.obterPorIdCurvaABCZ(curvaCalculoId);
                sugestaoCompra.setCurvaCalculo(curvaCalculo);

                sugestaoCompra.setCoberturaDiasA(rs.getInt("coberturadiasa"));
                sugestaoCompra.setCoberturaDiasB(rs.getInt("coberturadiasb"));
                sugestaoCompra.setCoberturaDiasC(rs.getInt("coberturadiasc"));
                sugestaoCompra.setCoberturaDiasZ(rs.getInt("coberturadiasz"));

                sugestaoCompra.setFornecedores(obterTodosFornecedoresPorSugestaoCompra(sugestaoCompra.getId()));

                sugestaoCompra.setPrazoEntregaCD(rs.getInt("prazoentregacd"));
                sugestaoCompra.setPrazoEntregaLoja(rs.getInt("prazoentregaloja"));
                sugestaoCompra.setPrazoEntregaTotal(rs.getInt("prazoentregatotal"));
                sugestaoCompra.setOpcaoGiroDia(rs.getInt("opcaogirodia"));
                sugestaoCompra.setCondicaoPagto(rs.getInt("condicaopagto"));
                sugestaoCompra.setObterEstoqueCD(rs.getInt("obterestoquecd"));
                sugestaoCompra.setGiroDiaMinimo(rs.getDouble("girodiaminimo"));
                sugestaoCompra.setCompraGrupo(rs.getInt("compragrupo"));
                sugestaoCompra.setRegistro(rs.getDate("registro"));

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                sugestaoCompra.setUsuario(usuario);

                Integer situacaoId = rs.getInt("situacao");
                SituacaoSugestaoCompra situacao = situacaoSugestaoCompraRepo
                        .obterPorIdSituacaoSugestaoCompra(situacaoId);
                sugestaoCompra.setSituacao(situacao);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                sugestaoCompra.setEntidadeGestora(entidadeGestora);

                return sugestaoCompra;
            }
        }, entidade);

    }

    public List<SugestaoCompra> obterTodosSugestaoCompraPorFiltro(Integer entidade,
            FiltroSugestaoCompraInput filtroSugestaoCompraInput) {

        String SELECT = "";
        String listaFornecedores = "";
        String listaStatus = "";

        SELECT = SELECT_ALL_FILTER + "registro between '" + filtroSugestaoCompraInput.getTempoInicio() + "' and" + " '"
                + filtroSugestaoCompraInput.getTempoFim() + "' and ";

        listaStatus = filtroSugestaoCompraInput.getListaStatus().toString().replace("[", "").replace("]", "");
        SELECT = SELECT + "situacao in (" + listaStatus + ") and ";

        listaFornecedores = filtroSugestaoCompraInput.getListaFornecedores().toString().replace("[", "").replace("]",
                "");
        SELECT = SELECT
                + "id in (select distinct sugestaocompra from dx_sugestaocompra_fornecedor where fornecedor in ("
                + listaFornecedores + ")) ";

        return jdbcTemplate.query(SELECT, new RowMapper<SugestaoCompra>() {
            @Override
            public SugestaoCompra mapRow(ResultSet rs, int rownumber) throws SQLException {

                SugestaoCompra sugestaoCompra = new SugestaoCompra();

                sugestaoCompra.setId(rs.getInt("id"));

                Integer curvaCalculoId = rs.getInt("curvacalculo");
                CurvaAbcz curvaCalculo = curvaAbczRepo.obterPorIdCurvaABCZ(curvaCalculoId);
                sugestaoCompra.setCurvaCalculo(curvaCalculo);

                sugestaoCompra.setCoberturaDiasA(rs.getInt("coberturadiasa"));
                sugestaoCompra.setCoberturaDiasB(rs.getInt("coberturadiasb"));
                sugestaoCompra.setCoberturaDiasC(rs.getInt("coberturadiasc"));
                sugestaoCompra.setCoberturaDiasZ(rs.getInt("coberturadiasz"));
                sugestaoCompra.setPrazoEntregaCD(rs.getInt("prazoentregacd"));
                sugestaoCompra.setPrazoEntregaLoja(rs.getInt("prazoentregaloja"));
                sugestaoCompra.setPrazoEntregaTotal(rs.getInt("prazoentregatotal"));
                sugestaoCompra.setOpcaoGiroDia(rs.getInt("opcaogirodia"));
                sugestaoCompra.setCondicaoPagto(rs.getInt("condicaopagto"));
                sugestaoCompra.setObterEstoqueCD(rs.getInt("obterestoquecd"));
                sugestaoCompra.setGiroDiaMinimo(rs.getDouble("girodiaminimo"));
                sugestaoCompra.setCompraGrupo(rs.getInt("compragrupo"));
                sugestaoCompra.setRegistro(rs.getDate("registro"));

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                sugestaoCompra.setUsuario(usuario);

                Integer situacaoId = rs.getInt("situacao");
                SituacaoSugestaoCompra situacao = situacaoSugestaoCompraRepo
                        .obterPorIdSituacaoSugestaoCompra(situacaoId);
                sugestaoCompra.setSituacao(situacao);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                sugestaoCompra.setEntidadeGestora(entidadeGestora);

                return sugestaoCompra;
            }
        }, entidade, filtroSugestaoCompraInput.getUsuario());

    }

    public List<Fornecedor> obterTodosFornecedoresPorSugestaoCompra(Integer sugestaoCompra) {

        return jdbcTemplate.query(SELECT_ALL_FORNECEFOR_POR_SUGESTAO_COMPRA, new RowMapper<Fornecedor>() {
            @Override
            public Fornecedor mapRow(ResultSet rs, int rownumber) throws SQLException {

                Integer fornecedorId = rs.getInt("fornecedor");
                Fornecedor fornecedor = fornecedorRepo.obterPorIdFornecedor(fornecedorId);

                return fornecedor;

            }
        }, sugestaoCompra);

    }

    public SugestaoCompra salvarSugestaoCompra(SugestaoCompra sugestaoCompra) {

        Date registro = new Date();
        java.sql.Date registroSQL = new java.sql.Date(registro.getTime());

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, sugestaoCompra.getCurvaCalculo().getId());
                ps.setInt(2, sugestaoCompra.getCoberturaDiasA());
                ps.setInt(3, sugestaoCompra.getCoberturaDiasB());
                ps.setInt(4, sugestaoCompra.getCoberturaDiasC());
                ps.setInt(5, sugestaoCompra.getCoberturaDiasZ());
                ps.setInt(6, sugestaoCompra.getPrazoEntregaCD());
                ps.setInt(7, sugestaoCompra.getPrazoEntregaLoja());
                ps.setInt(8, sugestaoCompra.getPrazoEntregaTotal());
                ps.setInt(9, sugestaoCompra.getOpcaoGiroDia());
                ps.setInt(10, sugestaoCompra.getCondicaoPagto());
                ps.setInt(11, sugestaoCompra.getObterEstoqueCD());
                ps.setDouble(12, sugestaoCompra.getGiroDiaMinimo());
                ps.setInt(13, sugestaoCompra.getCompraGrupo());
                ps.setDate(14, registroSQL);
                ps.setInt(15, sugestaoCompra.getUsuario().getId());
                ps.setInt(16, sugestaoCompra.getSituacao().getId());
                ps.setInt(17, sugestaoCompra.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        sugestaoCompra.setId(id);

        return sugestaoCompra;

    }

    public void salvarSugestaoCompraFornecedor(Integer sugestaoCompra, List<Integer> listaFornecedores) {

        for (int i = 0; i < listaFornecedores.size(); i++) {
            jdbcTemplate.update(INSERT_FORNECEDOR, new Object[] { sugestaoCompra, listaFornecedores.get(i) });
        }

    }

}

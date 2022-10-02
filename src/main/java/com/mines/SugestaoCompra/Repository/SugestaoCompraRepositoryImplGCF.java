package com.mines.SugestaoCompra.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SugestaoCompraRepositoryImplGCF implements SugestaoCompraRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ALL_FORNECEFOR_POR_SUGESTAO_COMPRA = " ";
    private static String SELECT_ONE = " ";
    private static String SELECT_ALL_FILTER = "";

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
}

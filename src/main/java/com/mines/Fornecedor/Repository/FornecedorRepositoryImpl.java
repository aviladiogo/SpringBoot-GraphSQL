package com.mines.Fornecedor.Repository;

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
import com.mines.EntidadeJuridica.Model.PessoaJuridica;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;
import com.mines.EntidadeJuridica.Repository.PessoaJuridicaRepository;
import com.mines.Fornecedor.Model.Fornecedor;
import com.mines.Produto.Model.Produto;
import com.mines.Produto.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class FornecedorRepositoryImpl implements FornecedorRepository {

    private static String SELECT_ALL = " select * from dx_fornecedor where entidadegestora = ?";
    private static String SELECT_ALL_PRODUTO_POR_FORNECEDOR = " select * from dx_fornecedor_produto "
            + " where fornecedor = ? order by produto";
    private static String SELECT_ALL_FORNECEDOR_POR_PRODUTO = " select * from dx_fornecedor as dx_f "
            + " inner join dx_fornecedor_produto as dx_fp on dx_f.id = dx_fp.fornecedor "
            + " where dx_fp.produto = ? and dx_f.entidadegestora = ? order by dx_f.id ";
    private static String SELECT_ALL_POR_TERMO = " ";
    private static String SELECT_ONE = " select * from dx_fornecedor where id = ? ";
    private static String SELECT_CRITERIA = "select distinct dx_f.*, dx_pf.nome from dx_fornecedor dx_f "
            + " inner join dx_pessoafisica dx_pf on dx_f.pessoafisica = dx_pf.id "
            + " where dx_f.entidadegestora = ? and dx_pf.nome ilike ? union "
            + " select distinct dx_f.*, dx_pj.nomefantasia as nome from dx_fornecedor dx_f "
            + " inner join dx_pessoajuridica dx_pj on dx_f.pessoajuridica = dx_pj.id "
            + " where dx_f.entidadegestora = ? and dx_pj.nomefantasia ilike ? ";
    private static String SELECT_ALL_DEPARTAMENT = "select distinct dx_f.*, dx_s.departamento from dx_fornecedor dx_f "
            + " inner join dx_produto dx_p on dx_p.fabricante = dx_f.id "
            + " inner join dx_subcategoria dx_sc on dx_p.subcategoria = dx_sc.id "
            + " inner join dx_categoria dx_c on dx_sc.categoria = dx_c.id "
            + " inner join dx_secao dx_s on dx_c.secao = dx_s.id "
            + " where dx_f.entidadegestora = ? and dx_s.departamento = ? ";
    private static String SELECT_ALL_SECTION = "select distinct dx_f.*, dx_c.secao from dx_fornecedor dx_f "
            + " inner join dx_produto dx_p on dx_p.fabricante = dx_f.id "
            + " inner join dx_subcategoria dx_sc on dx_p.subcategoria = dx_sc.id "
            + " inner join dx_categoria dx_c on dx_sc.categoria = dx_c.id "
            + " where dx_f.entidadegestora = ? and dx_c.secao = ? ";
    private static String SELECT_ALL_CATEGORY = "select distinct dx_f.*, dx_sc.categoria from dx_fornecedor dx_f "
            + " inner join dx_produto dx_p on dx_p.fabricante = dx_f.id "
            + " inner join dx_subcategoria dx_sc on dx_p.subcategoria = dx_sc.id "
            + " where dx_f.entidadegestora = ? and dx_sc.categoria = ? ";
    private static String SELECT_ALL_SUBCATEGORY = "select distinct dx_f.*, dx_p.subcategoria from dx_fornecedor dx_f "
            + " inner join dx_produto dx_p on dx_p.fabricante = dx_f.id "
            + " where dx_f.entidadegestora = ? and dx_p.subcategoria = ? ";
    private static String INSERT_F = " insert into dx_fornecedor (id, tipopessoa, responsavel, ativo, "
            + " prazoentrega, pedidominimo, compradorpadrao, pessoafisica, entidadegestora) "
            + " values (nextval('dx_fornecedor_id_seq'),?,?,?,?,?,?,?,?) ";
    private static String INSERT_RESPONSAVEL_NULL_AND_F = " insert into dx_fornecedor (id, tipopessoa, ativo, prazoentrega,"
            + " pedidominimo, compradorpadrao, pessoafisica, entidadegestora) "
            + " values (nextval('dx_fornecedor_id_seq'),?,?,?,?,?,?,?) ";
    private static String INSERT_J = " insert into dx_fornecedor (id, tipopessoa, responsavel, ativo, "
            + " prazoentrega, pedidominimo, compradorpadrao, pessoajuridica, entidadegestora) "
            + " values (nextval('dx_fornecedor_id_seq'),?,?,?,?,?,?,?,?) ";
    private static String INSERT_RESPONSAVEL_NULL_AND_J = " insert into dx_fornecedor (id, tipopessoa, ativo, prazoentrega, "
            + " pedidominimo, compradorpadrao, pessoajuridica, entidadegestora) "
            + " values (nextval('dx_fornecedor_id_seq'),?,?,?,?,?,?,?) ";
    private static String INSERT_PRODUTO = " insert into dx_fornecedor_produto (fornecedor, produto) "
            + " values (?,?)";
    private static String UPDATE = " update dx_fornecedor set tipopessoa = ?, ativo = ?, prazoentrega = ?,  "
            + " pedidominimo = ? where id = ? ";
    private static String DELETE = " delete from dx_fornecedor where id = ? ";
    private static String DELETE_PRODUTO = "delete from dx_fornecedor_produto where fornecedor = ?";

    @Autowired
    private JdbcTemplate jbdcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepo;

    @Lazy
    @Autowired
    private ProdutoRepository produtoRepo;

    public void setDataSource(DataSource dataSource) {
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    public Fornecedor atualizarFornecedor(Fornecedor fornecedor) {

        jbdcTemplate.update(UPDATE, new Object[] { fornecedor.getTipoPessoa(), fornecedor.getAtivo(),
                fornecedor.getPrazoEntrega(), fornecedor.getPedidoMinimo(), fornecedor.getId() });

        return fornecedor;
    }

    public Boolean deletarFornecedor(Fornecedor fornecedor) {

        Boolean ret = false;

        Integer rows = jbdcTemplate.update(DELETE, new Object[] { fornecedor.getId() });

        if (rows > 0) {
            ret = true;
        }

        return ret;

    }

    public Boolean deletarFornecedorProduto(Fornecedor fornecedor) {

        Boolean ret = false;

        Integer rows = jbdcTemplate.update(DELETE_PRODUTO, new Object[] { fornecedor.getId() });

        if (rows > 0) {
            ret = true;
        }

        return ret;

    }

    public Fornecedor obterPorIdFornecedor(Integer id) {

        return jbdcTemplate.queryForObject(SELECT_ONE, new Object[] { id }, new RowMapper<Fornecedor>() {
            @Override
            public Fornecedor mapRow(ResultSet rs, int rownumber) throws SQLException {

                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setTipoPessoa(rs.getString("tipopessoa"));
                fornecedor.setAtivo(rs.getBoolean("ativo"));
                fornecedor.setPrazoEntrega(rs.getInt("prazoentrega"));
                fornecedor.setPedidoMinimo(rs.getDouble("pedidominimo"));
                if (obterPorIdFornecedorProduto(fornecedor.getId()) != null) {
                    fornecedor.setProdutos(obterPorIdFornecedorProduto(fornecedor.getId()));
                }

                Integer responsavelId = rs.getInt("responsavel");
                if (responsavelId != 0) {
                    PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                    fornecedor.setResponsavel(responsavel);
                }

                Integer compradorPadraoId = rs.getInt("compradorpadrao");
                PessoaFisica compradorPadrao = pessoaFisicaRepo.obterPorIdPessoaFisica(compradorPadraoId);
                fornecedor.setCompradorPadrao(compradorPadrao);

                if (fornecedor.getTipoPessoa().equals("F")) {
                    Integer pessoaFisicaId = rs.getInt("pessoafisica");
                    PessoaFisica pessoaFisica = pessoaFisicaRepo.obterPorIdPessoaFisica(pessoaFisicaId);
                    fornecedor.setPessoaFisica(pessoaFisica);
                    fornecedor.setNomeFornecedor(pessoaFisica.getNome());
                } else if (fornecedor.getTipoPessoa().equals("J")) {
                    Integer pessoaJuridicaId = rs.getInt("pessoajuridica");
                    PessoaJuridica pessoaJuridica = pessoaJuridicaRepo.obterPorIdPessoaJuridica(pessoaJuridicaId);
                    fornecedor.setPessoaJuridica(pessoaJuridica);
                    fornecedor.setNomeFornecedor(pessoaJuridica.getNomeFantasia());
                }

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                fornecedor.setEntidadeGestora(entidadeGestora);

                return fornecedor;
            }
        });

    }

    public List<Fornecedor> obterTodosFornecedores(Integer entidade) {

        return jbdcTemplate.query(SELECT_ALL, new RowMapper<Fornecedor>() {
            @Override
            public Fornecedor mapRow(ResultSet rs, int rownumber) throws SQLException {

                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setTipoPessoa(rs.getString("tipopessoa"));
                fornecedor.setAtivo(rs.getBoolean("ativo"));
                fornecedor.setPrazoEntrega(rs.getInt("prazoentrega"));
                fornecedor.setPedidoMinimo(rs.getDouble("pedidominimo"));
                if (obterPorIdFornecedorProduto(fornecedor.getId()) != null) {
                    fornecedor.setProdutos(obterPorIdFornecedorProduto(fornecedor.getId()));
                }

                Integer responsavelId = rs.getInt("responsavel");
                if (responsavelId != 0) {
                    PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                    fornecedor.setResponsavel(responsavel);
                }

                Integer compradorPadraoId = rs.getInt("compradorpadrao");
                PessoaFisica compradorPadrao = pessoaFisicaRepo.obterPorIdPessoaFisica(compradorPadraoId);
                fornecedor.setCompradorPadrao(compradorPadrao);

                if (fornecedor.getTipoPessoa().equals("F")) {
                    Integer pessoaFisicaId = rs.getInt("pessoafisica");
                    PessoaFisica pessoaFisica = pessoaFisicaRepo.obterPorIdPessoaFisica(pessoaFisicaId);
                    fornecedor.setPessoaFisica(pessoaFisica);
                    fornecedor.setNomeFornecedor(pessoaFisica.getNome());
                } else if (fornecedor.getTipoPessoa().equals("J")) {
                    Integer pessoaJuridicaId = rs.getInt("pessoajuridica");
                    PessoaJuridica pessoaJuridica = pessoaJuridicaRepo.obterPorIdPessoaJuridica(pessoaJuridicaId);
                    fornecedor.setPessoaJuridica(pessoaJuridica);
                    fornecedor.setNomeFornecedor(pessoaJuridica.getNomeFantasia());
                }

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                fornecedor.setEntidadeGestora(entidadeGestora);

                return fornecedor;
            }
        }, entidade);
    }

    public List<Fornecedor> obterTodosFornecedoresPorTermo(Integer entidade, String termo) {

        return jbdcTemplate.query(SELECT_ALL_POR_TERMO, new RowMapper<Fornecedor>() {
            @Override
            public Fornecedor mapRow(ResultSet rs, int rownumber) throws SQLException {

                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setTipoPessoa(rs.getString("tipopessoa"));
                fornecedor.setAtivo(rs.getBoolean("ativo"));
                fornecedor.setPrazoEntrega(rs.getInt("prazoentrega"));
                fornecedor.setPedidoMinimo(rs.getDouble("pedidominimo"));
                if (obterPorIdFornecedorProduto(fornecedor.getId()) != null) {
                    fornecedor.setProdutos(obterPorIdFornecedorProduto(fornecedor.getId()));
                }

                Integer responsavelId = rs.getInt("responsavel");
                if (responsavelId != 0) {
                    PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                    fornecedor.setResponsavel(responsavel);
                }

                Integer compradorPadraoId = rs.getInt("compradorpadrao");
                PessoaFisica compradorPadrao = pessoaFisicaRepo.obterPorIdPessoaFisica(compradorPadraoId);
                fornecedor.setCompradorPadrao(compradorPadrao);

                if (fornecedor.getTipoPessoa().equals("F")) {
                    Integer pessoaFisicaId = rs.getInt("pessoafisica");
                    PessoaFisica pessoaFisica = pessoaFisicaRepo.obterPorIdPessoaFisica(pessoaFisicaId);
                    fornecedor.setPessoaFisica(pessoaFisica);
                    fornecedor.setNomeFornecedor(pessoaFisica.getNome());
                } else if (fornecedor.getTipoPessoa().equals("J")) {
                    Integer pessoaJuridicaId = rs.getInt("pessoajuridica");
                    PessoaJuridica pessoaJuridica = pessoaJuridicaRepo.obterPorIdPessoaJuridica(pessoaJuridicaId);
                    fornecedor.setPessoaJuridica(pessoaJuridica);
                    fornecedor.setNomeFornecedor(pessoaJuridica.getNomeFantasia());
                }

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                fornecedor.setEntidadeGestora(entidadeGestora);

                return fornecedor;
            }
        }, entidade, termo);
    }

    public List<Fornecedor> obterTodosFornecedoresPorProduto(Integer entidade, Integer produto) {

        return jbdcTemplate.query(SELECT_ALL_FORNECEDOR_POR_PRODUTO, new RowMapper<Fornecedor>() {
            @Override
            public Fornecedor mapRow(ResultSet rs, int rownumber) throws SQLException {

                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setTipoPessoa(rs.getString("tipopessoa"));
                fornecedor.setAtivo(rs.getBoolean("ativo"));
                fornecedor.setPrazoEntrega(rs.getInt("prazoentrega"));
                fornecedor.setPedidoMinimo(rs.getDouble("pedidominimo"));
                if (obterPorIdFornecedorProduto(fornecedor.getId()) != null) {
                    fornecedor.setProdutos(obterPorIdFornecedorProduto(fornecedor.getId()));
                }

                Integer responsavelId = rs.getInt("responsavel");
                if (responsavelId != 0) {
                    PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                    fornecedor.setResponsavel(responsavel);
                }

                Integer compradorPadraoId = rs.getInt("compradorpadrao");
                PessoaFisica compradorPadrao = pessoaFisicaRepo.obterPorIdPessoaFisica(compradorPadraoId);
                fornecedor.setCompradorPadrao(compradorPadrao);

                if (fornecedor.getTipoPessoa().equals("F")) {
                    Integer pessoaFisicaId = rs.getInt("pessoafisica");
                    PessoaFisica pessoaFisica = pessoaFisicaRepo.obterPorIdPessoaFisica(pessoaFisicaId);
                    fornecedor.setPessoaFisica(pessoaFisica);
                    fornecedor.setNomeFornecedor(pessoaFisica.getNome());
                } else if (fornecedor.getTipoPessoa().equals("J")) {
                    Integer pessoaJuridicaId = rs.getInt("pessoajuridica");
                    PessoaJuridica pessoaJuridica = pessoaJuridicaRepo.obterPorIdPessoaJuridica(pessoaJuridicaId);
                    fornecedor.setPessoaJuridica(pessoaJuridica);
                    fornecedor.setNomeFornecedor(pessoaJuridica.getNomeFantasia());
                }

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                fornecedor.setEntidadeGestora(entidadeGestora);

                return fornecedor;
            }
        }, produto, entidade);
    }

    public List<Fornecedor> obterFornecedoresPorCriterio(Integer entidade, String criterioBusca) {

        return jbdcTemplate.query(SELECT_CRITERIA, new RowMapper<Fornecedor>() {
            @Override
            public Fornecedor mapRow(ResultSet rs, int rownumber) throws SQLException {

                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setTipoPessoa(rs.getString("tipopessoa"));
                fornecedor.setAtivo(rs.getBoolean("ativo"));
                fornecedor.setPrazoEntrega(rs.getInt("prazoentrega"));
                fornecedor.setPedidoMinimo(rs.getDouble("pedidominimo"));
                if (obterPorIdFornecedorProduto(fornecedor.getId()) != null) {
                    fornecedor.setProdutos(obterPorIdFornecedorProduto(fornecedor.getId()));
                }

                Integer responsavelId = rs.getInt("responsavel");
                if (responsavelId != 0) {
                    PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                    fornecedor.setResponsavel(responsavel);
                }

                Integer compradorPadraoId = rs.getInt("compradorpadrao");
                PessoaFisica compradorPadrao = pessoaFisicaRepo.obterPorIdPessoaFisica(compradorPadraoId);
                fornecedor.setCompradorPadrao(compradorPadrao);

                if (fornecedor.getTipoPessoa().equals("F")) {
                    Integer pessoaFisicaId = rs.getInt("pessoafisica");
                    PessoaFisica pessoaFisica = pessoaFisicaRepo.obterPorIdPessoaFisica(pessoaFisicaId);
                    fornecedor.setPessoaFisica(pessoaFisica);
                    fornecedor.setNomeFornecedor(pessoaFisica.getNome());
                } else if (fornecedor.getTipoPessoa().equals("J")) {
                    Integer pessoaJuridicaId = rs.getInt("pessoajuridica");
                    PessoaJuridica pessoaJuridica = pessoaJuridicaRepo.obterPorIdPessoaJuridica(pessoaJuridicaId);
                    fornecedor.setPessoaJuridica(pessoaJuridica);
                    fornecedor.setNomeFornecedor(pessoaJuridica.getNomeFantasia());
                }

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                fornecedor.setEntidadeGestora(entidadeGestora);

                return fornecedor;
            }
        }, entidade, criterioBusca, entidade, criterioBusca);
    }

    public List<Fornecedor> obterFornecedoresPorDepartamento(Integer entidade, Integer departamento) {

        return jbdcTemplate.query(SELECT_ALL_DEPARTAMENT, new RowMapper<Fornecedor>() {
            @Override
            public Fornecedor mapRow(ResultSet rs, int rownumber) throws SQLException {

                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setTipoPessoa(rs.getString("tipopessoa"));
                fornecedor.setAtivo(rs.getBoolean("ativo"));
                fornecedor.setPrazoEntrega(rs.getInt("prazoentrega"));
                fornecedor.setPedidoMinimo(rs.getDouble("pedidominimo"));
                if (obterPorIdFornecedorProduto(fornecedor.getId()) != null) {
                    fornecedor.setProdutos(obterPorIdFornecedorProduto(fornecedor.getId()));
                }

                Integer responsavelId = rs.getInt("responsavel");
                if (responsavelId != 0) {
                    PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                    fornecedor.setResponsavel(responsavel);
                }

                Integer compradorPadraoId = rs.getInt("compradorpadrao");
                PessoaFisica compradorPadrao = pessoaFisicaRepo.obterPorIdPessoaFisica(compradorPadraoId);
                fornecedor.setCompradorPadrao(compradorPadrao);

                if (fornecedor.getTipoPessoa().equals("F")) {
                    Integer pessoaFisicaId = rs.getInt("pessoafisica");
                    PessoaFisica pessoaFisica = pessoaFisicaRepo.obterPorIdPessoaFisica(pessoaFisicaId);
                    fornecedor.setPessoaFisica(pessoaFisica);
                    fornecedor.setNomeFornecedor(pessoaFisica.getNome());
                } else if (fornecedor.getTipoPessoa().equals("J")) {
                    Integer pessoaJuridicaId = rs.getInt("pessoajuridica");
                    PessoaJuridica pessoaJuridica = pessoaJuridicaRepo.obterPorIdPessoaJuridica(pessoaJuridicaId);
                    fornecedor.setPessoaJuridica(pessoaJuridica);
                    fornecedor.setNomeFornecedor(pessoaJuridica.getNomeFantasia());
                }

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                fornecedor.setEntidadeGestora(entidadeGestora);

                return fornecedor;
            }
        }, entidade, departamento);
    }

    public List<Fornecedor> obterFornecedoresPorSecao(Integer entidade, Integer secao) {

        return jbdcTemplate.query(SELECT_ALL_SECTION, new RowMapper<Fornecedor>() {
            @Override
            public Fornecedor mapRow(ResultSet rs, int rownumber) throws SQLException {

                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setTipoPessoa(rs.getString("tipopessoa"));
                fornecedor.setAtivo(rs.getBoolean("ativo"));
                fornecedor.setPrazoEntrega(rs.getInt("prazoentrega"));
                fornecedor.setPedidoMinimo(rs.getDouble("pedidominimo"));
                if (obterPorIdFornecedorProduto(fornecedor.getId()) != null) {
                    fornecedor.setProdutos(obterPorIdFornecedorProduto(fornecedor.getId()));
                }

                Integer responsavelId = rs.getInt("responsavel");
                if (responsavelId != 0) {
                    PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                    fornecedor.setResponsavel(responsavel);
                }

                Integer compradorPadraoId = rs.getInt("compradorpadrao");
                PessoaFisica compradorPadrao = pessoaFisicaRepo.obterPorIdPessoaFisica(compradorPadraoId);
                fornecedor.setCompradorPadrao(compradorPadrao);

                if (fornecedor.getTipoPessoa().equals("F")) {
                    Integer pessoaFisicaId = rs.getInt("pessoafisica");
                    PessoaFisica pessoaFisica = pessoaFisicaRepo.obterPorIdPessoaFisica(pessoaFisicaId);
                    fornecedor.setPessoaFisica(pessoaFisica);
                    fornecedor.setNomeFornecedor(pessoaFisica.getNome());
                } else if (fornecedor.getTipoPessoa().equals("J")) {
                    Integer pessoaJuridicaId = rs.getInt("pessoajuridica");
                    PessoaJuridica pessoaJuridica = pessoaJuridicaRepo.obterPorIdPessoaJuridica(pessoaJuridicaId);
                    fornecedor.setPessoaJuridica(pessoaJuridica);
                    fornecedor.setNomeFornecedor(pessoaJuridica.getNomeFantasia());
                }

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                fornecedor.setEntidadeGestora(entidadeGestora);

                return fornecedor;
            }
        }, entidade, secao);
    }

    public List<Fornecedor> obterFornecedoresPorCategoria(Integer entidade, Integer categoria) {

        return jbdcTemplate.query(SELECT_ALL_CATEGORY, new RowMapper<Fornecedor>() {
            @Override
            public Fornecedor mapRow(ResultSet rs, int rownumber) throws SQLException {

                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setTipoPessoa(rs.getString("tipopessoa"));
                fornecedor.setAtivo(rs.getBoolean("ativo"));
                fornecedor.setPrazoEntrega(rs.getInt("prazoentrega"));
                fornecedor.setPedidoMinimo(rs.getDouble("pedidominimo"));
                if (obterPorIdFornecedorProduto(fornecedor.getId()) != null) {
                    fornecedor.setProdutos(obterPorIdFornecedorProduto(fornecedor.getId()));
                }

                Integer responsavelId = rs.getInt("responsavel");
                if (responsavelId != 0) {
                    PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                    fornecedor.setResponsavel(responsavel);
                }

                Integer compradorPadraoId = rs.getInt("compradorpadrao");
                PessoaFisica compradorPadrao = pessoaFisicaRepo.obterPorIdPessoaFisica(compradorPadraoId);
                fornecedor.setCompradorPadrao(compradorPadrao);

                if (fornecedor.getTipoPessoa().equals("F")) {
                    Integer pessoaFisicaId = rs.getInt("pessoafisica");
                    PessoaFisica pessoaFisica = pessoaFisicaRepo.obterPorIdPessoaFisica(pessoaFisicaId);
                    fornecedor.setPessoaFisica(pessoaFisica);
                    fornecedor.setNomeFornecedor(pessoaFisica.getNome());
                } else if (fornecedor.getTipoPessoa().equals("J")) {
                    Integer pessoaJuridicaId = rs.getInt("pessoajuridica");
                    PessoaJuridica pessoaJuridica = pessoaJuridicaRepo.obterPorIdPessoaJuridica(pessoaJuridicaId);
                    fornecedor.setPessoaJuridica(pessoaJuridica);
                    fornecedor.setNomeFornecedor(pessoaJuridica.getNomeFantasia());
                }

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                fornecedor.setEntidadeGestora(entidadeGestora);

                return fornecedor;
            }
        }, entidade, categoria);
    }

    public List<Fornecedor> obterFornecedoresPorSubCategoria(Integer entidade, Integer subCategoria) {

        return jbdcTemplate.query(SELECT_ALL_SUBCATEGORY, new RowMapper<Fornecedor>() {
            @Override
            public Fornecedor mapRow(ResultSet rs, int rownumber) throws SQLException {

                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setTipoPessoa(rs.getString("tipopessoa"));
                fornecedor.setAtivo(rs.getBoolean("ativo"));
                fornecedor.setPrazoEntrega(rs.getInt("prazoentrega"));
                fornecedor.setPedidoMinimo(rs.getDouble("pedidominimo"));
                if (obterPorIdFornecedorProduto(fornecedor.getId()) != null) {
                    fornecedor.setProdutos(obterPorIdFornecedorProduto(fornecedor.getId()));
                }

                Integer responsavelId = rs.getInt("responsavel");
                if (responsavelId != 0) {
                    PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                    fornecedor.setResponsavel(responsavel);
                }

                Integer compradorPadraoId = rs.getInt("compradorpadrao");
                PessoaFisica compradorPadrao = pessoaFisicaRepo.obterPorIdPessoaFisica(compradorPadraoId);
                fornecedor.setCompradorPadrao(compradorPadrao);

                if (fornecedor.getTipoPessoa().equals("F")) {
                    Integer pessoaFisicaId = rs.getInt("pessoafisica");
                    PessoaFisica pessoaFisica = pessoaFisicaRepo.obterPorIdPessoaFisica(pessoaFisicaId);
                    fornecedor.setPessoaFisica(pessoaFisica);
                    fornecedor.setNomeFornecedor(pessoaFisica.getNome());
                } else if (fornecedor.getTipoPessoa().equals("J")) {
                    Integer pessoaJuridicaId = rs.getInt("pessoajuridica");
                    PessoaJuridica pessoaJuridica = pessoaJuridicaRepo.obterPorIdPessoaJuridica(pessoaJuridicaId);
                    fornecedor.setPessoaJuridica(pessoaJuridica);
                    fornecedor.setNomeFornecedor(pessoaJuridica.getNomeFantasia());
                }

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                fornecedor.setEntidadeGestora(entidadeGestora);

                return fornecedor;
            }
        }, entidade, subCategoria);
    }

    public List<Produto> obterPorIdFornecedorProduto(Integer fornecedor) {

        return jbdcTemplate.query(SELECT_ALL_PRODUTO_POR_FORNECEDOR, new RowMapper<Produto>() {
            @Override
            public Produto mapRow(ResultSet rs, int rownumber) throws SQLException {

                Integer produtoId = rs.getInt("produto");
                Produto produto = produtoRepo.obterPorIdProduto(produtoId);

                return produto;
            }
        }, fornecedor);

    }

    public Fornecedor salvarFornecedor(Fornecedor fornecedor) {

        KeyHolder holder = new GeneratedKeyHolder();

        if (fornecedor.getResponsavel() != null) {
            if (fornecedor.getTipoPessoa().equals("F")) {
                jbdcTemplate.update(new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(INSERT_F, Statement.RETURN_GENERATED_KEYS);
                        ps.setString(1, fornecedor.getTipoPessoa());
                        ps.setInt(2, fornecedor.getResponsavel().getId());
                        ps.setBoolean(3, fornecedor.getAtivo());
                        ps.setInt(4, fornecedor.getPrazoEntrega());
                        ps.setDouble(5, fornecedor.getPedidoMinimo());
                        ps.setInt(6, fornecedor.getCompradorPadrao().getId());
                        ps.setInt(7, fornecedor.getPessoaFisica().getId());
                        ps.setInt(8, fornecedor.getEntidadeGestora().getId());
                        return ps;
                    }
                }, holder);
            } else if (fornecedor.getTipoPessoa().equals("J")) {
                jbdcTemplate.update(new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(INSERT_J, Statement.RETURN_GENERATED_KEYS);
                        ps.setString(1, fornecedor.getTipoPessoa());
                        ps.setInt(2, fornecedor.getResponsavel().getId());
                        ps.setBoolean(3, fornecedor.getAtivo());
                        ps.setInt(4, fornecedor.getPrazoEntrega());
                        ps.setDouble(5, fornecedor.getPedidoMinimo());
                        ps.setInt(6, fornecedor.getCompradorPadrao().getId());
                        ps.setInt(7, fornecedor.getPessoaJuridica().getId());
                        ps.setInt(8, fornecedor.getEntidadeGestora().getId());
                        return ps;
                    }
                }, holder);
            }
        } else {
            if (fornecedor.getTipoPessoa().equals("F")) {
                jbdcTemplate.update(new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(INSERT_RESPONSAVEL_NULL_AND_F,
                                Statement.RETURN_GENERATED_KEYS);
                        ps.setString(1, fornecedor.getTipoPessoa());
                        ps.setBoolean(2, fornecedor.getAtivo());
                        ps.setInt(3, fornecedor.getPrazoEntrega());
                        ps.setDouble(4, fornecedor.getPedidoMinimo());
                        ps.setInt(5, fornecedor.getCompradorPadrao().getId());
                        ps.setInt(6, fornecedor.getPessoaFisica().getId());
                        ps.setInt(7, fornecedor.getEntidadeGestora().getId());
                        return ps;
                    }
                }, holder);
            } else if (fornecedor.getTipoPessoa().equals("J")) {
                jbdcTemplate.update(new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(INSERT_RESPONSAVEL_NULL_AND_J,
                                Statement.RETURN_GENERATED_KEYS);
                        ps.setString(1, fornecedor.getTipoPessoa());
                        ps.setBoolean(2, fornecedor.getAtivo());
                        ps.setInt(3, fornecedor.getPrazoEntrega());
                        ps.setDouble(4, fornecedor.getPedidoMinimo());
                        ps.setInt(5, fornecedor.getCompradorPadrao().getId());
                        ps.setInt(6, fornecedor.getPessoaJuridica().getId());
                        ps.setInt(7, fornecedor.getEntidadeGestora().getId());
                        return ps;
                    }
                }, holder);
            }
        }

        Integer id = (Integer) holder.getKeys().get("id");
        fornecedor.setId(id);

        return fornecedor;
    }

    public void salvarFornecedorProduto(Integer fornecedor, List<Integer> listaProdutos) {

        for (int i = 0; i < listaProdutos.size(); i++) {
            jbdcTemplate.update(INSERT_PRODUTO, new Object[] { fornecedor, listaProdutos.get(i) });
        }

    }

}
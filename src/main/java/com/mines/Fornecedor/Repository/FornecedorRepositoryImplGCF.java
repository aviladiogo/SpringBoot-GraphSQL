package com.mines.Fornecedor.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class FornecedorRepositoryImplGCF implements FornecedorRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ALL_PRODUTO_POR_FORNECEDOR = " ";
    private static String SELECT_ALL_POR_TERMO = " ";
    private static String SELECT_ALL_FORNECEDOR_POR_PRODUTO = " ";
    private static String SELECT_ONE = " ";
    private static String SELECT_CRITERIA = " ";
    private static String SELECT_ALL_DEPARTAMENT = " ";
    private static String SELECT_ALL_SECTION = " ";
    private static String SELECT_ALL_CATEGORY = " ";
    private static String SELECT_ALL_SUBCATEGORY = " ";

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

    public Fornecedor obterPorIdFornecedor(Integer id) {

        return jbdcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Fornecedor>() {
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
                if(responsavelId!=0){
                    PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                    fornecedor.setResponsavel(responsavel);
                }

                Integer compradorPadraoId = rs.getInt("compradorpadrao");
                PessoaFisica compradorPadrao = pessoaFisicaRepo.obterPorIdPessoaFisica(compradorPadraoId);
                fornecedor.setCompradorPadrao(compradorPadrao);

                if(fornecedor.getTipoPessoa().equals("F")){
                    Integer pessoaFisicaId = rs.getInt("pessoafisica");
                    PessoaFisica pessoaFisica = pessoaFisicaRepo.obterPorIdPessoaFisica(pessoaFisicaId);
                    fornecedor.setPessoaFisica(pessoaFisica);
                    fornecedor.setNomeFornecedor(pessoaFisica.getNome());
                }
                else if(fornecedor.getTipoPessoa().equals("J")){
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
                if(responsavelId!=0){
                    PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                    fornecedor.setResponsavel(responsavel);
                }

                Integer compradorPadraoId = rs.getInt("compradorpadrao");
                PessoaFisica compradorPadrao = pessoaFisicaRepo.obterPorIdPessoaFisica(compradorPadraoId);
                fornecedor.setCompradorPadrao(compradorPadrao);

                if(fornecedor.getTipoPessoa().equals("F")){
                    Integer pessoaFisicaId = rs.getInt("pessoafisica");
                    PessoaFisica pessoaFisica = pessoaFisicaRepo.obterPorIdPessoaFisica(pessoaFisicaId);
                    fornecedor.setPessoaFisica(pessoaFisica);
                    fornecedor.setNomeFornecedor(pessoaFisica.getNome());
                }
                else if(fornecedor.getTipoPessoa().equals("J")){
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
                if(responsavelId!=0){
                    PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                    fornecedor.setResponsavel(responsavel);
                }

                Integer compradorPadraoId = rs.getInt("compradorpadrao");
                PessoaFisica compradorPadrao = pessoaFisicaRepo.obterPorIdPessoaFisica(compradorPadraoId);
                fornecedor.setCompradorPadrao(compradorPadrao);

                if(fornecedor.getTipoPessoa().equals("F")){
                    Integer pessoaFisicaId = rs.getInt("pessoafisica");
                    PessoaFisica pessoaFisica = pessoaFisicaRepo.obterPorIdPessoaFisica(pessoaFisicaId);
                    fornecedor.setPessoaFisica(pessoaFisica);
                    fornecedor.setNomeFornecedor(pessoaFisica.getNome());
                }
                else if(fornecedor.getTipoPessoa().equals("J")){
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
        }, entidade, produto);
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
                if(responsavelId!=0){
                    PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                    fornecedor.setResponsavel(responsavel);
                }

                Integer compradorPadraoId = rs.getInt("compradorpadrao");
                PessoaFisica compradorPadrao = pessoaFisicaRepo.obterPorIdPessoaFisica(compradorPadraoId);
                fornecedor.setCompradorPadrao(compradorPadrao);

                if(fornecedor.getTipoPessoa().equals("F")){
                    Integer pessoaFisicaId = rs.getInt("pessoafisica");
                    PessoaFisica pessoaFisica = pessoaFisicaRepo.obterPorIdPessoaFisica(pessoaFisicaId);
                    fornecedor.setPessoaFisica(pessoaFisica);
                    fornecedor.setNomeFornecedor(pessoaFisica.getNome());
                }
                else if(fornecedor.getTipoPessoa().equals("J")){
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
                if(responsavelId!=0){
                    PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                    fornecedor.setResponsavel(responsavel);
                }

                Integer compradorPadraoId = rs.getInt("compradorpadrao");
                PessoaFisica compradorPadrao = pessoaFisicaRepo.obterPorIdPessoaFisica(compradorPadraoId);
                fornecedor.setCompradorPadrao(compradorPadrao);

                if(fornecedor.getTipoPessoa().equals("F")){
                    Integer pessoaFisicaId = rs.getInt("pessoafisica");
                    PessoaFisica pessoaFisica = pessoaFisicaRepo.obterPorIdPessoaFisica(pessoaFisicaId);
                    fornecedor.setPessoaFisica(pessoaFisica);
                    fornecedor.setNomeFornecedor(pessoaFisica.getNome());
                }
                else if(fornecedor.getTipoPessoa().equals("J")){
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
                if(responsavelId!=0){
                    PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                    fornecedor.setResponsavel(responsavel);
                }

                Integer compradorPadraoId = rs.getInt("compradorpadrao");
                PessoaFisica compradorPadrao = pessoaFisicaRepo.obterPorIdPessoaFisica(compradorPadraoId);
                fornecedor.setCompradorPadrao(compradorPadrao);

                if(fornecedor.getTipoPessoa().equals("F")){
                    Integer pessoaFisicaId = rs.getInt("pessoafisica");
                    PessoaFisica pessoaFisica = pessoaFisicaRepo.obterPorIdPessoaFisica(pessoaFisicaId);
                    fornecedor.setPessoaFisica(pessoaFisica);
                    fornecedor.setNomeFornecedor(pessoaFisica.getNome());
                }
                else if(fornecedor.getTipoPessoa().equals("J")){
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
                if(responsavelId!=0){
                    PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                    fornecedor.setResponsavel(responsavel);
                }

                Integer compradorPadraoId = rs.getInt("compradorpadrao");
                PessoaFisica compradorPadrao = pessoaFisicaRepo.obterPorIdPessoaFisica(compradorPadraoId);
                fornecedor.setCompradorPadrao(compradorPadrao);

                if(fornecedor.getTipoPessoa().equals("F")){
                    Integer pessoaFisicaId = rs.getInt("pessoafisica");
                    PessoaFisica pessoaFisica = pessoaFisicaRepo.obterPorIdPessoaFisica(pessoaFisicaId);
                    fornecedor.setPessoaFisica(pessoaFisica);
                    fornecedor.setNomeFornecedor(pessoaFisica.getNome());
                }
                else if(fornecedor.getTipoPessoa().equals("J")){
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
                if(responsavelId!=0){
                    PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                    fornecedor.setResponsavel(responsavel);
                }

                Integer compradorPadraoId = rs.getInt("compradorpadrao");
                PessoaFisica compradorPadrao = pessoaFisicaRepo.obterPorIdPessoaFisica(compradorPadraoId);
                fornecedor.setCompradorPadrao(compradorPadrao);

                if(fornecedor.getTipoPessoa().equals("F")){
                    Integer pessoaFisicaId = rs.getInt("pessoafisica");
                    PessoaFisica pessoaFisica = pessoaFisicaRepo.obterPorIdPessoaFisica(pessoaFisicaId);
                    fornecedor.setPessoaFisica(pessoaFisica);
                    fornecedor.setNomeFornecedor(pessoaFisica.getNome());
                }
                else if(fornecedor.getTipoPessoa().equals("J")){
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

}
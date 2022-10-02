package com.mines.Produto.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.ClassificacaoMercadologica.Model.SubCategoria;
import com.mines.ClassificacaoMercadologica.Repository.SubCategoriaRepository;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.Fornecedor.Model.Fornecedor;
import com.mines.Fornecedor.Repository.FornecedorRepository;
import com.mines.Produto.Model.Apresentacao;
import com.mines.Produto.Model.Embalagem;
import com.mines.Produto.Model.Marca;
import com.mines.Produto.Model.Produto;
import com.mines.Produto.Model.Unidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoRepositoryImplGCF implements ProdutoRepositoryGCF{
    
    private static String SELECT_ALL = " ";
    private static String SELECT_ALL_DISTRIBUTOR = " ";
    private static String SELECT_ONE = " ";

    @Autowired
    private JdbcTemplate jbdcTemplate;

    @Autowired
    private SubCategoriaRepository subCategoriaRepo;

    @Autowired
    private UnidadeRepository unidadeRepo;

    @Autowired
    private FornecedorRepository fornecedorRepo;

    @Autowired
    private MarcaRepository marcaRepo;

    @Autowired
    private EmbalagemRepository embalagemRepo;

    @Autowired
    private ApresentacaoRepository apresentacaoRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    public Produto obterPorIdProduto(Integer id) {

        return jbdcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Produto>() {
            @Override
            public Produto mapRow(ResultSet rs, int rownumber) throws SQLException {

                Produto produto = new Produto();

                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setEan1(rs.getString("ean1"));
                produto.setEan2(rs.getString("ean2"));
                produto.setCodigo_cd(rs.getString("codigo_cd"));

                Integer subCategoriaId = rs.getInt("subcategoria");
                SubCategoria subCategoria = subCategoriaRepo.obterPorIdSubCategoria(subCategoriaId);
                produto.setSubCategoria(subCategoria);

                Integer unidadeId = rs.getInt("unidade");
                Unidade unidade = unidadeRepo.obterPorIdUnidade(unidadeId);
                produto.setUnidade(unidade);

                Integer fabricanteId = rs.getInt("fabricante");
                Fornecedor fabricante = fornecedorRepo.obterPorIdFornecedor(fabricanteId);
                produto.setFabricante(fabricante);

                Integer marcaId = rs.getInt("marca");
                Marca marca = marcaRepo.obterPorIdMarca(marcaId);
                produto.setMarca(marca);

                Integer embalagemId = rs.getInt("embalagem");
                Embalagem embalagem = embalagemRepo.obterPorIdEmbalagem(embalagemId);
                produto.setEmbalagem(embalagem);

                Integer apresentacaoId = rs.getInt("apresentacao");
                Apresentacao apresentacao = apresentacaoRepo.obterPorIdApresentacao(apresentacaoId);
                produto.setApresentacao(apresentacao);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                produto.setEntidadeGestora(entidadeGestora);
                
                return produto;
            }
        });

    }

    public List<Produto> obterTodosProdutos(Integer entidade) {

        return jbdcTemplate.query(SELECT_ALL, new RowMapper<Produto>() {
            @Override
            public Produto mapRow(ResultSet rs, int rownumber) throws SQLException {

                Produto produto = new Produto();

                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setEan1(rs.getString("ean1"));
                produto.setEan2(rs.getString("ean2"));
                produto.setCodigo_cd(rs.getString("codigo_cd"));

                Integer subCategoriaId = rs.getInt("subcategoria");
                SubCategoria subCategoria = subCategoriaRepo.obterPorIdSubCategoria(subCategoriaId);
                produto.setSubCategoria(subCategoria);

                Integer unidadeId = rs.getInt("unidade");
                Unidade unidade = unidadeRepo.obterPorIdUnidade(unidadeId);
                produto.setUnidade(unidade);

                Integer fabricanteId = rs.getInt("fabricante");
                Fornecedor fabricante = fornecedorRepo.obterPorIdFornecedor(fabricanteId);
                produto.setFabricante(fabricante);

                Integer marcaId = rs.getInt("marca");
                Marca marca = marcaRepo.obterPorIdMarca(marcaId);
                produto.setMarca(marca);

                Integer embalagemId = rs.getInt("embalagem");
                Embalagem embalagem = embalagemRepo.obterPorIdEmbalagem(embalagemId);
                produto.setEmbalagem(embalagem);

                Integer apresentacaoId = rs.getInt("apresentacao");
                Apresentacao apresentacao = apresentacaoRepo.obterPorIdApresentacao(apresentacaoId);
                produto.setApresentacao(apresentacao);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                produto.setEntidadeGestora(entidadeGestora);
                
                return produto;
            }
        }, entidade);
    }

    public List<Produto> obterProdutosPorFornecedores(Integer entidade, List<Integer> fornecedores) {

        String listaFornecedores = "";
        listaFornecedores = fornecedores.toString().replace("[", "").replace("]", "");
        String SELECT = SELECT_ALL_DISTRIBUTOR + "(" + listaFornecedores +")";
        return jbdcTemplate.query(SELECT, new RowMapper<Produto>() {
            @Override
            public Produto mapRow(ResultSet rs, int rownumber) throws SQLException {

                Produto produto = new Produto();

                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setEan1(rs.getString("ean1"));
                produto.setEan2(rs.getString("ean2"));
                produto.setCodigo_cd(rs.getString("codigo_cd"));

                Integer subCategoriaId = rs.getInt("subcategoria");
                SubCategoria subCategoria = subCategoriaRepo.obterPorIdSubCategoria(subCategoriaId);
                produto.setSubCategoria(subCategoria);

                Integer unidadeId = rs.getInt("unidade");
                Unidade unidade = unidadeRepo.obterPorIdUnidade(unidadeId);
                produto.setUnidade(unidade);

                Integer fabricanteId = rs.getInt("fabricante");
                Fornecedor fabricante = fornecedorRepo.obterPorIdFornecedor(fabricanteId);
                produto.setFabricante(fabricante);

                Integer marcaId = rs.getInt("marca");
                Marca marca = marcaRepo.obterPorIdMarca(marcaId);
                produto.setMarca(marca);

                Integer embalagemId = rs.getInt("embalagem");
                Embalagem embalagem = embalagemRepo.obterPorIdEmbalagem(embalagemId);
                produto.setEmbalagem(embalagem);

                Integer apresentacaoId = rs.getInt("apresentacao");
                Apresentacao apresentacao = apresentacaoRepo.obterPorIdApresentacao(apresentacaoId);
                produto.setApresentacao(apresentacao);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                produto.setEntidadeGestora(entidadeGestora);
                
                return produto;
            }
        },entidade);
    }

}

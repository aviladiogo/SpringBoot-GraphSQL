package com.mines.Produto.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository{
    
    private static String SELECT_ALL = " select * from dx_produto where entidadegestora = ? "
            + " order by descricao ";
    private static String SELECT_ALL_DISTRIBUTOR = " select distinct dx_p.*, dx_f.tipopessoa from dx_produto dx_p "
            + "inner join dx_fornecedor dx_f on dx_f.id = dx_p.fabricante "
            + "where dx_f.entidadegestora = ? and dx_p.fabricante in  ";
    private static String SELECT_ONE = " select * from dx_produto where id = ? ";
    private static String INSERT = " insert into dx_produto (id, descricao, ean1, ean2, codigo_cd, "
            + " subcategoria, unidade, fabricante, marca, embalagem, apresentacao, entidadegestora) "
            + " values (nextval('dx_produto_id_seq'),?,?,?,?,?,?,?,?,?,?,?) ";
    private static String UPDATE = " update dx_produto set descricao = ?, ean1 = ?, ean2 = ?, "
            + " codigo_cd = ? where id = ? ";
    private static String DELETE = " delete from dx_produto where id = ? ";

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

    public Produto atualizarProduto(Produto produto) {
        
        jbdcTemplate.update(UPDATE, new Object[] {produto.getDescricao(), produto.getEan1(), produto.getEan2(),
            produto.getCodigo_cd(), produto.getId()});

        return produto;
    }

    public Boolean deletarProduto(Produto produto) {

        Boolean ret = false;

        Integer rows = jbdcTemplate.update(DELETE, new Object[] {produto.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;

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

    public Produto salvarProduto(Produto produto) {

        KeyHolder holder = new GeneratedKeyHolder();
        
        jbdcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, produto.getDescricao());
                ps.setString(2, produto.getEan1());
                ps.setString(3, produto.getEan2());
                ps.setString(4, produto.getCodigo_cd());
                ps.setInt(5, produto.getSubCategoria().getId());
                ps.setInt(6, produto.getUnidade().getId());
                ps.setInt(7, produto.getFabricante().getId());
                ps.setInt(8, produto.getMarca().getId());
                ps.setInt(9, produto.getEmbalagem().getId());
                ps.setInt(10, produto.getApresentacao().getId());
                ps.setInt(11, produto.getEntidadeGestora().getId());

                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        produto.setId(id);
            
        return produto;
    }

}

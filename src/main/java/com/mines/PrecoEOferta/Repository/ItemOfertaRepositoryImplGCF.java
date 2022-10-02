package com.mines.PrecoEOferta.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.Fornecedor.Model.Fornecedor;
import com.mines.Fornecedor.Repository.FornecedorRepository;
import com.mines.PrecoEOferta.Model.ItemOferta;
import com.mines.Produto.Model.Produto;
import com.mines.Produto.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ItemOfertaRepositoryImplGCF implements ItemOfertaRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProdutoRepository produtoRepo;

    @Autowired
    private FornecedorRepository fornecedorRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public ItemOferta obterPorIdItemOferta(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<ItemOferta>() {
            @Override
            public ItemOferta mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                ItemOferta itemOferta = new ItemOferta();

                itemOferta.setId(rs.getInt("id"));
                itemOferta.setDescricao(rs.getString("descricao"));

                Integer produtoId = rs.getInt("produto");
                Produto produto = produtoRepo.obterPorIdProduto(produtoId);
                itemOferta.setProduto(produto);

                Integer fabricanteId = rs.getInt("fabricante");
                Fornecedor fabricante = fornecedorRepo.obterPorIdFornecedor(fabricanteId);
                itemOferta.setFabricante(fabricante);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                itemOferta.setEntidadeGestora(entidadeGestora);

                return itemOferta;
            }
        });

    }
    
    public List<ItemOferta> obterTodosItensOferta(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<ItemOferta>() {
            @Override
            public ItemOferta mapRow(ResultSet rs, int rownumber) throws SQLException {

                ItemOferta itemOferta = new ItemOferta();

                itemOferta.setId(rs.getInt("id"));
                itemOferta.setDescricao(rs.getString("descricao"));

                Integer produtoId = rs.getInt("produto");
                Produto produto = produtoRepo.obterPorIdProduto(produtoId);
                itemOferta.setProduto(produto);

                Integer fabricanteId = rs.getInt("fabricante");
                Fornecedor fabricante = fornecedorRepo.obterPorIdFornecedor(fabricanteId);
                itemOferta.setFabricante(fabricante);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                itemOferta.setEntidadeGestora(entidadeGestora);

                return itemOferta;
            }
        }, entidade);

    }
    
}

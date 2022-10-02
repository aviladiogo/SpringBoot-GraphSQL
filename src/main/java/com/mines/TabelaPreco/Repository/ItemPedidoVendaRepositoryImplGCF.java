package com.mines.TabelaPreco.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.Produto.Model.Produto;
import com.mines.Produto.Repository.ProdutoRepository;
import com.mines.TabelaPreco.Model.ItemPedidoVenda;
import com.mines.TabelaPreco.Model.TabelaPreco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ItemPedidoVendaRepositoryImplGCF implements ItemPedidoVendaRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ALL_TABELA_PRECO = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TabelaPrecoRepository tabelaPrecoRepo;

    @Autowired
    private ProdutoRepository produtoRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public ItemPedidoVenda obterPorIdItemPedidoVenda(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<ItemPedidoVenda>() {
            @Override
            public ItemPedidoVenda mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                ItemPedidoVenda itemPedidoVenda = new ItemPedidoVenda();

                itemPedidoVenda.setId(rs.getInt("id"));
                itemPedidoVenda.setValorUnitario(rs.getDouble("valorunitario"));
                itemPedidoVenda.setQtdeMinima(rs.getInt("qtdeminima"));

                Integer tabelaPrecoId = rs.getInt("tabelapreco");
                TabelaPreco tabelaPreco = tabelaPrecoRepo.obterPorIdTabelaPreco(tabelaPrecoId);
                itemPedidoVenda.setTabelaPreco(tabelaPreco);

                Integer produtoId = rs.getInt("produto");
                Produto produto = produtoRepo.obterPorIdProduto(produtoId);
                itemPedidoVenda.setProduto(produto);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                itemPedidoVenda.setEntidadeGestora(entidadeGestora);

                return itemPedidoVenda;
            }
        });

    }
    
    public List<ItemPedidoVenda> obterTodosItensPedidoVenda(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<ItemPedidoVenda>() {
            @Override
            public ItemPedidoVenda mapRow(ResultSet rs, int rownumber) throws SQLException {

                ItemPedidoVenda itemPedidoVenda = new ItemPedidoVenda();

                itemPedidoVenda.setId(rs.getInt("id"));
                itemPedidoVenda.setValorUnitario(rs.getDouble("valorunitario"));
                itemPedidoVenda.setQtdeMinima(rs.getInt("qtdeminima"));

                Integer tabelaPrecoId = rs.getInt("tabelapreco");
                TabelaPreco tabelaPreco = tabelaPrecoRepo.obterPorIdTabelaPreco(tabelaPrecoId);
                itemPedidoVenda.setTabelaPreco(tabelaPreco);

                Integer produtoId = rs.getInt("produto");
                Produto produto = produtoRepo.obterPorIdProduto(produtoId);
                itemPedidoVenda.setProduto(produto);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                itemPedidoVenda.setEntidadeGestora(entidadeGestora);

                return itemPedidoVenda;
            }
        }, entidade);

    }

    public List<ItemPedidoVenda> obterTodosItensPedidoVendaPorTabelaPreco(Integer entidade, Integer tabelaPreco) {
        
        return jdbcTemplate.query(SELECT_ALL_TABELA_PRECO, new RowMapper<ItemPedidoVenda>() {
            @Override
            public ItemPedidoVenda mapRow(ResultSet rs, int rownumber) throws SQLException {

                ItemPedidoVenda itemPedidoVenda = new ItemPedidoVenda();

                itemPedidoVenda.setId(rs.getInt("id"));
                itemPedidoVenda.setValorUnitario(rs.getDouble("valorunitario"));
                itemPedidoVenda.setQtdeMinima(rs.getInt("qtdeminima"));

                Integer tabelaPrecoId = rs.getInt("tabelapreco");
                TabelaPreco tabelaPreco = tabelaPrecoRepo.obterPorIdTabelaPreco(tabelaPrecoId);
                itemPedidoVenda.setTabelaPreco(tabelaPreco);

                Integer produtoId = rs.getInt("produto");
                Produto produto = produtoRepo.obterPorIdProduto(produtoId);
                itemPedidoVenda.setProduto(produto);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                itemPedidoVenda.setEntidadeGestora(entidadeGestora);

                return itemPedidoVenda;
            }
        }, entidade, tabelaPreco);

    }
    
}
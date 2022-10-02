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
import com.mines.Produto.Model.Produto;
import com.mines.Produto.Repository.ProdutoRepository;
import com.mines.TabelaPreco.Model.ItemPedidoVenda;
import com.mines.TabelaPreco.Model.TabelaPreco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ItemPedidoVendaRepositoryImpl implements ItemPedidoVendaRepository {

    private static String SELECT_ALL = " select * from dx_itempedidovenda where entidadegestora = ? ";
    private static String SELECT_ALL_TABELA_PRECO = " select * from dx_itempedidovenda where entidadegestora = ? "
            + " and tabelapreco = ?";
    private static String SELECT_ONE = " select * from dx_itempedidovenda where id = ? ";
    private static String INSERT = " insert into dx_itempedidovenda (id, tabelapreco, produto, valorunitario, "
            + " qtdeminima, entidadegestora)"
            + " values (nextval('dx_itempedidovenda_id_seq'),?,?,?,?,?) ";
    private static String UPDATE = " update dx_itempedidovenda set valorunitario = ?, qtdeminima = ?"
            + "where id = ? ";
    private static String DELETE = " delete from dx_itempedidovenda where id = ? ";
    
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
    
    public ItemPedidoVenda atualizarItemPedidoVenda(ItemPedidoVenda itemPedidoVenda) {
       
        jdbcTemplate.update(UPDATE, new Object[] {itemPedidoVenda.getValorUnitario(), 
            itemPedidoVenda.getQtdeMinima(), itemPedidoVenda.getId()});

        return itemPedidoVenda;

    }

    public Boolean deletarItemPedidoVenda(ItemPedidoVenda itemPedidoVenda) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {itemPedidoVenda.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
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

    public ItemPedidoVenda salvarItemPedidoVenda(ItemPedidoVenda itemPedidoVenda) {
        
        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, itemPedidoVenda.getTabelaPreco().getId());
                ps.setInt(2, itemPedidoVenda.getProduto().getId());
                ps.setDouble(3, itemPedidoVenda.getValorUnitario());
                ps.setInt(4, itemPedidoVenda.getQtdeMinima());
                ps.setInt(5, itemPedidoVenda.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        itemPedidoVenda.setId(id);
            
        return itemPedidoVenda;

    }
    
}
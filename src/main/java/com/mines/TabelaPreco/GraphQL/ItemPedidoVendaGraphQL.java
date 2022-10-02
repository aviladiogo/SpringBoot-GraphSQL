package com.mines.TabelaPreco.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.Produto.Model.Produto;
import com.mines.TabelaPreco.Model.ItemPedidoVenda;
import com.mines.TabelaPreco.Model.ItemPedidoVendaInput;
import com.mines.TabelaPreco.Model.TabelaPreco;
import com.mines.TabelaPreco.Repository.ItemPedidoVendaRepository;
import com.mines.TabelaPreco.Service.ItemPedidoVendaService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemPedidoVendaGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private ItemPedidoVendaRepository itemPedidoVendaRepo;

    @Autowired
    private ItemPedidoVendaService itemPedidoVendaServ;

    public ItemPedidoVenda itemPedidoVenda(Integer id) {

        ItemPedidoVenda itemPedidoVenda = null;
        try {
            itemPedidoVenda = itemPedidoVendaServ.obterPorIdItemPedidoVenda(id);
        } catch (Exception e) {
            return itemPedidoVenda;
        }
        return itemPedidoVenda;
    }

    public List<ItemPedidoVenda> itensPedidoVenda(Integer entidade) {

        List<ItemPedidoVenda> lista = itemPedidoVendaServ.obterTodosItensPedidoVenda(entidade);
        return lista;

    }

    public List<ItemPedidoVenda> itensPedidoVendaPorTabelaPreco(Integer entidade, Integer tabelaPreco) {

        List<ItemPedidoVenda> lista = itemPedidoVendaServ.obterTodosItensPedidoVendaPorTabelaPreco(entidade,
                tabelaPreco);
        return lista;

    }

    public Boolean deletarItemPedidoVenda(Integer id) {

        Boolean ret = false;

        try {
            ItemPedidoVenda itemPedidoVenda = itemPedidoVendaRepo.obterPorIdItemPedidoVenda(id);
            ret = itemPedidoVendaRepo.deletarItemPedidoVenda(itemPedidoVenda);
        } catch (Exception e) {
            return ret;
        }

        return ret;

    }

    public ItemPedidoVenda salvarItemPedidoVenda(ItemPedidoVendaInput itemPedidoVendaInput) throws SQLException {

        ItemPedidoVenda itemPedidoVenda = new ItemPedidoVenda();

        TabelaPreco tabelaPreco = new TabelaPreco();
        tabelaPreco.setId(itemPedidoVendaInput.getTabelaPreco());
        itemPedidoVenda.setTabelaPreco(tabelaPreco);

        Produto produto = new Produto();
        produto.setId(itemPedidoVendaInput.getProduto());
        itemPedidoVenda.setProduto(produto);

        itemPedidoVenda.setValorUnitario(itemPedidoVendaInput.getValorUnitario());
        itemPedidoVenda.setQtdeMinima(itemPedidoVendaInput.getQtdeMinima());

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(itemPedidoVendaInput.getEntidadeGestora());
        itemPedidoVenda.setEntidadeGestora(entidadeGestora);

        try {
            if (itemPedidoVendaInput.getId() == 0) {
                itemPedidoVenda = itemPedidoVendaRepo.salvarItemPedidoVenda(itemPedidoVenda);
            } else {
                itemPedidoVenda.setId(itemPedidoVendaInput.getId());
                itemPedidoVenda = itemPedidoVendaRepo.atualizarItemPedidoVenda(itemPedidoVenda);
            }
        } catch (Exception e) {
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return itemPedidoVenda;

    }

}

package com.mines.TabelaPreco.Repository;

import java.util.List;
import com.mines.TabelaPreco.Model.ItemPedidoVenda;

public interface ItemPedidoVendaRepository {

    List<ItemPedidoVenda> obterTodosItensPedidoVenda(Integer entidade);

    List<ItemPedidoVenda> obterTodosItensPedidoVendaPorTabelaPreco(Integer entidade, Integer tabelaPreco);
    
    ItemPedidoVenda obterPorIdItemPedidoVenda(Integer id);

    ItemPedidoVenda salvarItemPedidoVenda(ItemPedidoVenda itemPedidoVenda);

    ItemPedidoVenda atualizarItemPedidoVenda(ItemPedidoVenda itemPedidoVenda);

    Boolean deletarItemPedidoVenda(ItemPedidoVenda itemPedidoVenda);
    
}

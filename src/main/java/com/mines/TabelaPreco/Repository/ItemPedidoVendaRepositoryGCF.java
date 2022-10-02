package com.mines.TabelaPreco.Repository;

import java.util.List;
import com.mines.TabelaPreco.Model.ItemPedidoVenda;

public interface ItemPedidoVendaRepositoryGCF {

    List<ItemPedidoVenda> obterTodosItensPedidoVenda(Integer entidade);

    List<ItemPedidoVenda> obterTodosItensPedidoVendaPorTabelaPreco(Integer entidade, Integer tabelaPreco);
    
    ItemPedidoVenda obterPorIdItemPedidoVenda(Integer id);
    
}
package com.mines.TabelaPreco.Service;

import java.util.List;
import com.mines.TabelaPreco.Model.ItemPedidoVenda;

public interface ItemPedidoVendaService {

    List<ItemPedidoVenda> obterTodosItensPedidoVenda(Integer entidade);

    List<ItemPedidoVenda> obterTodosItensPedidoVendaPorTabelaPreco(Integer entidade, Integer tabelaPreco);
    
    ItemPedidoVenda obterPorIdItemPedidoVenda(Integer id);
    
}
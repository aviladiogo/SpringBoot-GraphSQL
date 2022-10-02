package com.mines.CurvaABCZ.Repository;

import java.util.List;
import com.mines.CurvaABCZ.Model.ItemClasseProduto;

public interface ItemClasseProdutoRepository {
    
    List<ItemClasseProduto> obterTodosItemClasseProduto();
    
    List<ItemClasseProduto> obterPorIdItemClasseProduto(Integer classeProdutoId);

    ItemClasseProduto salvarItemClasseProduto(ItemClasseProduto itemClasseProduto);

    Boolean deletarItemClasseProduto(Integer classeProdutoId);
}

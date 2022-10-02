package com.mines.CurvaABCZ.Service;

import java.util.List;
import com.mines.CurvaABCZ.Model.ItemClasseProduto;

public interface ItemClasseProdutoService {
    
    List<ItemClasseProduto> obterTodosItemClasseProduto();
    
    List<ItemClasseProduto> obterPorIdItemClasseProduto(Integer classeProdutoId);

}

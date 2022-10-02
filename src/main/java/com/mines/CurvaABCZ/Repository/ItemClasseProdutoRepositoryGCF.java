package com.mines.CurvaABCZ.Repository;

import java.util.List;
import com.mines.CurvaABCZ.Model.ItemClasseProduto;

public interface ItemClasseProdutoRepositoryGCF {
    
    List<ItemClasseProduto> obterTodosItemClasseProduto();
    
    List<ItemClasseProduto> obterPorIdItemClasseProduto(Integer classeProdutoId);

}

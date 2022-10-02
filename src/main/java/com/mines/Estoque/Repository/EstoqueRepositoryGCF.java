package com.mines.Estoque.Repository;

import java.util.List;
import com.mines.Estoque.Model.Estoque;

public interface EstoqueRepositoryGCF {

    List<Estoque> obterTodosEstoques(Integer entidade);
    
    Estoque obterPorIdEstoque(Integer id);
    
}

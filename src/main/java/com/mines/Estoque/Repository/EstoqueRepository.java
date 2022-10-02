package com.mines.Estoque.Repository;

import java.util.List;
import com.mines.Estoque.Model.Estoque;

public interface EstoqueRepository {

    List<Estoque> obterTodosEstoques(Integer entidade);
    
    Estoque obterPorIdEstoque(Integer id);

    Estoque salvarEstoque(Estoque estoque);

    Estoque atualizarEstoque(Estoque estoque);

    Boolean deletarEstoque(Estoque estoque);
    
}

package com.mines.Estoque.Service;

import java.util.List;
import com.mines.Estoque.Model.Estoque;

public interface EstoqueService {

    List<Estoque> obterTodosEstoques(Integer entidade);
    
    Estoque obterPorIdEstoque(Integer id);
    
}
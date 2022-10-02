package com.mines.Fornecedor.Service;

import java.util.List;
import com.mines.Fornecedor.Model.FornecedorFilial;

public interface FornecedorFilialService {

    List<FornecedorFilial> obterTodosFornecedoresFilial(Integer entidade);

    FornecedorFilial obterPorIdFornecedorFilial(Integer fornecedor, Integer filial);
    
}

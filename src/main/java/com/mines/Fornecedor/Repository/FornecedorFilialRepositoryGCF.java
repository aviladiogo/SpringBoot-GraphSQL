package com.mines.Fornecedor.Repository;

import java.util.List;
import com.mines.Fornecedor.Model.FornecedorFilial;

public interface FornecedorFilialRepositoryGCF {

    List<FornecedorFilial> obterTodosFornecedoresFilial(Integer entidade);

    FornecedorFilial obterPorIdFornecedorFilial(Integer fornecedor, Integer filial);
    
}

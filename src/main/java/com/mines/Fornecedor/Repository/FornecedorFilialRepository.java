package com.mines.Fornecedor.Repository;

import java.util.List;
import com.mines.Fornecedor.Model.FornecedorFilial;

public interface FornecedorFilialRepository {
    
    List<FornecedorFilial> obterTodosFornecedoresFilial(Integer entidade);
    
    FornecedorFilial obterPorIdFornecedorFilial(Integer fornecedor, Integer filial);

    FornecedorFilial salvarFornecedorFilial(FornecedorFilial fornecedorFilial);

    FornecedorFilial atualizarFornecedorFilial(FornecedorFilial fornecedorFilial);

    Boolean deletarFornecedorFilial(FornecedorFilial fornecedorFilial);
}

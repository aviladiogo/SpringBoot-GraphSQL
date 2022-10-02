package com.mines.Produto.Repository;

import java.util.List;
import com.mines.Produto.Model.Unidade;

public interface UnidadeRepositoryGCF {
    
    List<Unidade> obterTodosUnidades(Integer entidade);
    
    Unidade obterPorIdUnidade(Integer id);

}


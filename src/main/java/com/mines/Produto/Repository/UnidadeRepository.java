package com.mines.Produto.Repository;

import java.util.List;
import com.mines.Produto.Model.Unidade;

public interface UnidadeRepository {
    
    List<Unidade> obterTodosUnidades(Integer entidade);
    
    Unidade obterPorIdUnidade(Integer id);

    Unidade salvarUnidade(Unidade unidade);

    Unidade atualizarUnidade(Unidade unidade);

    Boolean deletarUnidade(Unidade unidade);
}

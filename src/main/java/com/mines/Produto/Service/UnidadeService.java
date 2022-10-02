package com.mines.Produto.Service;

import java.util.List;
import com.mines.Produto.Model.Unidade;

public interface UnidadeService {
    
    List<Unidade> obterTodosUnidades(Integer entidade);
    
    Unidade obterPorIdUnidade(Integer id);

}

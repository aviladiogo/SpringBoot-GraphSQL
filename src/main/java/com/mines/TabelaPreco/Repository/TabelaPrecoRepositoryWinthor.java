package com.mines.TabelaPreco.Repository;

import java.util.List;
import com.mines.TabelaPreco.Model.TabelaPreco;

public interface TabelaPrecoRepositoryWinthor {

    List<TabelaPreco> obterTodosTabelasPreco(Integer entidade);
    
    TabelaPreco obterPorIdTabelaPreco(Integer id);
}

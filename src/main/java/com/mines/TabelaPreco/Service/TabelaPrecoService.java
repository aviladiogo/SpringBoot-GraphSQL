package com.mines.TabelaPreco.Service;

import java.util.List;
import com.mines.TabelaPreco.Model.TabelaPreco;

public interface TabelaPrecoService {

    List<TabelaPreco> obterTodosTabelasPreco(Integer entidade);
    
    TabelaPreco obterPorIdTabelaPreco(Integer id);
}

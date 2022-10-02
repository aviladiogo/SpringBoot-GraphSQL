package com.mines.TabelaPreco.Repository;

import java.util.List;
import com.mines.TabelaPreco.Model.TabelaPreco;

public interface TabelaPrecoRepository {

    List<TabelaPreco> obterTodosTabelasPreco(Integer entidade);
    
    TabelaPreco obterPorIdTabelaPreco(Integer id);

    TabelaPreco salvarTabelaPreco(TabelaPreco tabelaPreco);

    void salvarTabelaPrecoFornecedor(Integer tabelaPreco, List<Integer> listaFornecedores);

    TabelaPreco atualizarTabelaPreco(TabelaPreco tabelaPreco);

    Boolean deletarTabelaPreco(TabelaPreco tabelaPreco);

    Boolean deletarTabelaPrecoFornecedor(TabelaPreco tabelaPreco);
}

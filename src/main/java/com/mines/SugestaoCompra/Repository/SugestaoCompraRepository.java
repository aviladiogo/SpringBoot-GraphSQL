package com.mines.SugestaoCompra.Repository;

import java.util.List;
import com.mines.SugestaoCompra.Model.FiltroSugestaoCompraInput;
import com.mines.SugestaoCompra.Model.SugestaoCompra;

public interface SugestaoCompraRepository {

    List<SugestaoCompra> obterTodosSugestaoCompra(Integer entidade);


    List<SugestaoCompra> obterTodosSugestaoCompraPorFiltro(Integer entidade,
            FiltroSugestaoCompraInput filtroSugestaoCompraInput);
    
    SugestaoCompra obterPorIdSugestaoCompra(Integer id);

    SugestaoCompra salvarSugestaoCompra(SugestaoCompra sugestaoCompra);

    void salvarSugestaoCompraFornecedor(Integer sugestaoCompra, List<Integer> listaFornecedores);

    SugestaoCompra atualizarSugestaoCompra(SugestaoCompra sugestaoCompra);

    Boolean deletarSugestaoCompra(SugestaoCompra sugestaoCompra);

    Boolean deletarSugestaoCompraFornecedor(SugestaoCompra sugestaoCompra);
}

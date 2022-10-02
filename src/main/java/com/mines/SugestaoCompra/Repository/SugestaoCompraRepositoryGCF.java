package com.mines.SugestaoCompra.Repository;

import java.util.List;
import com.mines.SugestaoCompra.Model.FiltroSugestaoCompraInput;
import com.mines.SugestaoCompra.Model.SugestaoCompra;

public interface SugestaoCompraRepositoryGCF {

    List<SugestaoCompra> obterTodosSugestaoCompra(Integer entidade);


    List<SugestaoCompra> obterTodosSugestaoCompraPorFiltro(Integer entidade,
            FiltroSugestaoCompraInput filtroSugestaoCompraInput);
    
    SugestaoCompra obterPorIdSugestaoCompra(Integer id);

}

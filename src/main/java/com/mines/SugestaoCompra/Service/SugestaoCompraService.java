package com.mines.SugestaoCompra.Service;

import java.util.List;
import com.mines.SugestaoCompra.Model.FiltroSugestaoCompraInput;
import com.mines.SugestaoCompra.Model.SugestaoCompra;

public interface SugestaoCompraService {

    List<SugestaoCompra> obterTodosSugestaoCompra(Integer entidade);


    List<SugestaoCompra> obterTodosSugestaoCompraPorFiltro(Integer entidade,
            FiltroSugestaoCompraInput filtroSugestaoCompraInput);
    
    SugestaoCompra obterPorIdSugestaoCompra(Integer id);

}

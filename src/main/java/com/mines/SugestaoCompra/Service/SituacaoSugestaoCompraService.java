package com.mines.SugestaoCompra.Service;

import java.util.List;
import com.mines.SugestaoCompra.Model.SituacaoSugestaoCompra;

public interface SituacaoSugestaoCompraService {
    
    List<SituacaoSugestaoCompra> obterTodosSituacoesSugestaoCompras(Integer entidade);
    
    SituacaoSugestaoCompra obterPorIdSituacaoSugestaoCompra(Integer id);
}

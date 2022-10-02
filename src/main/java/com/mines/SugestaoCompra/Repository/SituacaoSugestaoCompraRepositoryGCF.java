package com.mines.SugestaoCompra.Repository;

import java.util.List;
import com.mines.SugestaoCompra.Model.SituacaoSugestaoCompra;

public interface SituacaoSugestaoCompraRepositoryGCF {
    
    List<SituacaoSugestaoCompra> obterTodosSituacoesSugestaoCompras(Integer entidade);
    
    SituacaoSugestaoCompra obterPorIdSituacaoSugestaoCompra(Integer id);
}

package com.mines.SugestaoCompra.Repository;

import java.util.List;
import com.mines.SugestaoCompra.Model.SituacaoSugestaoCompra;

public interface SituacaoSugestaoCompraRepositoryWinthor {
    
    List<SituacaoSugestaoCompra> obterTodosSituacoesSugestaoCompras(Integer entidade);
    
    SituacaoSugestaoCompra obterPorIdSituacaoSugestaoCompra(Integer id);
}

package com.mines.SugestaoCompra.Repository;

import java.util.List;
import com.mines.SugestaoCompra.Model.SituacaoSugestaoCompra;

public interface SituacaoSugestaoCompraRepository {
    
    List<SituacaoSugestaoCompra> obterTodosSituacoesSugestaoCompras(Integer entidade);
    
    SituacaoSugestaoCompra obterPorIdSituacaoSugestaoCompra(Integer id);

    SituacaoSugestaoCompra salvarSituacaoSugestaoCompra(SituacaoSugestaoCompra situacaoSugestaoCompra);

    SituacaoSugestaoCompra atualizarSituacaoSugestaoCompra(SituacaoSugestaoCompra situacaoSugestaoCompra);

    Boolean deletarSituacaoSugestaoCompra(SituacaoSugestaoCompra situacaoSugestaoCompra);
}

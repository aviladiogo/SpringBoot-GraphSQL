package com.mines.SugestaoCompra.Repository;

import java.util.List;
import com.mines.SugestaoCompra.Model.SugestaoCompraItem;

public interface SugestaoCompraItemRepository {

    List<SugestaoCompraItem> obterTodosSugestoesCompraItem(Integer entidade);
    
    SugestaoCompraItem obterPorIdSugestaoCompraItem(Integer id);

    SugestaoCompraItem salvarSugestaoCompraItem(SugestaoCompraItem sugestaoCompraItem);

    SugestaoCompraItem atualizarSugestaoCompraItem(Integer id, SugestaoCompraItem sugestaoCompraItem);

    Boolean deletarSugestaoCompraItem(Integer id);
    
}

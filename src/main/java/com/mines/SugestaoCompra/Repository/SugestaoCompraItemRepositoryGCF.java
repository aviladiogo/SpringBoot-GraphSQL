package com.mines.SugestaoCompra.Repository;

import java.util.List;
import com.mines.SugestaoCompra.Model.SugestaoCompraItem;

public interface SugestaoCompraItemRepositoryGCF {

    List<SugestaoCompraItem> obterTodosSugestoesCompraItem(Integer entidade);
    
    SugestaoCompraItem obterPorIdSugestaoCompraItem(Integer id);
    
}

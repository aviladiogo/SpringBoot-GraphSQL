package com.mines.SugestaoCompra.Service;

import java.util.List;
import com.mines.SugestaoCompra.Model.SugestaoCompraItem;

public interface SugestaoCompraItemService {

    List<SugestaoCompraItem> obterTodosSugestoesCompraItem(Integer entidade);
    
    SugestaoCompraItem obterPorIdSugestaoCompraItem(Integer id);
    
}

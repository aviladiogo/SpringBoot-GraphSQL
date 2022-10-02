package com.mines.Fornecedor.Service;

import java.util.List;
import com.mines.Fornecedor.Model.TipoFrete;

public interface TipoFreteService {

    List<TipoFrete> obterTodosTiposFrete(Integer entidade);
    
    TipoFrete obterPorIdTipoFrete(Integer id);
    
}

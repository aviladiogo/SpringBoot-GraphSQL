package com.mines.CondicaoPagto.Service;

import java.util.List;
import com.mines.CondicaoPagto.Model.TipoPagto;

public interface TipoPagtoService {
    
    List<TipoPagto> obterTodosTiposPagto(Integer entidade);
    
    TipoPagto obterPorIdTipoPagto(Integer id);

}


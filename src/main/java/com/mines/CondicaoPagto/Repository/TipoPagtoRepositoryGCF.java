package com.mines.CondicaoPagto.Repository;

import java.util.List;
import com.mines.CondicaoPagto.Model.TipoPagto;

public interface TipoPagtoRepositoryGCF {
    
    List<TipoPagto> obterTodosTiposPagto(Integer entidade);
    
    TipoPagto obterPorIdTipoPagto(Integer id);

}

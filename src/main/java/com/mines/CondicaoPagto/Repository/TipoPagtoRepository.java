package com.mines.CondicaoPagto.Repository;

import java.util.List;
import com.mines.CondicaoPagto.Model.TipoPagto;

public interface TipoPagtoRepository {
    
    List<TipoPagto> obterTodosTiposPagto(Integer entidade);
    
    TipoPagto obterPorIdTipoPagto(Integer id);

    TipoPagto salvarTipoPagto(TipoPagto tipoPagto);

    TipoPagto atualizarTipoPagto(TipoPagto tipoPagto);

    Boolean deletarTipoPagto(TipoPagto tipoPagto);
}

package com.mines.CondicaoPagto.Repository;

import java.util.List;
import com.mines.CondicaoPagto.Model.CondicaoPagto;

public interface CondicaoPagtoRepository {

    List<CondicaoPagto> obterTodosCondicoesPagto(Integer entidade);
    
    CondicaoPagto obterPorIdCondicaoPagto(Integer id);

    CondicaoPagto salvarCondicaoPagto(CondicaoPagto condicaoPagto);

    CondicaoPagto atualizarCondicaoPagto(CondicaoPagto condicaoPagto);

    Boolean deletarCondicaoPagto(CondicaoPagto condicaoPagto);
    
}

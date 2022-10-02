package com.mines.CondicaoPagto.Repository;

import java.util.List;
import com.mines.CondicaoPagto.Model.CondicaoPagto;

public interface CondicaoPagtoRepositoryWinthor {

    List<CondicaoPagto> obterTodosCondicoesPagto(Integer entidade);
    
    CondicaoPagto obterPorIdCondicaoPagto(Integer id);
    
}
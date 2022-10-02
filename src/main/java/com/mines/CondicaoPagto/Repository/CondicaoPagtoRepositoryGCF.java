package com.mines.CondicaoPagto.Repository;

import java.util.List;
import com.mines.CondicaoPagto.Model.CondicaoPagto;

public interface CondicaoPagtoRepositoryGCF {

    List<CondicaoPagto> obterTodosCondicoesPagto(Integer entidade);
    
    CondicaoPagto obterPorIdCondicaoPagto(Integer id);
    
}
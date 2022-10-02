package com.mines.CondicaoPagto.Service;

import java.util.List;
import com.mines.CondicaoPagto.Model.CondicaoPagto;

public interface CondicaoPagtoService {

    List<CondicaoPagto> obterTodosCondicoesPagto(Integer entidade);
    
    CondicaoPagto obterPorIdCondicaoPagto(Integer id);
    
}
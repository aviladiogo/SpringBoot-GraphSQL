package com.mines.CondicaoPagto.Repository;

import java.util.List;
import com.mines.CondicaoPagto.Model.PrazoParcela;

public interface PrazoParcelaRepositoryGCF {

    List<PrazoParcela> obterTodosPrazoParcelas(Integer entidade);
    
    PrazoParcela obterPorIdPrazoParcela(Integer id);
    
}
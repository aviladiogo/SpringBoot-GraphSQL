package com.mines.CondicaoPagto.Service;

import java.util.List;
import com.mines.CondicaoPagto.Model.PrazoParcela;

public interface PrazoParcelaService {

    List<PrazoParcela> obterTodosPrazoParcelas(Integer entidade);
    
    PrazoParcela obterPorIdPrazoParcela(Integer id);
    
}
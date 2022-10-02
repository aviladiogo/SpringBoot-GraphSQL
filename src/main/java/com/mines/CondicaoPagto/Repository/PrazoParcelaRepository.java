package com.mines.CondicaoPagto.Repository;

import java.util.List;
import com.mines.CondicaoPagto.Model.PrazoParcela;

public interface PrazoParcelaRepository {

    List<PrazoParcela> obterTodosPrazoParcelas(Integer entidade);
    
    PrazoParcela obterPorIdPrazoParcela(Integer id);

    PrazoParcela salvarPrazoParcela(PrazoParcela prazoParcela);

    PrazoParcela atualizarPrazoParcela(PrazoParcela prazoParcela);

    Boolean deletarPrazoParcela(PrazoParcela prazoParcela);
    
}

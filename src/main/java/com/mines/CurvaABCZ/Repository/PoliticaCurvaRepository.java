package com.mines.CurvaABCZ.Repository;

import java.util.List;
import com.mines.CurvaABCZ.Model.PoliticaCurva;

public interface PoliticaCurvaRepository {
    
    List<PoliticaCurva> obterTodosPoliticaCurvas(Integer entidade);
    
    PoliticaCurva obterPorIdPoliticaCurva(Integer id);

    PoliticaCurva salvarPoliticaCurva(PoliticaCurva politicaCurva);

    PoliticaCurva atualizarPoliticaCurva(PoliticaCurva politicaCurva);

    Boolean deletarPoliticaCurva(PoliticaCurva politicaCurva);
}

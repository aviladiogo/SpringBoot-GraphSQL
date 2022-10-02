package com.mines.CurvaABCZ.Repository;

import java.util.List;
import com.mines.CurvaABCZ.Model.PoliticaCurva;

public interface PoliticaCurvaRepositoryGCF {
    
    List<PoliticaCurva> obterTodosPoliticaCurvas(Integer entidade);
    
    PoliticaCurva obterPorIdPoliticaCurva(Integer id);

}
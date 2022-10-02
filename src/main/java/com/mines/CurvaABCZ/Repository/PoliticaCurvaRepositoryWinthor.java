package com.mines.CurvaABCZ.Repository;

import java.util.List;
import com.mines.CurvaABCZ.Model.PoliticaCurva;

public interface PoliticaCurvaRepositoryWinthor {
    
    List<PoliticaCurva> obterTodosPoliticaCurvas(Integer entidade);
    
    PoliticaCurva obterPorIdPoliticaCurva(Integer id);

}
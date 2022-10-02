package com.mines.CurvaABCZ.Service;

import java.util.List;
import com.mines.CurvaABCZ.Model.PoliticaCurva;

public interface PoliticaCurvaService {
    
    List<PoliticaCurva> obterTodosPoliticaCurvas(Integer entidade);
    
    PoliticaCurva obterPorIdPoliticaCurva(Integer id);

}
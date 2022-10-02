package com.mines.CurvaABCZ.Repository;

import java.util.List;
import com.mines.CurvaABCZ.Model.CurvaGiroVendaDia;

public interface CurvaGiroVendaDiaRepositoryGCF {

    List<CurvaGiroVendaDia> obterTodosCurvasGiroVendaDia(Integer entidade);
    
    CurvaGiroVendaDia obterPorIdCurvaGiroVendaDia(Integer id);
    
}
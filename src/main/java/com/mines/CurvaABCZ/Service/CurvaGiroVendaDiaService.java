package com.mines.CurvaABCZ.Service;

import java.util.List;
import com.mines.CurvaABCZ.Model.CurvaGiroVendaDia;

public interface CurvaGiroVendaDiaService {

    List<CurvaGiroVendaDia> obterTodosCurvasGiroVendaDia(Integer entidade);
    
    CurvaGiroVendaDia obterPorIdCurvaGiroVendaDia(Integer id);
    
}
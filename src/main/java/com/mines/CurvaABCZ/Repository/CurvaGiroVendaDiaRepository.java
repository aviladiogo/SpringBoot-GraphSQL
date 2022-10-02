package com.mines.CurvaABCZ.Repository;

import java.util.List;
import com.mines.CurvaABCZ.Model.CurvaGiroVendaDia;

public interface CurvaGiroVendaDiaRepository {

    List<CurvaGiroVendaDia> obterTodosCurvasGiroVendaDia(Integer entidade);
    
    CurvaGiroVendaDia obterPorIdCurvaGiroVendaDia(Integer id);

    CurvaGiroVendaDia salvarCurvaGiroVendaDia(CurvaGiroVendaDia curvaGiroVendaDia);

    CurvaGiroVendaDia atualizarCurvaGiroVendaDia(CurvaGiroVendaDia curvaGiroVendaDia);

    Boolean deletarCurvaGiroVendaDia(CurvaGiroVendaDia curvaGiroVendaDia);
    
}

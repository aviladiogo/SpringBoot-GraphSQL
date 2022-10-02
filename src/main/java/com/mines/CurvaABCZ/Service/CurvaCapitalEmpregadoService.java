package com.mines.CurvaABCZ.Service;

import java.util.List;
import com.mines.CurvaABCZ.Model.CurvaCapitalEmpregado;

public interface CurvaCapitalEmpregadoService {
    
    List<CurvaCapitalEmpregado> obterTodosCurvasCapitalEmpregado(Integer entidade);
    
    CurvaCapitalEmpregado obterPorIdCurvaCapitalEmpregado(Integer id);

}

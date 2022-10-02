package com.mines.CurvaABCZ.Repository;

import java.util.List;
import com.mines.CurvaABCZ.Model.CurvaCapitalEmpregado;

public interface CurvaCapitalEmpregadoRepositoryGCF {
    
    List<CurvaCapitalEmpregado> obterTodosCurvasCapitalEmpregado(Integer entidade);
    
    CurvaCapitalEmpregado obterPorIdCurvaCapitalEmpregado(Integer id);

}
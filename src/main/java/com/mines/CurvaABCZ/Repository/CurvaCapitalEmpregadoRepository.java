package com.mines.CurvaABCZ.Repository;

import java.util.List;
import com.mines.CurvaABCZ.Model.CurvaCapitalEmpregado;

public interface CurvaCapitalEmpregadoRepository {
    
    List<CurvaCapitalEmpregado> obterTodosCurvasCapitalEmpregado(Integer entidade);
    
    CurvaCapitalEmpregado obterPorIdCurvaCapitalEmpregado(Integer id);

    CurvaCapitalEmpregado salvarCurvaCapitalEmpregado(CurvaCapitalEmpregado curvaCapitalEmpregado);

    CurvaCapitalEmpregado atualizarCurvaCapitalEmpregado(CurvaCapitalEmpregado curvaCapitalEmpregado);

    Boolean deletarCurvaCapitalEmpregado(CurvaCapitalEmpregado curvaCapitalEmpregado);
}

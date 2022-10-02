package com.mines.CurvaABCZ.Repository;

import java.util.List;
import com.mines.CurvaABCZ.Model.CurvaMargemContribuicao;

public interface CurvaMargemContribuicaoRepositoryGCF {
    
    List<CurvaMargemContribuicao> obterTodosCurvasMargemContribuicao(Integer entidade);
    
    CurvaMargemContribuicao obterPorIdCurvaMargemContribuicao(Integer id);
}

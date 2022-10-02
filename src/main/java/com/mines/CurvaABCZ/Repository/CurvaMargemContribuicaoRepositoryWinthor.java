package com.mines.CurvaABCZ.Repository;

import java.util.List;
import com.mines.CurvaABCZ.Model.CurvaMargemContribuicao;

public interface CurvaMargemContribuicaoRepositoryWinthor {
    
    List<CurvaMargemContribuicao> obterTodosCurvasMargemContribuicao(Integer entidade);
    
    CurvaMargemContribuicao obterPorIdCurvaMargemContribuicao(Integer id);
}

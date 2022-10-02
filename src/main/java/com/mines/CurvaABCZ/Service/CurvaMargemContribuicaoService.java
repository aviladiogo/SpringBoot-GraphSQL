package com.mines.CurvaABCZ.Service;

import java.util.List;
import com.mines.CurvaABCZ.Model.CurvaMargemContribuicao;

public interface CurvaMargemContribuicaoService {
    
    List<CurvaMargemContribuicao> obterTodosCurvasMargemContribuicao(Integer entidade);
    
    CurvaMargemContribuicao obterPorIdCurvaMargemContribuicao(Integer id);
}


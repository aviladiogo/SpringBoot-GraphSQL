package com.mines.CurvaABCZ.Repository;

import java.util.List;
import com.mines.CurvaABCZ.Model.CurvaMargemContribuicao;

public interface CurvaMargemContribuicaoRepository {
    
    List<CurvaMargemContribuicao> obterTodosCurvasMargemContribuicao(Integer entidade);
    
    CurvaMargemContribuicao obterPorIdCurvaMargemContribuicao(Integer id);

    CurvaMargemContribuicao salvarCurvaMargemContribuicao(CurvaMargemContribuicao curvaMargemContribuicao);

    CurvaMargemContribuicao atualizarCurvaMargemContribuicao(CurvaMargemContribuicao curvaMargemContribuicao);

    Boolean deletarCurvaMargemContribuicao(CurvaMargemContribuicao curvaMargemContribuicao);
}

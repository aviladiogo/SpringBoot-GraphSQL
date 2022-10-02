package com.mines.CurvaABCZ.Repository;

import java.util.List;
import com.mines.CurvaABCZ.Model.CurvaAbcz;

public interface CurvaAbczRepository {

    List<CurvaAbcz> obterTodosCurvasABCZ(Integer entidade);

    List<Integer> obterTodosIDsCurvaABCZ();
    
    CurvaAbcz obterPorIdCurvaABCZ(Integer id);

    CurvaAbcz salvarCurvaABCZ(CurvaAbcz curvaAbcz);

    CurvaAbcz atualizarCurvaABCZ(CurvaAbcz curvaAbcz);

    Boolean deletarCurvaABCZ(CurvaAbcz curvaAbcz);
}

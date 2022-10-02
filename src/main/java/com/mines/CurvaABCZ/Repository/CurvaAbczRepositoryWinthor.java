package com.mines.CurvaABCZ.Repository;

import java.util.List;
import com.mines.CurvaABCZ.Model.CurvaAbcz;

public interface CurvaAbczRepositoryWinthor {

    List<CurvaAbcz> obterTodosCurvasABCZ(Integer entidade);
    
    CurvaAbcz obterPorIdCurvaABCZ(Integer id);

}
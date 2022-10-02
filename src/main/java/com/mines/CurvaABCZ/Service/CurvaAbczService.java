package com.mines.CurvaABCZ.Service;

import java.util.List;
import com.mines.CurvaABCZ.Model.CurvaAbcz;

public interface CurvaAbczService {

    List<CurvaAbcz> obterTodosCurvasABCZ(Integer entidade);
    
    CurvaAbcz obterPorIdCurvaABCZ(Integer id);

}
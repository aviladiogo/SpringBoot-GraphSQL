package com.mines.CurvaABCZ.Service;

import java.util.List;
import com.mines.CurvaABCZ.Model.TipoCurvaAbcz;

public interface TipoCurvaAbczService {
    
    List<TipoCurvaAbcz> obterTodosTiposCurvaAbcz(Integer entidade);
    
    TipoCurvaAbcz obterPorIdTipoCurvaAbcz(Integer id);

}
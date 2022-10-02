package com.mines.CurvaABCZ.Repository;

import java.util.List;
import com.mines.CurvaABCZ.Model.TipoCurvaAbcz;

public interface TipoCurvaAbczRepositoryWinthor {
    
    List<TipoCurvaAbcz> obterTodosTiposCurvaAbcz(Integer entidade);
    
    TipoCurvaAbcz obterPorIdTipoCurvaAbcz(Integer id);

}
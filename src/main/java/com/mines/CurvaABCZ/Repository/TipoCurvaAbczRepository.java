package com.mines.CurvaABCZ.Repository;

import java.util.List;
import com.mines.CurvaABCZ.Model.TipoCurvaAbcz;

public interface TipoCurvaAbczRepository {
    
    List<TipoCurvaAbcz> obterTodosTiposCurvaAbcz(Integer entidade);
    
    TipoCurvaAbcz obterPorIdTipoCurvaAbcz(Integer id);

    TipoCurvaAbcz salvarTipoCurvaAbcz(TipoCurvaAbcz tipoCurvaAbcz);

    TipoCurvaAbcz atualizarTipoCurvaAbcz(TipoCurvaAbcz tipoCurvaAbcz);

    Boolean deletarTipoCurvaAbcz(TipoCurvaAbcz tipoCurvaAbcz);
}

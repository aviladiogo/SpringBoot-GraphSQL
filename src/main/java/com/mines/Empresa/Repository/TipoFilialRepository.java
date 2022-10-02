package com.mines.Empresa.Repository;

import java.util.List;
import com.mines.Empresa.Model.TipoFilial;

public interface TipoFilialRepository {
    
    List<TipoFilial> obterTodosTipoFiliais(Integer entidade);
    
    TipoFilial obterPorIdTipoFilial(Integer id);

    TipoFilial salvarTipoFilial(TipoFilial tipoFilial);

    TipoFilial atualizarTipoFilial(TipoFilial tipoFilial);

    Boolean deletarTipoFilial(TipoFilial tipoFilial);
}

package com.mines.Empresa.Repository;

import java.util.List;
import com.mines.Empresa.Model.TipoFilial;

public interface TipoFilialRepositoryGCF {
    
    List<TipoFilial> obterTodosTipoFiliais(Integer entidade);
    
    TipoFilial obterPorIdTipoFilial(Integer id);

}

package com.mines.Empresa.Repository;

import java.util.List;
import com.mines.Empresa.Model.TipoFilial;

public interface TipoFilialRepositoryWinthor {
    
    List<TipoFilial> obterTodosTipoFiliais(Integer entidade);
    
    TipoFilial obterPorIdTipoFilial(Integer id);

}

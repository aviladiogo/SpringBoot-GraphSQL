package com.mines.Empresa.Service;

import java.util.List;
import com.mines.Empresa.Model.TipoFilial;

public interface TipoFilialService {
    
    List<TipoFilial> obterTodosTipoFiliais(Integer entidade);
    
    TipoFilial obterPorIdTipoFilial(Integer id);

}

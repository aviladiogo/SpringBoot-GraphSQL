package com.mines.Empresa.Service;

import java.util.List;
import com.mines.Empresa.Model.GrupoFilial;

public interface GrupoFilialService {
    
    List<GrupoFilial> obterTodosGrupoFiliais(Integer entidade);
    
    GrupoFilial obterPorIdGrupoFilial(Integer id);

}

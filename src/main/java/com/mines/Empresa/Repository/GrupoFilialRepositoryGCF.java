package com.mines.Empresa.Repository;

import java.util.List;
import com.mines.Empresa.Model.GrupoFilial;

public interface GrupoFilialRepositoryGCF {
    
    List<GrupoFilial> obterTodosGrupoFiliais(Integer entidade);
    
    GrupoFilial obterPorIdGrupoFilial(Integer id);

}

package com.mines.Empresa.Repository;

import java.util.List;
import com.mines.Empresa.Model.GrupoFilial;

public interface GrupoFilialRepository {
    
    List<GrupoFilial> obterTodosGrupoFiliais(Integer entidade);
    
    GrupoFilial obterPorIdGrupoFilial(Integer id);

    GrupoFilial salvarGrupoFilial(GrupoFilial grupoFilial);

    GrupoFilial atualizarGrupoFilial(GrupoFilial grupoFilial);

    Boolean deletarGrupoFilial(GrupoFilial grupoFilial);
}

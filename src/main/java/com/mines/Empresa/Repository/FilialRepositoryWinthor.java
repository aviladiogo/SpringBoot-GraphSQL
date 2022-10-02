package com.mines.Empresa.Repository;

import java.util.List;
import com.mines.Empresa.Model.Filial;

public interface FilialRepositoryWinthor {
    
    List<Filial> obterTodosFiliais(Integer entidade);

    List<Filial> obterTodosFiliaisPorGrupoCompraInterno(Integer entidade, Integer grupoCompraInterno);
    
    Filial obterPorIdFilial(Integer id);
}

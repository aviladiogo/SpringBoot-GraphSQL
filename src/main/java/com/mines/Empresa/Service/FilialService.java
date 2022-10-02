package com.mines.Empresa.Service;

import java.util.List;
import com.mines.Empresa.Model.Filial;

public interface FilialService {
    
    List<Filial> obterTodosFiliais(Integer entidade);

    List<Filial> obterTodosFiliaisPorGrupoCompraInterno(Integer entidade, Integer grupoCompraInterno);
    
    Filial obterPorIdFilial(Integer id);
}

package com.mines.Empresa.Repository;

import java.util.List;
import com.mines.Empresa.Model.Filial;

public interface FilialRepository {
    
    List<Filial> obterTodosFiliais(Integer entidade);

    List<Filial> obterTodosFiliaisPorGrupoCompraInterno(Integer entidade, Integer grupoCompraInterno);
    
    Filial obterPorIdFilial(Integer id);

    Filial salvarFilial(Filial filial);

    Filial atualizarFilial(Filial filial);

    Boolean deletarFilial(Filial filial);
}

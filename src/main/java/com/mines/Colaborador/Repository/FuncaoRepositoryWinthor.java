package com.mines.Colaborador.Repository;

import java.util.List;
import com.mines.Colaborador.Model.Funcao;

public interface FuncaoRepositoryWinthor {
    
    List<Funcao> obterTodosFuncoes(Integer entidade);
    
    Funcao obterPorIdFuncao(Integer id);

}

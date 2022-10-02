package com.mines.Colaborador.Repository;

import java.util.List;
import com.mines.Colaborador.Model.Funcao;

public interface FuncaoRepositoryGCF {
    
    List<Funcao> obterTodosFuncoes(Integer entidade);
    
    Funcao obterPorIdFuncao(Integer id);

}
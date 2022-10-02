package com.mines.Colaborador.Repository;

import java.util.List;
import com.mines.Colaborador.Model.Funcao;

public interface FuncaoRepository {
    
    List<Funcao> obterTodosFuncoes(Integer entidade);
    
    Funcao obterPorIdFuncao(Integer id);

    Funcao salvarFuncao(Funcao funcao);

    Funcao atualizarFuncao(Funcao funcao);

    Boolean deletarFuncao(Funcao funcao);
}

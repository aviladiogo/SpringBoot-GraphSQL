package com.mines.Colaborador.Service;

import java.util.List;
import com.mines.Colaborador.Model.Funcao;

public interface FuncaoService {
    
    List<Funcao> obterTodosFuncoes(Integer entidade);
    
    Funcao obterPorIdFuncao(Integer id);

}
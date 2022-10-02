package com.mines.Colaborador.Repository;

import java.util.List;
import com.mines.Colaborador.Model.Colaborador;

public interface ColaboradorRepositoryGCF {

    List<Colaborador> obterTodosColaboradores(Integer entidade);
    
    Colaborador obterPorIdColaborador(Integer id);
    
}
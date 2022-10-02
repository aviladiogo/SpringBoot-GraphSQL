package com.mines.Colaborador.Service;

import java.util.List;
import com.mines.Colaborador.Model.Colaborador;

public interface ColaboradorService {

    List<Colaborador> obterTodosColaboradores(Integer entidade);
    
    Colaborador obterPorIdColaborador(Integer id);
    
}
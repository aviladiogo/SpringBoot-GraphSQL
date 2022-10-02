package com.mines.Colaborador.Repository;

import java.util.List;
import com.mines.Colaborador.Model.Colaborador;

public interface ColaboradorRepository {

    List<Colaborador> obterTodosColaboradores(Integer entidade);
    
    Colaborador obterPorIdColaborador(Integer id);

    Colaborador salvarColaborador(Colaborador colaborador);

    Colaborador atualizarColaborador(Colaborador colaborador);

    Boolean deletarColaborador(Colaborador colaborador);
    
}

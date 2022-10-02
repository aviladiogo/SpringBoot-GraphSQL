package com.mines.Seguranca.Repository;

import java.util.List;
import com.mines.Seguranca.Model.Modulo;

public interface ModuloRepository {
    
    List<Modulo> obterTodosModulos(Integer entidade);
    
    Modulo obterPorIdModulo(Integer id);

    Modulo salvarModulo(Modulo modulo);

    Modulo atualizarModulo(Modulo modulo);

    Boolean deletarModulo(Modulo modulo);
}

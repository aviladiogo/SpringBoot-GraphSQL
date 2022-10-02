package com.mines.Seguranca.Repository;

import java.util.List;
import com.mines.Seguranca.Model.Modulo;

public interface ModuloRepositoryWinthor {
    
    List<Modulo> obterTodosModulos(Integer entidade);
    
    Modulo obterPorIdModulo(Integer id);
}

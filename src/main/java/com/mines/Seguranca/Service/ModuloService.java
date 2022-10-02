package com.mines.Seguranca.Service;

import java.util.List;
import com.mines.Seguranca.Model.Modulo;

public interface ModuloService {
    
    List<Modulo> obterTodosModulos(Integer entidade);
    
    Modulo obterPorIdModulo(Integer id);
}

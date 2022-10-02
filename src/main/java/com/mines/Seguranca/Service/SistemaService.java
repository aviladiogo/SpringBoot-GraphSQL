package com.mines.Seguranca.Service;

import java.util.List;
import com.mines.Seguranca.Model.Sistema;

public interface SistemaService {
    
    List<Sistema> obterTodosSistemas(Integer entidade);
    
    Sistema obterPorIdSistema(Integer id);
}

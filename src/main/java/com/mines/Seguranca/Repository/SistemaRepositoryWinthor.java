package com.mines.Seguranca.Repository;

import java.util.List;
import com.mines.Seguranca.Model.Sistema;

public interface SistemaRepositoryWinthor {
    
    List<Sistema> obterTodosSistemas(Integer entidade);
    
    Sistema obterPorIdSistema(Integer id);
}

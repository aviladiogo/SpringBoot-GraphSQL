package com.mines.Seguranca.Repository;

import java.util.List;
import com.mines.Seguranca.Model.Sistema;

public interface SistemaRepository {
    
    List<Sistema> obterTodosSistemas(Integer entidade);
    
    Sistema obterPorIdSistema(Integer id);

    Sistema salvarSistema(Sistema sistema);

    Sistema atualizarSistema(Sistema sistema);

    Boolean deletarSistema(Sistema sistema);
}

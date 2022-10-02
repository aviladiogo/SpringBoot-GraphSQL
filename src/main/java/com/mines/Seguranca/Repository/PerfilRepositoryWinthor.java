package com.mines.Seguranca.Repository;

import java.util.List;
import com.mines.Seguranca.Model.Perfil;

public interface PerfilRepositoryWinthor {
    
    List<Perfil> obterTodosPerfis(Integer entidade);
    
    Perfil obterPorIdPerfil(Integer id);
}

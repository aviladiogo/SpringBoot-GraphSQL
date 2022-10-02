package com.mines.Seguranca.Repository;

import java.util.List;
import com.mines.Seguranca.Model.Perfil;

public interface PerfilRepository {
    
    List<Perfil> obterTodosPerfis(Integer entidade);
    
    Perfil obterPorIdPerfil(Integer id);

    Perfil salvarPerfil(Perfil perfil);

    Perfil atualizarPerfil(Perfil perfil);

    Boolean deletarPerfil(Perfil perfil);
}

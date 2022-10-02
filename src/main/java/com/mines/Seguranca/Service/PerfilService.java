package com.mines.Seguranca.Service;

import java.util.List;
import com.mines.Seguranca.Model.Perfil;

public interface PerfilService {
    
    List<Perfil> obterTodosPerfis(Integer entidade);
    
    Perfil obterPorIdPerfil(Integer id);
}

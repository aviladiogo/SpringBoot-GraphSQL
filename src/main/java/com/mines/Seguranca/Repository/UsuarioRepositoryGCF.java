package com.mines.Seguranca.Repository;

import java.util.List;
import com.mines.Seguranca.Model.Usuario;

public interface UsuarioRepositoryGCF {
    
    List<Usuario> obterTodosUsuarios(Integer entidade);
    
    Usuario obterPorIdUsuario(Integer id);
}

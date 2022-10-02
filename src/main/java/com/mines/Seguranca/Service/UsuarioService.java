package com.mines.Seguranca.Service;

import java.util.List;
import com.mines.Seguranca.Model.Usuario;

public interface UsuarioService {
    
    List<Usuario> obterTodosUsuarios(Integer entidade);
    
    Usuario obterPorIdUsuario(Integer id);
}

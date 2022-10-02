package com.mines.Seguranca.Repository;

import java.util.List;
import com.mines.Seguranca.Model.Usuario;

public interface UsuarioRepository {
    
    List<Usuario> obterTodosUsuarios(Integer entidade);
    
    Usuario obterPorIdUsuario(Integer id);

    Usuario obterUsuarioPorLogin(String login);

    Usuario validarLogin(String login, String senha);

    Usuario inserirUsuario(Usuario usuario) throws Exception;

    Usuario atualizarUsuario(Usuario usuario);

    Boolean deletarUsuario(Usuario usuario);
}

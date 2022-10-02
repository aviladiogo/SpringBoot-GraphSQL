package com.mines.Seguranca.Service;

import java.util.List;
import com.mines.Seguranca.Model.Permissao;

public interface PermissaoService {
    
    List<Permissao> obterTodosPermissoes(Integer entidade);
    
    Permissao obterPorIdPermissao(Integer id);
}

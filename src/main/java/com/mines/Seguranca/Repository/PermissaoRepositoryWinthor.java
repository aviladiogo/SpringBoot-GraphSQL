package com.mines.Seguranca.Repository;

import java.util.List;
import com.mines.Seguranca.Model.Permissao;

public interface PermissaoRepositoryWinthor {
    
    List<Permissao> obterTodosPermissoes(Integer entidade);
    
    Permissao obterPorIdPermissao(Integer id);
}

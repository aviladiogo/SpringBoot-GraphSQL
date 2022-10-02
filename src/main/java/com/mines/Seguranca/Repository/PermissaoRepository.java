package com.mines.Seguranca.Repository;

import java.util.List;
import com.mines.Seguranca.Model.Permissao;

public interface PermissaoRepository {
    
    List<Permissao> obterTodosPermissoes(Integer entidade);
    
    Permissao obterPorIdPermissao(Integer id);

    Permissao salvarPermissao(Permissao permissao);

    Permissao atualizarPermissao(Permissao permissao);

    Boolean deletarPermissao(Permissao permissao);
}

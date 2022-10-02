package com.mines.Produto.Repository;

import java.util.List;
import com.mines.Produto.Model.Embalagem;

public interface EmbalagemRepositoryGCF {
    
    List<Embalagem> obterTodosEmbalagens(Integer entidade);
    
    Embalagem obterPorIdEmbalagem(Integer id);
}


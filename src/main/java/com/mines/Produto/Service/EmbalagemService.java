package com.mines.Produto.Service;

import java.util.List;
import com.mines.Produto.Model.Embalagem;

public interface EmbalagemService {
    
    List<Embalagem> obterTodosEmbalagens(Integer entidade);
    
    Embalagem obterPorIdEmbalagem(Integer id);
}


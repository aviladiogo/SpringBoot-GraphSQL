package com.mines.Produto.Repository;

import java.util.List;
import com.mines.Produto.Model.Embalagem;

public interface EmbalagemRepository {
    
    List<Embalagem> obterTodosEmbalagens(Integer entidade);
    
    Embalagem obterPorIdEmbalagem(Integer id);

    Embalagem salvarEmbalagem(Embalagem embalagem);

    Embalagem atualizarEmbalagem(Embalagem embalagem);

    Boolean deletarEmbalagem(Embalagem embalagem);
}

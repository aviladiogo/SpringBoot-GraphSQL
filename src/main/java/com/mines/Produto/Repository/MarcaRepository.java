package com.mines.Produto.Repository;

import java.util.List;
import com.mines.Produto.Model.Marca;

public interface MarcaRepository {
    
    List<Marca> obterTodosMarcas(Integer entidade);
    
    Marca obterPorIdMarca(Integer id);

    Marca salvarMarca(Marca marca);

    Marca atualizarMarca(Marca marca);

    Boolean deletarMarca(Marca embalagem);
}

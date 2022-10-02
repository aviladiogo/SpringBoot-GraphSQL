package com.mines.Produto.Repository;

import java.util.List;
import com.mines.Produto.Model.Marca;

public interface MarcaRepositoryWinthor {
    
    List<Marca> obterTodosMarcas(Integer entidade);
    
    Marca obterPorIdMarca(Integer id);

}

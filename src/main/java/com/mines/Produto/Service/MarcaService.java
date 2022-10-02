package com.mines.Produto.Service;

import java.util.List;
import com.mines.Produto.Model.Marca;

public interface MarcaService {
    
    List<Marca> obterTodosMarcas(Integer entidade);
    
    Marca obterPorIdMarca(Integer id);

}

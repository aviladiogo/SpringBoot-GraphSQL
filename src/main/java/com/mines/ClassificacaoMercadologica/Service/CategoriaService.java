package com.mines.ClassificacaoMercadologica.Service;

import java.util.List;
import com.mines.ClassificacaoMercadologica.Model.Categoria;

public interface CategoriaService {

    List<Categoria> obterTodosCategorias(Integer entidade);
    
    List<Categoria> obterCategoriasPorSecao(Integer entidade, Integer secao);

    Categoria obterPorIdCategoria(Integer id);
}
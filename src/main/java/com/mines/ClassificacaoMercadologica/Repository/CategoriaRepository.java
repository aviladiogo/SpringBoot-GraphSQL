package com.mines.ClassificacaoMercadologica.Repository;

import java.util.List;
import com.mines.ClassificacaoMercadologica.Model.Categoria;

public interface CategoriaRepository {

    List<Categoria> obterTodosCategorias(Integer entidade);
    
    List<Categoria> obterCategoriasPorSecao(Integer entidade, Integer secao);

    Categoria obterPorIdCategoria(Integer id);

    Categoria salvarCategoria(Categoria categoria);

    Categoria atualizarCategoria(Categoria categoria);

    Boolean deletarCategoria(Categoria categoria);
    
}

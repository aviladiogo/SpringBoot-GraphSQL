package com.mines.ClassificacaoMercadologica.Repository;

import java.util.List;
import com.mines.ClassificacaoMercadologica.Model.SubCategoria;

public interface SubCategoriaRepositoryGCF {

    List<SubCategoria> obterTodosSubCategorias(Integer entidade);

    List<SubCategoria> obterSubCategoriasPorCategoria(Integer entidade, Integer categoria);
    
    SubCategoria obterPorIdSubCategoria(Integer id);
    
}
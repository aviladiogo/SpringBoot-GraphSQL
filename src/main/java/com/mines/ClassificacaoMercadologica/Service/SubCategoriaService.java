package com.mines.ClassificacaoMercadologica.Service;

import java.util.List;
import com.mines.ClassificacaoMercadologica.Model.SubCategoria;

public interface SubCategoriaService {

    List<SubCategoria> obterTodosSubCategorias(Integer entidade);

    List<SubCategoria> obterSubCategoriasPorCategoria(Integer entidade, Integer categoria);
    
    SubCategoria obterPorIdSubCategoria(Integer id);
    
}
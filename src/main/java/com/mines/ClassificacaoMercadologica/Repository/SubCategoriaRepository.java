package com.mines.ClassificacaoMercadologica.Repository;

import java.util.List;
import com.mines.ClassificacaoMercadologica.Model.SubCategoria;

public interface SubCategoriaRepository {

    List<SubCategoria> obterTodosSubCategorias(Integer entidade);

    List<SubCategoria> obterSubCategoriasPorCategoria(Integer entidade, Integer categoria);
    
    SubCategoria obterPorIdSubCategoria(Integer id);

    SubCategoria salvarSubCategoria(SubCategoria subCategoria);

    SubCategoria atualizarSubCategoria(SubCategoria subCategoria);

    Boolean deletarSubCategoria(SubCategoria subCategoria);
    
}

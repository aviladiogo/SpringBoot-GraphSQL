package com.mines.Comprador.Repository;

import java.util.List;
import com.mines.Comprador.Model.CompradorSubCategoria;

public interface CompradorSubCategoriaRepositoryGCF {
    
    List<CompradorSubCategoria> obterTodosCompradoresSubCategoria(Integer entidade);
    
    CompradorSubCategoria obterPorIdCompradorSubCategoria(Integer id);

}
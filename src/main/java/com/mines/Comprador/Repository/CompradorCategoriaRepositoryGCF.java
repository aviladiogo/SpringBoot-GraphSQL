package com.mines.Comprador.Repository;

import java.util.List;
import com.mines.Comprador.Model.CompradorCategoria;

public interface CompradorCategoriaRepositoryGCF {
    
    List<CompradorCategoria> obterTodosCompradoresCategoria(Integer entidade);
    
    CompradorCategoria obterPorIdCompradorCategoria(Integer id);
    
}

package com.mines.Comprador.Service;

import java.util.List;
import com.mines.Comprador.Model.CompradorCategoria;

public interface CompradorCategoriaService {
    
    List<CompradorCategoria> obterTodosCompradoresCategoria(Integer entidade);
    
    CompradorCategoria obterPorIdCompradorCategoria(Integer id);
    
}

package com.mines.Comprador.Service;

import java.util.List;
import com.mines.Comprador.Model.CompradorSubCategoria;

public interface CompradorSubCategoriaService {
    
    List<CompradorSubCategoria> obterTodosCompradoresSubCategoria(Integer entidade);
    
    CompradorSubCategoria obterPorIdCompradorSubCategoria(Integer id);

}
package com.mines.Comprador.Repository;

import java.util.List;
import com.mines.Comprador.Model.CompradorSubCategoria;

public interface CompradorSubCategoriaRepository {
    
    List<CompradorSubCategoria> obterTodosCompradoresSubCategoria(Integer entidade);
    
    CompradorSubCategoria obterPorIdCompradorSubCategoria(Integer id);

    CompradorSubCategoria salvarCompradorSubCategoria(CompradorSubCategoria compradorSubCategoria);

    CompradorSubCategoria atualizarCompradorSubCategoria(CompradorSubCategoria compradorSubCategoria);

    Boolean deletarCompradorSubCategoria(CompradorSubCategoria compradorSubCategoria);
}


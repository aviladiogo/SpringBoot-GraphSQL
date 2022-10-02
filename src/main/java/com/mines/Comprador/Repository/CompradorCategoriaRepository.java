package com.mines.Comprador.Repository;

import java.util.List;
import com.mines.Comprador.Model.CompradorCategoria;

public interface CompradorCategoriaRepository {
    
    List<CompradorCategoria> obterTodosCompradoresCategoria(Integer entidade);
    
    CompradorCategoria obterPorIdCompradorCategoria(Integer id);

    CompradorCategoria salvarCompradorCategoria(CompradorCategoria compradorCategoria);

    CompradorCategoria atualizarCompradorCategoria(CompradorCategoria compradorCategoria);

    Boolean deletarCompradorCategoria(CompradorCategoria compradorCategoria);
}


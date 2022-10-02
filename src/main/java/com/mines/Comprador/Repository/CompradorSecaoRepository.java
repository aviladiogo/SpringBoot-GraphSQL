package com.mines.Comprador.Repository;

import java.util.List;
import com.mines.Comprador.Model.CompradorSecao;

public interface CompradorSecaoRepository {
    
    List<CompradorSecao> obterTodosCompradoresSecao(Integer entidade);
    
    CompradorSecao obterPorIdCompradorSecao(Integer id);

    CompradorSecao salvarCompradorSecao(CompradorSecao compradorSecao);

    CompradorSecao atualizarCompradorSecao(CompradorSecao compradorSecao);

    Boolean deletarCompradorSecao(CompradorSecao compradorSecao);
}


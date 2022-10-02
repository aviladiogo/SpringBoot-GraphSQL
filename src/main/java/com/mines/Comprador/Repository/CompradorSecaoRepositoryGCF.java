package com.mines.Comprador.Repository;

import java.util.List;
import com.mines.Comprador.Model.CompradorSecao;

public interface CompradorSecaoRepositoryGCF {
    
    List<CompradorSecao> obterTodosCompradoresSecao(Integer entidade);
    
    CompradorSecao obterPorIdCompradorSecao(Integer id);
    
}
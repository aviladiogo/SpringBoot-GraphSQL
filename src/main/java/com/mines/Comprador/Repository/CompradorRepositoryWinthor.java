package com.mines.Comprador.Repository;

import java.util.List;
import com.mines.Comprador.Model.Comprador;

public interface CompradorRepositoryWinthor {

    List<Comprador> obterTodosCompradores(Integer entidade);
    
    Comprador obterPorIdComprador(Integer id);
    
}
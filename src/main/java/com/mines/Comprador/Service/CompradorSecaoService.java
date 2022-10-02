package com.mines.Comprador.Service;

import java.util.List;
import com.mines.Comprador.Model.CompradorSecao;

public interface CompradorSecaoService {
    
    List<CompradorSecao> obterTodosCompradoresSecao(Integer entidade);
    
    CompradorSecao obterPorIdCompradorSecao(Integer id);
    
}
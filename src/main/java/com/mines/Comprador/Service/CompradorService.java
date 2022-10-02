package com.mines.Comprador.Service;

import java.util.List;
import com.mines.Comprador.Model.Comprador;

public interface CompradorService {

    List<Comprador> obterTodosCompradores(Integer entidade);
    
    Comprador obterPorIdComprador(Integer id);
    
}

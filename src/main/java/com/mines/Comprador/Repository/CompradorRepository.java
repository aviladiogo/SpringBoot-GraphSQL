package com.mines.Comprador.Repository;

import java.util.List;
import com.mines.Comprador.Model.Comprador;

public interface CompradorRepository {

    List<Comprador> obterTodosCompradores(Integer entidade);
    
    Comprador obterPorIdComprador(Integer id);

    Comprador salvarComprador(Comprador comprador);

    Comprador atualizarComprador(Comprador comprador);

    Boolean deletarComprador(Comprador comprador);
    
}

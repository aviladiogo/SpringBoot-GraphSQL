package com.mines.Comprador.Service;

import java.util.List;
import com.mines.Comprador.Model.CompradorDepartamento;

public interface CompradorDepartamentoService {
    
    List<CompradorDepartamento> obterTodosCompradoresDepartamento(Integer entidade);
    
    CompradorDepartamento obterPorIdCompradorDepartamento(Integer id);

}

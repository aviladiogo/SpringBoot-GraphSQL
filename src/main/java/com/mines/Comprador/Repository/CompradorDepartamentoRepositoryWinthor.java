package com.mines.Comprador.Repository;

import java.util.List;
import com.mines.Comprador.Model.CompradorDepartamento;

public interface CompradorDepartamentoRepositoryWinthor {
    
    List<CompradorDepartamento> obterTodosCompradoresDepartamento(Integer entidade);
    
    CompradorDepartamento obterPorIdCompradorDepartamento(Integer id);

}

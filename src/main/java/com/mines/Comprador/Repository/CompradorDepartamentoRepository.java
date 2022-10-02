package com.mines.Comprador.Repository;

import java.util.List;
import com.mines.Comprador.Model.CompradorDepartamento;

public interface CompradorDepartamentoRepository {
    
    List<CompradorDepartamento> obterTodosCompradoresDepartamento(Integer entidade);
    
    CompradorDepartamento obterPorIdCompradorDepartamento(Integer id);

    CompradorDepartamento salvarCompradorDepartamento(CompradorDepartamento compradorDepartamento);

    CompradorDepartamento atualizarCompradorDepartamento(CompradorDepartamento compradorDepartamento);

    Boolean deletarCompradorDepartamento(CompradorDepartamento compradorDepartamento);
}

package com.mines.ClassificacaoMercadologica.Repository;

import java.util.List;
import com.mines.ClassificacaoMercadologica.Model.Departamento;

public interface DepartamentoRepository {
    
    List<Departamento> obterTodosDepartamentos(Integer entidade);
    
    Departamento obterPorIdDepartamento(Integer id);

    Departamento salvarDepartamento(Departamento departamento);

    Departamento atualizarDepartamento(Departamento departamento);

    Boolean deletarDepartamento(Departamento departamento);
}

package com.mines.ClassificacaoMercadologica.Repository;

import java.util.List;
import com.mines.ClassificacaoMercadologica.Model.Departamento;

public interface DepartamentoRepositoryWinthor {
    
    List<Departamento> obterTodosDepartamentos(Integer entidade);
    
    Departamento obterPorIdDepartamento(Integer id);
}

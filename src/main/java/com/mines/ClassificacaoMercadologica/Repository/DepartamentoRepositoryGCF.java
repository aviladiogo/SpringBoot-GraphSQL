package com.mines.ClassificacaoMercadologica.Repository;

import java.util.List;
import com.mines.ClassificacaoMercadologica.Model.Departamento;

public interface DepartamentoRepositoryGCF {
    
    List<Departamento> obterTodosDepartamentos(Integer entidade);
    
    Departamento obterPorIdDepartamento(Integer id);
}


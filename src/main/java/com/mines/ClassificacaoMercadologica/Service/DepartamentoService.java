package com.mines.ClassificacaoMercadologica.Service;

import java.util.List;

import com.mines.ClassificacaoMercadologica.Model.Departamento;
import org.springframework.stereotype.Service;

@Service
public interface DepartamentoService {
    
    List<Departamento> obterTodosDepartamentos(Integer entidade);
    
    Departamento obterPorIdDepartamento(Integer id);
}

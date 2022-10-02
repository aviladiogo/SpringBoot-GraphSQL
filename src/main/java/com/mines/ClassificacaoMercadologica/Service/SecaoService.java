package com.mines.ClassificacaoMercadologica.Service;

import java.util.List;
import com.mines.ClassificacaoMercadologica.Model.Secao;

public interface SecaoService {
    
    List<Secao> obterTodosSecoes(Integer entidade);

    List<Secao> obterSecoesPorDepartamento(Integer entidade, Integer departamento);
    
    Secao obterPorIdSecao(Integer id);
}

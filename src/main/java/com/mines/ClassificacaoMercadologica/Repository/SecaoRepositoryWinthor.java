package com.mines.ClassificacaoMercadologica.Repository;

import java.util.List;
import com.mines.ClassificacaoMercadologica.Model.Secao;

public interface SecaoRepositoryWinthor {
    
    List<Secao> obterTodosSecoes(Integer entidade);

    List<Secao> obterSecoesPorDepartamento(Integer entidade, Integer departamento);
    
    Secao obterPorIdSecao(Integer id);

}


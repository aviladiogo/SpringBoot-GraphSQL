package com.mines.ClassificacaoMercadologica.Repository;

import java.util.List;
import com.mines.ClassificacaoMercadologica.Model.Secao;

public interface SecaoRepository {
    
    List<Secao> obterTodosSecoes(Integer entidade);

    List<Secao> obterSecoesPorDepartamento(Integer entidade, Integer departamento);
    
    Secao obterPorIdSecao(Integer id);

    Secao salvarSecao(Secao secao);

    Secao atualizarSecao(Secao secao);

    Boolean deletarSecao(Secao secao);
}

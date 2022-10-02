package com.mines.Produto.Repository;

import java.util.List;
import com.mines.Produto.Model.Apresentacao;

public interface ApresentacaoRepositoryWinthor {

    List<Apresentacao> obterTodosApresentacoes(Integer entidade);
    
    Apresentacao obterPorIdApresentacao(Integer id);
}
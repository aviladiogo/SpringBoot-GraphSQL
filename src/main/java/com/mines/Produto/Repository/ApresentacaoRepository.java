package com.mines.Produto.Repository;

import java.util.List;
import com.mines.Produto.Model.Apresentacao;

public interface ApresentacaoRepository {

    List<Apresentacao> obterTodosApresentacoes(Integer entidade);
    
    Apresentacao obterPorIdApresentacao(Integer id);

    Apresentacao salvarApresentacao(Apresentacao apresentacao);

    Apresentacao atualizarApresentacao(Apresentacao apresentacao);

    Boolean deletarApresentacao(Apresentacao apresentacao);   
}

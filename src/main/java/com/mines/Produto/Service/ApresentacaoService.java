package com.mines.Produto.Service;

import java.util.List;
import com.mines.Produto.Model.Apresentacao;

public interface ApresentacaoService {

    List<Apresentacao> obterTodosApresentacoes(Integer entidade);
    
    Apresentacao obterPorIdApresentacao(Integer id);
}

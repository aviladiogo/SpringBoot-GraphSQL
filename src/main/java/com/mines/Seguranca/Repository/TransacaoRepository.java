package com.mines.Seguranca.Repository;

import java.util.List;
import com.mines.Seguranca.Model.Transacao;

public interface TransacaoRepository {
    
    List<Transacao> obterTodosTransacoes(Integer entidade);
    
    Transacao obterPorIdTransacao(Integer id);

    Transacao salvarTransacao(Transacao transacao);

    Transacao atualizarTransacao(Transacao transacao);

    Boolean deletarTransacao(Transacao transacao);
}

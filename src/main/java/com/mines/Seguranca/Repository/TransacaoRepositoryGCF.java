package com.mines.Seguranca.Repository;

import java.util.List;
import com.mines.Seguranca.Model.Transacao;

public interface TransacaoRepositoryGCF {
    
    List<Transacao> obterTodosTransacoes(Integer entidade);
    
    Transacao obterPorIdTransacao(Integer id);

}
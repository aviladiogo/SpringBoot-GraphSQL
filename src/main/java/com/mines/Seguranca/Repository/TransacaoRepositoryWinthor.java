package com.mines.Seguranca.Repository;

import java.util.List;
import com.mines.Seguranca.Model.Transacao;

public interface TransacaoRepositoryWinthor {
    
    List<Transacao> obterTodosTransacoes(Integer entidade);
    
    Transacao obterPorIdTransacao(Integer id);

}
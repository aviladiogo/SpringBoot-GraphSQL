package com.mines.Seguranca.Service;

import java.util.List;
import com.mines.Seguranca.Model.Transacao;

public interface TransacaoService {
    
    List<Transacao> obterTodosTransacoes(Integer entidade);
    
    Transacao obterPorIdTransacao(Integer id);

}
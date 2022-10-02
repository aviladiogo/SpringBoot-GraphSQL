package com.mines.EntidadeJuridica.Repository;

import java.util.List;
import com.mines.EntidadeJuridica.Model.PessoaJuridica;

public interface PessoaJuridicaRepository {

    List<PessoaJuridica> obterTodosPessoasJuridica(Integer entidade);
    
    PessoaJuridica obterPorIdPessoaJuridica(Integer id);

    PessoaJuridica salvarPessoaJuridica(PessoaJuridica pessoaJuridica);

    PessoaJuridica atualizarPessoaJuridica(PessoaJuridica pessoaJuridica);

    Boolean deletarPessoaJuridica(PessoaJuridica pessoaJuridica);
    
}

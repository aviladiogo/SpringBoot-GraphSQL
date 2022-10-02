package com.mines.EntidadeJuridica.Repository;

import java.util.List;
import com.mines.EntidadeJuridica.Model.PessoaFisica;

public interface PessoaFisicaRepository {
    
    List<PessoaFisica> obterTodosPessoasFisica(Integer entidade);
    
    PessoaFisica obterPorIdPessoaFisica(Integer id);

    PessoaFisica salvarPessoaFisica(PessoaFisica pessoaFisica);

    PessoaFisica atualizarPessoaFisica(PessoaFisica pessoaFisica);

    Boolean deletarPessoaFisica(PessoaFisica pessoaFisica);
}

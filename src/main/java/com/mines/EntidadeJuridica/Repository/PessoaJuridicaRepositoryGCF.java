package com.mines.EntidadeJuridica.Repository;

import java.util.List;
import com.mines.EntidadeJuridica.Model.PessoaJuridica;

public interface PessoaJuridicaRepositoryGCF {

    List<PessoaJuridica> obterTodosPessoasJuridica(Integer entidade);
    
    PessoaJuridica obterPorIdPessoaJuridica(Integer id);
    
}

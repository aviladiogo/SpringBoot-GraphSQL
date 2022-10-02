package com.mines.EntidadeJuridica.Service;

import java.util.List;
import com.mines.EntidadeJuridica.Model.PessoaJuridica;

public interface PessoaJuridicaService {

    List<PessoaJuridica> obterTodosPessoasJuridica(Integer entidade);
    
    PessoaJuridica obterPorIdPessoaJuridica(Integer id);
    
}


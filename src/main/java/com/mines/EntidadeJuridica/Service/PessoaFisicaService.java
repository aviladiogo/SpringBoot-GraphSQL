package com.mines.EntidadeJuridica.Service;

import java.util.List;
import com.mines.EntidadeJuridica.Model.PessoaFisica;

public interface PessoaFisicaService {
    
    List<PessoaFisica> obterTodosPessoasFisica(Integer entidade);
    
    PessoaFisica obterPorIdPessoaFisica(Integer id);

}
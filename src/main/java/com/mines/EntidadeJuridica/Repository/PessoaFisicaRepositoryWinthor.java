package com.mines.EntidadeJuridica.Repository;

import java.util.List;
import com.mines.EntidadeJuridica.Model.PessoaFisica;

public interface PessoaFisicaRepositoryWinthor {
    
    List<PessoaFisica> obterTodosPessoasFisica(Integer entidade);
    
    PessoaFisica obterPorIdPessoaFisica(Integer id);

}

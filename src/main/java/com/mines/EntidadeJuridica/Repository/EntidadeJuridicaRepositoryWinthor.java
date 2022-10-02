package com.mines.EntidadeJuridica.Repository;

import java.util.List;
import com.mines.EntidadeJuridica.Model.EntidadeJuridica;

public interface EntidadeJuridicaRepositoryWinthor {
    
    List<EntidadeJuridica> obterTodosEntidadesJuridicas(Integer entidade);
    
    EntidadeJuridica obterPorIdEntidadeJuridica(Integer id);

}

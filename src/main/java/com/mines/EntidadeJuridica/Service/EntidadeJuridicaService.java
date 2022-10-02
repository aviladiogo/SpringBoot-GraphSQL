package com.mines.EntidadeJuridica.Service;

import java.util.List;
import com.mines.EntidadeJuridica.Model.EntidadeJuridica;

public interface EntidadeJuridicaService {
    
    List<EntidadeJuridica> obterTodosEntidadesJuridicas(Integer entidade);
    
    EntidadeJuridica obterPorIdEntidadeJuridica(Integer id);

}

package com.mines.EntidadeJuridica.Repository;

import java.util.List;
import com.mines.EntidadeJuridica.Model.EntidadeJuridica;

public interface EntidadeJuridicaRepository {
    
    List<EntidadeJuridica> obterTodosEntidadesJuridicas(Integer entidade);
    
    EntidadeJuridica obterPorIdEntidadeJuridica(Integer id);

    EntidadeJuridica salvarEntidadeJuridica(EntidadeJuridica entidadeJuridica);

    EntidadeJuridica atualizarEntidadeJuridica(EntidadeJuridica entidadeJuridica);

    Boolean deletarEntidadeJuridica(EntidadeJuridica entidadeJuridica);
}

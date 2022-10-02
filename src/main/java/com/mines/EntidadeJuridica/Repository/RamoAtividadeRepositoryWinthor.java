package com.mines.EntidadeJuridica.Repository;

import java.util.List;
import com.mines.EntidadeJuridica.Model.RamoAtividade;

public interface RamoAtividadeRepositoryWinthor {
    
    List<RamoAtividade> obterTodosRamoAtividades(Integer entidade);
    
    RamoAtividade obterPorIdRamoAtividade(Integer id);

}
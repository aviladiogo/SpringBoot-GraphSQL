package com.mines.EntidadeJuridica.Repository;

import java.util.List;
import com.mines.EntidadeJuridica.Model.RamoAtividade;

public interface RamoAtividadeRepository {
    
    List<RamoAtividade> obterTodosRamoAtividades(Integer entidade);
    
    RamoAtividade obterPorIdRamoAtividade(Integer id);

    RamoAtividade salvarRamoAtividade(RamoAtividade ramoAtividade);

    RamoAtividade atualizarRamoAtividade(RamoAtividade ramoAtividade);

    Boolean deletarRamoAtividade(RamoAtividade ramoAtividade);
}
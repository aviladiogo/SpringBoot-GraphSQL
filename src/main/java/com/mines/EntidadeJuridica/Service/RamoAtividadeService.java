package com.mines.EntidadeJuridica.Service;

import java.util.List;
import com.mines.EntidadeJuridica.Model.RamoAtividade;

public interface RamoAtividadeService {
    
    List<RamoAtividade> obterTodosRamoAtividades(Integer entidade);
    
    RamoAtividade obterPorIdRamoAtividade(Integer id);

}
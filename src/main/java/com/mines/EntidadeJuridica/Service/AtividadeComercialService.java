package com.mines.EntidadeJuridica.Service;

import java.util.List;
import com.mines.EntidadeJuridica.Model.AtividadeComercial;

public interface AtividadeComercialService {
    
    List<AtividadeComercial> obterTodosAtividadesComercial(Integer entidade);

    public List<AtividadeComercial> obterTodosAtividadesComercialPorRamoAtividade(Integer entidade,
            Integer ramoAtividade);
    
    AtividadeComercial obterPorIdAtividadeComercial(Integer id);

}

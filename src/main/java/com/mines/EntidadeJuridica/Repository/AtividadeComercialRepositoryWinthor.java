package com.mines.EntidadeJuridica.Repository;

import java.util.List;
import com.mines.EntidadeJuridica.Model.AtividadeComercial;

public interface AtividadeComercialRepositoryWinthor {
    
    List<AtividadeComercial> obterTodosAtividadesComercial(Integer entidade);

    public List<AtividadeComercial> obterTodosAtividadesComercialPorRamoAtividade(Integer entidade,
            Integer ramoAtividade);
    
    AtividadeComercial obterPorIdAtividadeComercial(Integer id);

}

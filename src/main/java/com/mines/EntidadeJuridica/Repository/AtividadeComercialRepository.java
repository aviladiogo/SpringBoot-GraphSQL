package com.mines.EntidadeJuridica.Repository;

import java.util.List;
import com.mines.EntidadeJuridica.Model.AtividadeComercial;

public interface AtividadeComercialRepository {
    
    List<AtividadeComercial> obterTodosAtividadesComercial(Integer entidade);

    public List<AtividadeComercial> obterTodosAtividadesComercialPorRamoAtividade(Integer entidade,
            Integer ramoAtividade);
    
    AtividadeComercial obterPorIdAtividadeComercial(Integer id);

    AtividadeComercial salvarAtividadeComercial(AtividadeComercial atividadeComercial);

    AtividadeComercial atualizarAtividadeComercial(AtividadeComercial atividadeComercial);

    Boolean deletarAtividadeComercial(AtividadeComercial atividadeComercial);
}

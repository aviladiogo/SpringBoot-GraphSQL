package com.mines.PrecoEOferta.Repository;

import java.util.List;
import com.mines.PrecoEOferta.Model.PoliticaItemOferta;

public interface PoliticaItemOfertaRepository {
    
    List<PoliticaItemOferta> obterTodosPoliticaItensOferta(Integer entidade);
    
    PoliticaItemOferta obterPorIdPoliticaItemOferta(Integer id);

    PoliticaItemOferta salvarPoliticaItemOferta(PoliticaItemOferta politicaItemOferta);

    PoliticaItemOferta atualizarPoliticaItemOferta(PoliticaItemOferta politicaItemOferta);

    Boolean deletarPoliticaItemOferta(PoliticaItemOferta politicaItemOferta);
}

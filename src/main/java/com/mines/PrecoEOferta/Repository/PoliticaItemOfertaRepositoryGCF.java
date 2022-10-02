package com.mines.PrecoEOferta.Repository;

import java.util.List;
import com.mines.PrecoEOferta.Model.PoliticaItemOferta;

public interface PoliticaItemOfertaRepositoryGCF {
    
    List<PoliticaItemOferta> obterTodosPoliticaItensOferta(Integer entidade);
    
    PoliticaItemOferta obterPorIdPoliticaItemOferta(Integer id);
}

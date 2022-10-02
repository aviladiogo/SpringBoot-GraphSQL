package com.mines.PrecoEOferta.Service;

import java.util.List;
import com.mines.PrecoEOferta.Model.PoliticaItemOferta;

public interface PoliticaItemOfertaService {
    
    List<PoliticaItemOferta> obterTodosPoliticaItensOferta(Integer entidade);
    
    PoliticaItemOferta obterPorIdPoliticaItemOferta(Integer id);
}
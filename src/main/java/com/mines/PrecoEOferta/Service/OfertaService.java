package com.mines.PrecoEOferta.Service;

import java.util.List;
import com.mines.PrecoEOferta.Model.Oferta;

public interface OfertaService {
    
    List<Oferta> obterTodosOfertas(Integer entidade);
    
    Oferta obterPorIdOferta(Integer id);

}
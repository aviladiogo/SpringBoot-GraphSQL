package com.mines.PrecoEOferta.Repository;

import java.util.List;
import com.mines.PrecoEOferta.Model.Oferta;

public interface OfertaRepositoryWinthor {
    
    List<Oferta> obterTodosOfertas(Integer entidade);
    
    Oferta obterPorIdOferta(Integer id);

}
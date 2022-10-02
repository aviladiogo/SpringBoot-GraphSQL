package com.mines.PrecoEOferta.Repository;

import java.util.List;
import com.mines.PrecoEOferta.Model.Oferta;

public interface OfertaRepository {
    
    List<Oferta> obterTodosOfertas(Integer entidade);
    
    Oferta obterPorIdOferta(Integer id);

    Oferta salvarOferta(Oferta oferta);

    Oferta atualizarOferta(Oferta oferta);

    Boolean deletarOferta(Oferta oferta);
}

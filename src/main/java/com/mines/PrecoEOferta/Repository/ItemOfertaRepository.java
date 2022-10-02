package com.mines.PrecoEOferta.Repository;

import java.util.List;
import com.mines.PrecoEOferta.Model.ItemOferta;

public interface ItemOfertaRepository {

    List<ItemOferta> obterTodosItensOferta(Integer entidade);
    
    ItemOferta obterPorIdItemOferta(Integer id);

    ItemOferta salvarItemOferta(ItemOferta itemOferta);

    ItemOferta atualizarItemOferta(ItemOferta itemOferta);

    Boolean deletarItemOferta(ItemOferta itemOferta);
    
}

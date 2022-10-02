package com.mines.PrecoEOferta.Repository;

import java.util.List;
import com.mines.PrecoEOferta.Model.ItemOferta;

public interface ItemOfertaRepositoryGCF {

    List<ItemOferta> obterTodosItensOferta(Integer entidade);
    
    ItemOferta obterPorIdItemOferta(Integer id);
    
}

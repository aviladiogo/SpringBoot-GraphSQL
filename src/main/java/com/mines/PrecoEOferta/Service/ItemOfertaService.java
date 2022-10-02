package com.mines.PrecoEOferta.Service;

import java.util.List;
import com.mines.PrecoEOferta.Model.ItemOferta;

public interface ItemOfertaService {

    List<ItemOferta> obterTodosItensOferta(Integer entidade);
    
    ItemOferta obterPorIdItemOferta(Integer id);
    
}

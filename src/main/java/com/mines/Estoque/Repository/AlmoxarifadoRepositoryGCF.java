package com.mines.Estoque.Repository;

import java.util.List;
import com.mines.Estoque.Model.Almoxarifado;

public interface AlmoxarifadoRepositoryGCF {
    
    List<Almoxarifado> obterTodosAlmoxarifados(Integer entidade);
    
    Almoxarifado obterPorIdAlmoxarifado(Integer id);
}

package com.mines.Estoque.Service;

import java.util.List;
import com.mines.Estoque.Model.Almoxarifado;

public interface AlmoxarifadoService {
    
    List<Almoxarifado> obterTodosAlmoxarifados(Integer entidade);
    
    Almoxarifado obterPorIdAlmoxarifado(Integer id);
}

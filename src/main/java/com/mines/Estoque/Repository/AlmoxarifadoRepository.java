package com.mines.Estoque.Repository;

import java.util.List;
import com.mines.Estoque.Model.Almoxarifado;

public interface AlmoxarifadoRepository {
    
    List<Almoxarifado> obterTodosAlmoxarifados(Integer entidade);
    
    Almoxarifado obterPorIdAlmoxarifado(Integer id);

    Almoxarifado salvarAlmoxarifado(Almoxarifado almoxarifado);

    Almoxarifado atualizarAlmoxarifado(Almoxarifado almoxarifado);

    Boolean deletarAlmoxarifado(Almoxarifado almoxarifado);
}

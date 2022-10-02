package com.mines.Empresa.Repository;

import java.util.List;
import com.mines.Empresa.Model.GrupoCompra;

public interface GrupoCompraRepositoryWinthor {
    
    List<GrupoCompra> obterTodosGrupoCompras(Integer entidade);
    
    GrupoCompra obterPorIdGrupoCompra(Integer id);

}

package com.mines.Empresa.Repository;

import java.util.List;
import com.mines.Empresa.Model.GrupoCompra;

public interface GrupoCompraRepositoryGCF {
    
    List<GrupoCompra> obterTodosGrupoCompras(Integer entidade);
    
    GrupoCompra obterPorIdGrupoCompra(Integer id);

}

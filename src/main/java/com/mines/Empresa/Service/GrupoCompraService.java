package com.mines.Empresa.Service;

import java.util.List;
import com.mines.Empresa.Model.GrupoCompra;

public interface GrupoCompraService {
    
    List<GrupoCompra> obterTodosGrupoCompras(Integer entidade);
    
    GrupoCompra obterPorIdGrupoCompra(Integer id);

}

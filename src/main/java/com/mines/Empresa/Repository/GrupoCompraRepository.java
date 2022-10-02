package com.mines.Empresa.Repository;

import java.util.List;
import com.mines.Empresa.Model.GrupoCompra;

public interface GrupoCompraRepository {
    
    List<GrupoCompra> obterTodosGrupoCompras(Integer entidade);
    
    GrupoCompra obterPorIdGrupoCompra(Integer id);

    GrupoCompra salvarGrupoCompra(GrupoCompra grupoCompra);

    GrupoCompra atualizarGrupoCompra(GrupoCompra grupoCompra);

    Boolean deletarGrupoCompra(GrupoCompra grupoCompra);
}

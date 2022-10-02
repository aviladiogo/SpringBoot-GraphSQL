package com.mines.AgendaCompra.Repository;

import java.util.List;
import com.mines.AgendaCompra.Model.AgendaCompra;
import com.mines.AgendaCompra.Model.FiltroAgendaCompraInput;

public interface AgendaCompraRepositoryGCF {

    List<AgendaCompra> obterTodosAgendaCompra(Integer entidade);
    
    AgendaCompra obterPorIdAgendaCompra(Integer id);

    public List<AgendaCompra> obterTodosAgendaCompraPorFiltro(FiltroAgendaCompraInput filtroAgendaCompraInput);
    
}

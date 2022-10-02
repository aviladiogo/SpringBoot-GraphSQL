package com.mines.AgendaCompra.Service;

import java.text.ParseException;
import java.util.List;
import com.mines.AgendaCompra.Model.AgendaCompra;
import com.mines.AgendaCompra.Model.FiltroAgendaCompraInput;

public interface AgendaCompraService {
    
    List<AgendaCompra> obterTodosAgendaCompra(Integer entidade);
    
    AgendaCompra obterPorIdAgendaCompra(Integer id);

    public List<AgendaCompra> obterTodosAgendaCompraPorFiltro(FiltroAgendaCompraInput filtroAgendaCompraInput) throws ParseException;

}

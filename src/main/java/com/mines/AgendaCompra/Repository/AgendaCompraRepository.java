package com.mines.AgendaCompra.Repository;

import java.text.ParseException;
import java.util.List;
import com.mines.AgendaCompra.Model.AgendaCompra;
import com.mines.AgendaCompra.Model.FiltroAgendaCompraInput;

public interface AgendaCompraRepository {

    List<AgendaCompra> obterTodosAgendaCompra(Integer entidade);

    public List<AgendaCompra> obterTodosAgendaCompraPorFiltro(FiltroAgendaCompraInput filtroAgendaCompraInput) throws ParseException;
    
    AgendaCompra obterPorIdAgendaCompra(Integer id);

    AgendaCompra salvarAgendaCompra(AgendaCompra agendaCompra);

    void salvarAgendaCompraFornecedor(Integer agendaCompra, List<Integer> listaFornecedores);

    AgendaCompra atualizarAgendaCompra(AgendaCompra agendaCompra);

    Boolean deletarAgendaCompra(AgendaCompra agendaCompra);

    Boolean deletarAgendaCompraFornecedor(AgendaCompra agendaCompra);
    
}

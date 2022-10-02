package com.mines.Estoque.Service;

import java.util.List;
import com.mines.Estoque.Model.EstoqueHistorico;

public interface EstoqueHistoricoService {
    
    List<EstoqueHistorico> obterTodosEstoquesHistorico(Integer entidade);

    EstoqueHistorico obterPorIdEstoqueHistorico(Integer id);
}

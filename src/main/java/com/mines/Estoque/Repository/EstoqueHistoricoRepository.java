package com.mines.Estoque.Repository;

import java.util.List;
import com.mines.Estoque.Model.EstoqueHistorico;

public interface EstoqueHistoricoRepository {
    
    List<EstoqueHistorico> obterTodosEstoquesHistorico(Integer entidade);

    EstoqueHistorico obterPorIdEstoqueHistorico(Integer id);

    EstoqueHistorico salvarEstoqueHistorico(EstoqueHistorico estoqueHistorico);

    EstoqueHistorico atualizarEstoqueHistorico(EstoqueHistorico estoqueHistorico);

    Boolean deletarEstoqueHistorico(EstoqueHistorico estoqueHistorico);
}

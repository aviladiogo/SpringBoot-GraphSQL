package com.mines.Estoque.Repository;

import java.util.List;
import com.mines.Estoque.Model.EstoqueHistorico;

public interface EstoqueHistoricoRepositoryWinthor {
    
    List<EstoqueHistorico> obterTodosEstoquesHistorico(Integer entidade);

    EstoqueHistorico obterPorIdEstoqueHistorico(Integer id);
}

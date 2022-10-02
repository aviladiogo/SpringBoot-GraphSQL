package com.mines.Fornecedor.Repository;

import java.util.List;
import com.mines.Fornecedor.Model.TipoFrete;

public interface TipoFreteRepository {
    
    List<TipoFrete> obterTodosTiposFrete(Integer entidade);
    
    TipoFrete obterPorIdTipoFrete(Integer id);

    TipoFrete salvarTipoFrete(TipoFrete tipoFrete);

    TipoFrete atualizarTipoFrete(TipoFrete tipoFrete);

    Boolean deletarTipoFrete(TipoFrete tipoFrete);
}

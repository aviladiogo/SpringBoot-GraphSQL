package com.mines.Fornecedor.Repository;

import java.util.List;
import com.mines.Fornecedor.Model.TipoFrete;

public interface TipoFreteRepositoryWinthor {
    
    List<TipoFrete> obterTodosTiposFrete(Integer entidade);
    
    TipoFrete obterPorIdTipoFrete(Integer id);
    
}

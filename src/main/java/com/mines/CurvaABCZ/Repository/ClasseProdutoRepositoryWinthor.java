package com.mines.CurvaABCZ.Repository;

import java.util.List;
import com.mines.CurvaABCZ.Model.ClasseProduto;

public interface ClasseProdutoRepositoryWinthor {
    
    List<ClasseProduto> obterTodosClasseProduto(Integer entidade);
    
    ClasseProduto obterPorIdClasseProduto(Integer id);

}

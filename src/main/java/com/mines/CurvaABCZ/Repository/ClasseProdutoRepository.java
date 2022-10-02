package com.mines.CurvaABCZ.Repository;

import java.util.List;
import com.mines.CurvaABCZ.Model.ClasseProduto;

public interface ClasseProdutoRepository {
    
    List<ClasseProduto> obterTodosClasseProduto(Integer entidade);
    
    ClasseProduto obterPorIdClasseProduto(Integer id);

    ClasseProduto salvarClasseProduto(ClasseProduto classeProduto);

    ClasseProduto atualizarClasseProduto(ClasseProduto classeProduto);

    Boolean deletarClasseProduto(ClasseProduto classeProduto);
}

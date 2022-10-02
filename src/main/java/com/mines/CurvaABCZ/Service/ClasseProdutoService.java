package com.mines.CurvaABCZ.Service;

import java.util.List;
import com.mines.CurvaABCZ.Model.ClasseProduto;

public interface ClasseProdutoService {
    
    List<ClasseProduto> obterTodosClasseProduto(Integer entidade);
    
    ClasseProduto obterPorIdClasseProduto(Integer id);

}

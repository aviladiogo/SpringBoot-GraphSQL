package com.mines.Produto.Service;

import java.util.List;
import com.mines.Produto.Model.Produto;

public interface ProdutoService {
    
    List<Produto> obterTodosProdutos(Integer entidade);

    List<Produto> obterProdutosPorFornecedores(Integer entidade, List<Integer> fornecedores);
    
    Produto obterPorIdProduto(Integer id);

}
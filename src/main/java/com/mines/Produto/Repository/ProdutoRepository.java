package com.mines.Produto.Repository;

import java.util.List;
import com.mines.Produto.Model.Produto;

public interface ProdutoRepository {
    
    List<Produto> obterTodosProdutos(Integer entidade);

    List<Produto> obterProdutosPorFornecedores(Integer entidade, List<Integer> fornecedores);
    
    Produto obterPorIdProduto(Integer id);

    Produto salvarProduto(Produto produto);

    Produto atualizarProduto(Produto produto);

    Boolean deletarProduto(Produto produto);
}

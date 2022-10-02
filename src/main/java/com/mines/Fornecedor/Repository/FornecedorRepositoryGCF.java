package com.mines.Fornecedor.Repository;

import java.util.List;
import com.mines.Fornecedor.Model.Fornecedor;

public interface FornecedorRepositoryGCF {
    
    List<Fornecedor> obterTodosFornecedores(Integer entidade);

    List<Fornecedor> obterFornecedoresPorCriterio(Integer entidade, String criterioBusca);

    List<Fornecedor> obterTodosFornecedoresPorTermo(Integer entidade, String termo);

    List<Fornecedor> obterTodosFornecedoresPorProduto(Integer entidade, Integer produto);

    List<Fornecedor> obterFornecedoresPorDepartamento(Integer entidade, Integer departamento);

    List<Fornecedor> obterFornecedoresPorSecao(Integer entidade, Integer secao);
    
    List<Fornecedor> obterFornecedoresPorCategoria(Integer entidade, Integer categoria);

    List<Fornecedor> obterFornecedoresPorSubCategoria(Integer entidade, Integer subCategoria);

    Fornecedor obterPorIdFornecedor(Integer id);

}

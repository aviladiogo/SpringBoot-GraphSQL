package com.mines.Fornecedor.Repository;

import java.util.List;
import com.mines.Fornecedor.Model.Fornecedor;

public interface FornecedorRepository {
    
    List<Fornecedor> obterTodosFornecedores(Integer entidade);

    List<Fornecedor> obterTodosFornecedoresPorTermo(Integer entidade, String termo);

    List<Fornecedor> obterTodosFornecedoresPorProduto(Integer entidade, Integer produto);

    List<Fornecedor> obterFornecedoresPorCriterio(Integer entidade, String criterioBusca);

    List<Fornecedor> obterFornecedoresPorDepartamento(Integer entidade, Integer departamento);

    List<Fornecedor> obterFornecedoresPorSecao(Integer entidade, Integer secao);

    List<Fornecedor> obterFornecedoresPorCategoria(Integer entidade, Integer categoria);

    List<Fornecedor> obterFornecedoresPorSubCategoria(Integer entidade, Integer subCategoria);
    
    Fornecedor obterPorIdFornecedor(Integer id);

    Fornecedor salvarFornecedor(Fornecedor fornecedor);

    public void salvarFornecedorProduto(Integer fornecedor, List<Integer> listaProdutos);

    Fornecedor atualizarFornecedor(Fornecedor fornecedor);

    Boolean deletarFornecedor(Fornecedor fornecedor);

    public Boolean deletarFornecedorProduto(Fornecedor fornecedor);
}

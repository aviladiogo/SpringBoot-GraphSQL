package com.mines.Produto.Service;

import java.util.List;
import com.mines.Produto.Model.Produto;
import com.mines.Produto.Repository.ProdutoRepository;
import com.mines.Produto.Repository.ProdutoRepositoryGCF;
import com.mines.Produto.Repository.ProdutoRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService{
    
    @Autowired
    private ProdutoRepository produtoRepo;

    @Autowired 
    private ProdutoRepositoryWinthor produtoRepoWinthor;

    @Autowired
    private ProdutoRepositoryGCF produtoRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<Produto> obterTodosProdutos(Integer entidade) {

        List<Produto> produto = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                produto = produtoRepo.obterTodosProdutos(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                produto = produtoRepoWinthor.obterTodosProdutos(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                produto = produtoRepoGCF.obterTodosProdutos(entidade);
                break;
            }
        }

        return produto;
    }

    @Override
    public List<Produto> obterProdutosPorFornecedores(Integer entidade, List<Integer> fornecedores) {

        List<Produto> produto = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                produto = produtoRepo.obterProdutosPorFornecedores(entidade, fornecedores);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                produto = produtoRepoWinthor.obterProdutosPorFornecedores(entidade, fornecedores);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                produto = produtoRepoGCF.obterProdutosPorFornecedores(entidade, fornecedores);
                break;
            }
        }

        return produto;
    }

    @Override
    public Produto obterPorIdProduto(Integer id) {

        Produto produto = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                produto = produtoRepo.obterPorIdProduto(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                produto = produtoRepoWinthor.obterPorIdProduto(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                produto = produtoRepoGCF.obterPorIdProduto(id);
                break;
            }
        }

        return produto;
    }
}


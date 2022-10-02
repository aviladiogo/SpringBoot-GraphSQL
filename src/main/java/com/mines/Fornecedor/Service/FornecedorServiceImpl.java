package com.mines.Fornecedor.Service;

import java.util.List;
import com.mines.Fornecedor.Model.Fornecedor;
import com.mines.Fornecedor.Repository.FornecedorRepository;
import com.mines.Fornecedor.Repository.FornecedorRepositoryGCF;
import com.mines.Fornecedor.Repository.FornecedorRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FornecedorServiceImpl implements FornecedorService{

    @Autowired
    private FornecedorRepository fornecedorRepo;

    @Autowired
    private FornecedorRepositoryWinthor fornecedorRepoWinthor;
    
    @Autowired
    private FornecedorRepositoryGCF fornecedorRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<Fornecedor> obterTodosFornecedores(Integer entidade) {

        List<Fornecedor> fornecedores = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                fornecedores = fornecedorRepo.obterTodosFornecedores(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                fornecedores = fornecedorRepoWinthor.obterTodosFornecedores(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                fornecedores = fornecedorRepoGCF.obterTodosFornecedores(entidade);
                break;
            }
        }

        return fornecedores;
    }

    @Override
    public List<Fornecedor> obterTodosFornecedoresPorTermo(Integer entidade, String termo) {

        List<Fornecedor> fornecedores = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                fornecedores = fornecedorRepo.obterTodosFornecedoresPorTermo(entidade, termo);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                fornecedores = fornecedorRepoWinthor.obterTodosFornecedoresPorTermo(entidade, termo);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                fornecedores = fornecedorRepoGCF.obterTodosFornecedoresPorTermo(entidade, termo);
                break;
            }
        }

        return fornecedores;
    }

    @Override
    public List<Fornecedor> obterTodosFornecedoresPorProduto(Integer entidade, Integer produto) {
        List<Fornecedor> fornecedores = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                fornecedores = fornecedorRepo.obterTodosFornecedoresPorProduto(entidade, produto);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                fornecedores = fornecedorRepoWinthor.obterTodosFornecedoresPorProduto(entidade, produto);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                fornecedores = fornecedorRepoGCF.obterTodosFornecedoresPorProduto(entidade, produto);
                break;
            }
        }

        return fornecedores;
    }

    @Override
    public List<Fornecedor> obterFornecedoresPorCriterio(Integer entidade, String criterioBusca) {

        List<Fornecedor> fornecedores = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                fornecedores = fornecedorRepo.obterFornecedoresPorCriterio(entidade, criterioBusca);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                fornecedores = fornecedorRepoWinthor.obterFornecedoresPorCriterio(entidade, criterioBusca);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                fornecedores = fornecedorRepoGCF.obterFornecedoresPorCriterio(entidade, criterioBusca);
                break;
            }
        }

        return fornecedores;
    }

    @Override
    public List<Fornecedor> obterFornecedoresPorDepartamento(Integer entidade, Integer departamento) {

        List<Fornecedor> fornecedores = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                fornecedores = fornecedorRepo.obterFornecedoresPorDepartamento(entidade, departamento);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                fornecedores = fornecedorRepoWinthor.obterFornecedoresPorDepartamento(entidade, departamento);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                fornecedores = fornecedorRepoGCF.obterFornecedoresPorDepartamento(entidade, departamento);
                break;
            }
        }

        return fornecedores;
    }

    @Override
    public List<Fornecedor> obterFornecedoresPorSecao(Integer entidade, Integer secao) {

        List<Fornecedor> fornecedores = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                fornecedores = fornecedorRepo.obterFornecedoresPorSecao(entidade, secao);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                fornecedores = fornecedorRepoWinthor.obterFornecedoresPorSecao(entidade, secao);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                fornecedores = fornecedorRepoGCF.obterFornecedoresPorSecao(entidade, secao);
                break;
            }
        }

        return fornecedores;
    }

    @Override
    public List<Fornecedor> obterFornecedoresPorCategoria(Integer entidade, Integer categoria) {

        List<Fornecedor> fornecedores = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                fornecedores = fornecedorRepo.obterFornecedoresPorCategoria(entidade, categoria);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                fornecedores = fornecedorRepoWinthor.obterFornecedoresPorCategoria(entidade, categoria);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                fornecedores = fornecedorRepoGCF.obterFornecedoresPorCategoria(entidade, categoria);
                break;
            }
        }

        return fornecedores;
    }

    @Override
    public List<Fornecedor> obterFornecedoresPorSubCategoria(Integer entidade, Integer subCategoria) {

        List<Fornecedor> fornecedores = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                fornecedores = fornecedorRepo.obterFornecedoresPorSubCategoria(entidade, subCategoria);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                fornecedores = fornecedorRepoWinthor.obterFornecedoresPorSubCategoria(entidade, subCategoria);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                fornecedores = fornecedorRepoGCF.obterFornecedoresPorSubCategoria(entidade, subCategoria);
                break;
            }
        }

        return fornecedores;
    }

    @Override
    public Fornecedor obterPorIdFornecedor(Integer id) {

        Fornecedor fornecedor = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                fornecedor = fornecedorRepo.obterPorIdFornecedor(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                fornecedor = fornecedorRepoWinthor.obterPorIdFornecedor(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                fornecedor = fornecedorRepoGCF.obterPorIdFornecedor(id);
                break;
            }
        }

        return fornecedor;
    }
    
}

package com.mines.Produto.Service;

import java.util.List;
import com.mines.Produto.Model.Unidade;
import com.mines.Produto.Repository.UnidadeRepository;
import com.mines.Produto.Repository.UnidadeRepositoryGCF;
import com.mines.Produto.Repository.UnidadeRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UnidadeServiceImpl implements UnidadeService{
    
    @Autowired
    private UnidadeRepository unidadeRepo;

    @Autowired 
    private UnidadeRepositoryWinthor unidadeRepoWinthor;

    @Autowired
    private UnidadeRepositoryGCF unidadeRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<Unidade> obterTodosUnidades(Integer entidade) {

        List<Unidade> unidade = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                unidade = unidadeRepo.obterTodosUnidades(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                unidade = unidadeRepoWinthor.obterTodosUnidades(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                unidade = unidadeRepoGCF.obterTodosUnidades(entidade);
                break;
            }
        }

        return unidade;
    }

    @Override
    public Unidade obterPorIdUnidade(Integer id) {

        Unidade unidade = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                unidade = unidadeRepo.obterPorIdUnidade(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                unidade = unidadeRepoWinthor.obterPorIdUnidade(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                unidade = unidadeRepoGCF.obterPorIdUnidade(id);
                break;
            }
        }

        return unidade;
    }
}


package com.mines.Estoque.Service;

import java.util.List;
import com.mines.Estoque.Model.Estoque;
import com.mines.Estoque.Repository.EstoqueRepository;
import com.mines.Estoque.Repository.EstoqueRepositoryGCF;
import com.mines.Estoque.Repository.EstoqueRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EstoqueServiceImpl implements EstoqueService{

    @Autowired
    private EstoqueRepository estoqueRepo;

    @Autowired 
    private EstoqueRepositoryWinthor estoqueRepoWinthor;

    @Autowired
    private EstoqueRepositoryGCF estoqueRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<Estoque> obterTodosEstoques(Integer entidade) {

        List<Estoque> estoque = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                estoque = estoqueRepo.obterTodosEstoques(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                estoque = estoqueRepoWinthor.obterTodosEstoques(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                estoque = estoqueRepoGCF.obterTodosEstoques(entidade);
                break;
            }
        }

        return estoque;
    }

    @Override
    public Estoque obterPorIdEstoque(Integer fornecedor) {

        Estoque estoque = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                estoque = estoqueRepo.obterPorIdEstoque(fornecedor);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                estoque = estoqueRepoWinthor.obterPorIdEstoque(fornecedor);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                estoque = estoqueRepoGCF.obterPorIdEstoque(fornecedor);
                break;
            }
        }

        return estoque;
    }
    
}

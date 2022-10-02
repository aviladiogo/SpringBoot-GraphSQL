package com.mines.Fornecedor.Service;

import java.util.List;
import com.mines.Fornecedor.Model.FornecedorFilial;
import com.mines.Fornecedor.Repository.FornecedorFilialRepository;
import com.mines.Fornecedor.Repository.FornecedorFilialRepositoryGCF;
import com.mines.Fornecedor.Repository.FornecedorFilialRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FornecedorFilialServiceImpl implements FornecedorFilialService{

    @Autowired
    private FornecedorFilialRepository fornecedorFilialRepo;

    @Autowired 
    private FornecedorFilialRepositoryWinthor fornecedorFilialRepoWinthor;

    @Autowired
    private FornecedorFilialRepositoryGCF fornecedorFilialRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<FornecedorFilial> obterTodosFornecedoresFilial(Integer entidade) {

        List<FornecedorFilial> fornecedoresFilial = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                fornecedoresFilial = fornecedorFilialRepo.obterTodosFornecedoresFilial(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                fornecedoresFilial = fornecedorFilialRepoWinthor.obterTodosFornecedoresFilial(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                fornecedoresFilial = fornecedorFilialRepoGCF.obterTodosFornecedoresFilial(entidade);
                break;
            }
        }

        return fornecedoresFilial;
    }

    @Override
    public FornecedorFilial obterPorIdFornecedorFilial(Integer fornecedor, Integer filial) {

        FornecedorFilial fornecedorFilial = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                fornecedorFilial = fornecedorFilialRepo.obterPorIdFornecedorFilial(fornecedor, filial);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                fornecedorFilial = fornecedorFilialRepoWinthor.obterPorIdFornecedorFilial(fornecedor, filial);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                fornecedorFilial = fornecedorFilialRepoGCF.obterPorIdFornecedorFilial(fornecedor, filial);
                break;
            }
        }

        return fornecedorFilial;
    }
    
}

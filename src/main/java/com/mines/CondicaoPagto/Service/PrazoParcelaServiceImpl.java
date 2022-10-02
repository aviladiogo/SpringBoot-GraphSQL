package com.mines.CondicaoPagto.Service;

import java.util.List;
import com.mines.CondicaoPagto.Model.PrazoParcela;
import com.mines.CondicaoPagto.Repository.PrazoParcelaRepository;
import com.mines.CondicaoPagto.Repository.PrazoParcelaRepositoryGCF;
import com.mines.CondicaoPagto.Repository.PrazoParcelaRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PrazoParcelaServiceImpl implements PrazoParcelaService{
    
    @Autowired
    private PrazoParcelaRepository prazoParcelaRepo;

    @Autowired 
    private PrazoParcelaRepositoryWinthor prazoParcelaRepoWinthor;

    @Autowired
    private PrazoParcelaRepositoryGCF prazoParcelaRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<PrazoParcela> obterTodosPrazoParcelas(Integer entidade) {

        List<PrazoParcela> prazoParcela = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                prazoParcela = prazoParcelaRepo.obterTodosPrazoParcelas(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                prazoParcela = prazoParcelaRepoWinthor.obterTodosPrazoParcelas(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                prazoParcela = prazoParcelaRepoGCF.obterTodosPrazoParcelas(entidade);
                break;
            }
        }

        return prazoParcela;
    }

    @Override
    public PrazoParcela obterPorIdPrazoParcela(Integer id) {

        PrazoParcela prazoParcela = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                prazoParcela = prazoParcelaRepo.obterPorIdPrazoParcela(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                prazoParcela = prazoParcelaRepoWinthor.obterPorIdPrazoParcela(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                prazoParcela = prazoParcelaRepoGCF.obterPorIdPrazoParcela(id);
                break;
            }
        }

        return prazoParcela;
    }
}


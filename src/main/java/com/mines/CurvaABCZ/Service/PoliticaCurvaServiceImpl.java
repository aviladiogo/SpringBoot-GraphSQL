package com.mines.CurvaABCZ.Service;

import java.util.List;
import com.mines.CurvaABCZ.Model.PoliticaCurva;
import com.mines.CurvaABCZ.Repository.PoliticaCurvaRepository;
import com.mines.CurvaABCZ.Repository.PoliticaCurvaRepositoryGCF;
import com.mines.CurvaABCZ.Repository.PoliticaCurvaRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PoliticaCurvaServiceImpl implements PoliticaCurvaService{
    
    @Autowired
    private PoliticaCurvaRepository politicaCurvaRepo;

    @Autowired 
    private PoliticaCurvaRepositoryWinthor politicaCurvaRepoWinthor;

    @Autowired
    private PoliticaCurvaRepositoryGCF politicaCurvaRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<PoliticaCurva> obterTodosPoliticaCurvas(Integer entidade) {

        List<PoliticaCurva> politicaCurva = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                politicaCurva = politicaCurvaRepo.obterTodosPoliticaCurvas(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                politicaCurva = politicaCurvaRepoWinthor.obterTodosPoliticaCurvas(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                politicaCurva = politicaCurvaRepoGCF.obterTodosPoliticaCurvas(entidade);
                break;
            }
        }

        return politicaCurva;
    }

    @Override
    public PoliticaCurva obterPorIdPoliticaCurva(Integer id) {

        PoliticaCurva politicaCurva = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                politicaCurva = politicaCurvaRepo.obterPorIdPoliticaCurva(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                politicaCurva = politicaCurvaRepoWinthor.obterPorIdPoliticaCurva(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                politicaCurva = politicaCurvaRepoGCF.obterPorIdPoliticaCurva(id);
                break;
            }
        }

        return politicaCurva;
    }
}


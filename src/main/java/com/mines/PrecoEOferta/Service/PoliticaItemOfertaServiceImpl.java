package com.mines.PrecoEOferta.Service;

import java.util.List;
import com.mines.PrecoEOferta.Model.PoliticaItemOferta;
import com.mines.PrecoEOferta.Repository.PoliticaItemOfertaRepository;
import com.mines.PrecoEOferta.Repository.PoliticaItemOfertaRepositoryGCF;
import com.mines.PrecoEOferta.Repository.PoliticaItemOfertaRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PoliticaItemOfertaServiceImpl implements PoliticaItemOfertaService{
    
    @Autowired
    private PoliticaItemOfertaRepository politicaItemOfertaRepo;

    @Autowired 
    private PoliticaItemOfertaRepositoryWinthor politicaItemOfertaRepoWinthor;

    @Autowired
    private PoliticaItemOfertaRepositoryGCF politicaItemOfertaRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<PoliticaItemOferta> obterTodosPoliticaItensOferta(Integer entidade) {

        List<PoliticaItemOferta> politicaItemOferta = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                politicaItemOferta = politicaItemOfertaRepo.obterTodosPoliticaItensOferta(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                politicaItemOferta = politicaItemOfertaRepoWinthor.obterTodosPoliticaItensOferta(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                politicaItemOferta = politicaItemOfertaRepoGCF.obterTodosPoliticaItensOferta(entidade);
                break;
            }
        }

        return politicaItemOferta;
    }

    @Override
    public PoliticaItemOferta obterPorIdPoliticaItemOferta(Integer id) {

        PoliticaItemOferta politicaItemOferta = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                politicaItemOferta = politicaItemOfertaRepo.obterPorIdPoliticaItemOferta(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                politicaItemOferta = politicaItemOfertaRepoWinthor.obterPorIdPoliticaItemOferta(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                politicaItemOferta = politicaItemOfertaRepoGCF.obterPorIdPoliticaItemOferta(id);
                break;
            }
        }

        return politicaItemOferta;
    }
}


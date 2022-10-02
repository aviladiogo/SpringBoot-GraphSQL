package com.mines.PrecoEOferta.Service;

import java.util.List;
import com.mines.PrecoEOferta.Model.Oferta;
import com.mines.PrecoEOferta.Repository.OfertaRepository;
import com.mines.PrecoEOferta.Repository.OfertaRepositoryGCF;
import com.mines.PrecoEOferta.Repository.OfertaRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OfertaServiceImpl implements OfertaService{
    
    @Autowired
    private OfertaRepository ofertaRepo;

    @Autowired 
    private OfertaRepositoryWinthor ofertaRepoWinthor;

    @Autowired
    private OfertaRepositoryGCF ofertaRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<Oferta> obterTodosOfertas(Integer entidade) {

        List<Oferta> oferta = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                oferta = ofertaRepo.obterTodosOfertas(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                oferta = ofertaRepoWinthor.obterTodosOfertas(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                oferta = ofertaRepoGCF.obterTodosOfertas(entidade);
                break;
            }
        }

        return oferta;
    }

    @Override
    public Oferta obterPorIdOferta(Integer id) {

        Oferta oferta = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                oferta = ofertaRepo.obterPorIdOferta(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                oferta = ofertaRepoWinthor.obterPorIdOferta(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                oferta = ofertaRepoGCF.obterPorIdOferta(id);
                break;
            }
        }

        return oferta;
    }
}


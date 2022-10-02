package com.mines.CondicaoPagto.Service;

import java.util.List;
import com.mines.CondicaoPagto.Model.CondicaoPagto;
import com.mines.CondicaoPagto.Repository.CondicaoPagtoRepository;
import com.mines.CondicaoPagto.Repository.CondicaoPagtoRepositoryGCF;
import com.mines.CondicaoPagto.Repository.CondicaoPagtoRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CondicaoPagtoServiceImpl implements CondicaoPagtoService{
    
    @Autowired
    private CondicaoPagtoRepository condicaoPagtoRepo;

    @Autowired 
    private CondicaoPagtoRepositoryWinthor condicaoPagtoRepoWinthor;

    @Autowired
    private CondicaoPagtoRepositoryGCF condicaoPagtoRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<CondicaoPagto> obterTodosCondicoesPagto(Integer entidade) {

        List<CondicaoPagto> condicaoPagto = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                condicaoPagto = condicaoPagtoRepo.obterTodosCondicoesPagto(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                condicaoPagto = condicaoPagtoRepoWinthor.obterTodosCondicoesPagto(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                condicaoPagto = condicaoPagtoRepoGCF.obterTodosCondicoesPagto(entidade);
                break;
            }
        }

        return condicaoPagto;
    }

    @Override
    public CondicaoPagto obterPorIdCondicaoPagto(Integer id) {

        CondicaoPagto condicaoPagto = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                condicaoPagto = condicaoPagtoRepo.obterPorIdCondicaoPagto(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                condicaoPagto = condicaoPagtoRepoWinthor.obterPorIdCondicaoPagto(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                condicaoPagto = condicaoPagtoRepoGCF.obterPorIdCondicaoPagto(id);
                break;
            }
        }

        return condicaoPagto;
    }
}


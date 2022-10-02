package com.mines.Empresa.Service;

import java.util.List;
import com.mines.Empresa.Model.GrupoFilial;
import com.mines.Empresa.Repository.GrupoFilialRepository;
import com.mines.Empresa.Repository.GrupoFilialRepositoryGCF;
import com.mines.Empresa.Repository.GrupoFilialRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GrupoFilialServiceImpl implements GrupoFilialService{
    
    @Autowired
    private GrupoFilialRepository grupoFilialRepo;

    @Autowired 
    private GrupoFilialRepositoryWinthor grupoFilialRepoWinthor;

    @Autowired
    private GrupoFilialRepositoryGCF grupoFilialRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<GrupoFilial> obterTodosGrupoFiliais(Integer entidade) {

        List<GrupoFilial> grupoFilial = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                grupoFilial = grupoFilialRepo.obterTodosGrupoFiliais(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                grupoFilial = grupoFilialRepoWinthor.obterTodosGrupoFiliais(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                grupoFilial = grupoFilialRepoGCF.obterTodosGrupoFiliais(entidade);
                break;
            }
        }

        return grupoFilial;
    }

    @Override
    public GrupoFilial obterPorIdGrupoFilial(Integer id) {

        GrupoFilial grupoFilial = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                grupoFilial = grupoFilialRepo.obterPorIdGrupoFilial(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                grupoFilial = grupoFilialRepoWinthor.obterPorIdGrupoFilial(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                grupoFilial = grupoFilialRepoGCF.obterPorIdGrupoFilial(id);
                break;
            }
        }

        return grupoFilial;
    }
}


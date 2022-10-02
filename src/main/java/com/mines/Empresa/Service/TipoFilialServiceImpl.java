package com.mines.Empresa.Service;

import java.util.List;
import com.mines.Empresa.Model.TipoFilial;
import com.mines.Empresa.Repository.TipoFilialRepository;
import com.mines.Empresa.Repository.TipoFilialRepositoryGCF;
import com.mines.Empresa.Repository.TipoFilialRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TipoFilialServiceImpl implements TipoFilialService{
    
    @Autowired
    private TipoFilialRepository tipoFilialRepo;

    @Autowired 
    private TipoFilialRepositoryWinthor tipoFilialRepoWinthor;

    @Autowired
    private TipoFilialRepositoryGCF tipoFilialRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<TipoFilial> obterTodosTipoFiliais(Integer entidade) {

        List<TipoFilial> tipoFilial = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                tipoFilial = tipoFilialRepo.obterTodosTipoFiliais(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                tipoFilial = tipoFilialRepoWinthor.obterTodosTipoFiliais(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                tipoFilial = tipoFilialRepoGCF.obterTodosTipoFiliais(entidade);
                break;
            }
        }

        return tipoFilial;
    }

    @Override
    public TipoFilial obterPorIdTipoFilial(Integer id) {

        TipoFilial tipoFilial = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                tipoFilial = tipoFilialRepo.obterPorIdTipoFilial(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                tipoFilial = tipoFilialRepoWinthor.obterPorIdTipoFilial(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                tipoFilial = tipoFilialRepoGCF.obterPorIdTipoFilial(id);
                break;
            }
        }

        return tipoFilial;
    }
}


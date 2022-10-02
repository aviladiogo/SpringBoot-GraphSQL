package com.mines.Fornecedor.Service;

import java.util.List;
import com.mines.Fornecedor.Model.TipoFrete;
import com.mines.Fornecedor.Repository.TipoFreteRepository;
import com.mines.Fornecedor.Repository.TipoFreteRepositoryGCF;
import com.mines.Fornecedor.Repository.TipoFreteRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TipoFreteServiceImpl implements TipoFreteService{
    
    @Autowired
    private TipoFreteRepository tipoFreteRepo;

    @Autowired 
    private TipoFreteRepositoryWinthor tipoFreteRepoWinthor;

    @Autowired
    private TipoFreteRepositoryGCF tipoFreteRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<TipoFrete> obterTodosTiposFrete(Integer entidade) {

        List<TipoFrete> tiposFrete = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                tiposFrete = tipoFreteRepo.obterTodosTiposFrete(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                tiposFrete = tipoFreteRepoWinthor.obterTodosTiposFrete(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                tiposFrete = tipoFreteRepoGCF.obterTodosTiposFrete(entidade);
                break;
            }
        }

        return tiposFrete;
    }

    @Override
    public TipoFrete obterPorIdTipoFrete(Integer id) {

        TipoFrete tipoFrete = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                tipoFrete = tipoFreteRepo.obterPorIdTipoFrete(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                tipoFrete = tipoFreteRepoWinthor.obterPorIdTipoFrete(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                tipoFrete = tipoFreteRepoGCF.obterPorIdTipoFrete(id);
                break;
            }
        }

        return tipoFrete;
    }
}

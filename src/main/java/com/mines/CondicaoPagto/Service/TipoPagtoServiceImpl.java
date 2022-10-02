package com.mines.CondicaoPagto.Service;

import java.util.List;
import com.mines.CondicaoPagto.Model.TipoPagto;
import com.mines.CondicaoPagto.Repository.TipoPagtoRepository;
import com.mines.CondicaoPagto.Repository.TipoPagtoRepositoryGCF;
import com.mines.CondicaoPagto.Repository.TipoPagtoRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TipoPagtoServiceImpl implements TipoPagtoService{
    
    @Autowired
    private TipoPagtoRepository tipoPagtoRepo;

    @Autowired 
    private TipoPagtoRepositoryWinthor tipoPagtoRepoWinthor;

    @Autowired
    private TipoPagtoRepositoryGCF tipoPagtoRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<TipoPagto> obterTodosTiposPagto(Integer entidade) {

        List<TipoPagto> tipoPagto = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                tipoPagto = tipoPagtoRepo.obterTodosTiposPagto(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                tipoPagto = tipoPagtoRepoWinthor.obterTodosTiposPagto(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                tipoPagto = tipoPagtoRepoGCF.obterTodosTiposPagto(entidade);
                break;
            }
        }

        return tipoPagto;
    }

    @Override
    public TipoPagto obterPorIdTipoPagto(Integer id) {

        TipoPagto tipoPagto = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                tipoPagto = tipoPagtoRepo.obterPorIdTipoPagto(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                tipoPagto = tipoPagtoRepoWinthor.obterPorIdTipoPagto(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                tipoPagto = tipoPagtoRepoGCF.obterPorIdTipoPagto(id);
                break;
            }
        }

        return tipoPagto;
    }
}


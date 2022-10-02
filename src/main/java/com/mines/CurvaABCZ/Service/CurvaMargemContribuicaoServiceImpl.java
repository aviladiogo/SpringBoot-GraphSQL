package com.mines.CurvaABCZ.Service;

import java.util.List;
import com.mines.CurvaABCZ.Model.CurvaMargemContribuicao;
import com.mines.CurvaABCZ.Repository.CurvaMargemContribuicaoRepository;
import com.mines.CurvaABCZ.Repository.CurvaMargemContribuicaoRepositoryGCF;
import com.mines.CurvaABCZ.Repository.CurvaMargemContribuicaoRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CurvaMargemContribuicaoServiceImpl implements CurvaMargemContribuicaoService{
    
    @Autowired
    private CurvaMargemContribuicaoRepository curvaMargemContribuicaoRepo;

    @Autowired 
    private CurvaMargemContribuicaoRepositoryWinthor curvaMargemContribuicaoRepoWinthor;

    @Autowired
    private CurvaMargemContribuicaoRepositoryGCF curvaMargemContribuicaoRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<CurvaMargemContribuicao> obterTodosCurvasMargemContribuicao(Integer entidade) {

        List<CurvaMargemContribuicao> curvaMargemContribuicao = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                curvaMargemContribuicao = curvaMargemContribuicaoRepo.obterTodosCurvasMargemContribuicao(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                curvaMargemContribuicao = curvaMargemContribuicaoRepoWinthor.obterTodosCurvasMargemContribuicao(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                curvaMargemContribuicao = curvaMargemContribuicaoRepoGCF.obterTodosCurvasMargemContribuicao(entidade);
                break;
            }
        }

        return curvaMargemContribuicao;
    }

    @Override
    public CurvaMargemContribuicao obterPorIdCurvaMargemContribuicao(Integer id) {

        CurvaMargemContribuicao curvaMargemContribuicao = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                curvaMargemContribuicao = curvaMargemContribuicaoRepo.obterPorIdCurvaMargemContribuicao(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                curvaMargemContribuicao = curvaMargemContribuicaoRepoWinthor.obterPorIdCurvaMargemContribuicao(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                curvaMargemContribuicao = curvaMargemContribuicaoRepoGCF.obterPorIdCurvaMargemContribuicao(id);
                break;
            }
        }

        return curvaMargemContribuicao;
    }
}


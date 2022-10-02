package com.mines.CurvaABCZ.Service;

import java.util.List;
import com.mines.CurvaABCZ.Model.CurvaCapitalEmpregado;
import com.mines.CurvaABCZ.Repository.CurvaCapitalEmpregadoRepository;
import com.mines.CurvaABCZ.Repository.CurvaCapitalEmpregadoRepositoryGCF;
import com.mines.CurvaABCZ.Repository.CurvaCapitalEmpregadoRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CurvaCapitalEmpregadoServiceImpl implements CurvaCapitalEmpregadoService{
    
    @Autowired
    private CurvaCapitalEmpregadoRepository curvaCapitalEmpregadoRepo;

    @Autowired 
    private CurvaCapitalEmpregadoRepositoryWinthor curvaCapitalEmpregadoRepoWinthor;

    @Autowired
    private CurvaCapitalEmpregadoRepositoryGCF curvaCapitalEmpregadoRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<CurvaCapitalEmpregado> obterTodosCurvasCapitalEmpregado(Integer entidade) {

        List<CurvaCapitalEmpregado> curvaCapitalEmpregado = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                curvaCapitalEmpregado = curvaCapitalEmpregadoRepo.obterTodosCurvasCapitalEmpregado(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                curvaCapitalEmpregado = curvaCapitalEmpregadoRepoWinthor.obterTodosCurvasCapitalEmpregado(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                curvaCapitalEmpregado = curvaCapitalEmpregadoRepoGCF.obterTodosCurvasCapitalEmpregado(entidade);
                break;
            }
        }

        return curvaCapitalEmpregado;
    }

    @Override
    public CurvaCapitalEmpregado obterPorIdCurvaCapitalEmpregado(Integer id) {

        CurvaCapitalEmpregado curvaCapitalEmpregado = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                curvaCapitalEmpregado = curvaCapitalEmpregadoRepo.obterPorIdCurvaCapitalEmpregado(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                curvaCapitalEmpregado = curvaCapitalEmpregadoRepoWinthor.obterPorIdCurvaCapitalEmpregado(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                curvaCapitalEmpregado = curvaCapitalEmpregadoRepoGCF.obterPorIdCurvaCapitalEmpregado(id);
                break;
            }
        }

        return curvaCapitalEmpregado;
    }
}


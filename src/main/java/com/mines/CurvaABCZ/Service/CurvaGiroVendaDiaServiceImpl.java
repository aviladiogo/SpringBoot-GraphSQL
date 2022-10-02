package com.mines.CurvaABCZ.Service;

import java.util.List;
import com.mines.CurvaABCZ.Model.CurvaGiroVendaDia;
import com.mines.CurvaABCZ.Repository.CurvaGiroVendaDiaRepository;
import com.mines.CurvaABCZ.Repository.CurvaGiroVendaDiaRepositoryGCF;
import com.mines.CurvaABCZ.Repository.CurvaGiroVendaDiaRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CurvaGiroVendaDiaServiceImpl implements CurvaGiroVendaDiaService{
    
    @Autowired
    private CurvaGiroVendaDiaRepository curvaGiroVendaDiaRepo;

    @Autowired 
    private CurvaGiroVendaDiaRepositoryWinthor curvaGiroVendaDiaRepoWinthor;

    @Autowired
    private CurvaGiroVendaDiaRepositoryGCF curvaGiroVendaDiaRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<CurvaGiroVendaDia> obterTodosCurvasGiroVendaDia(Integer entidade) {

        List<CurvaGiroVendaDia> curvaGiroVendaDia = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                curvaGiroVendaDia = curvaGiroVendaDiaRepo.obterTodosCurvasGiroVendaDia(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                curvaGiroVendaDia = curvaGiroVendaDiaRepoWinthor.obterTodosCurvasGiroVendaDia(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                curvaGiroVendaDia = curvaGiroVendaDiaRepoGCF.obterTodosCurvasGiroVendaDia(entidade);
                break;
            }
        }

        return curvaGiroVendaDia;
    }

    @Override
    public CurvaGiroVendaDia obterPorIdCurvaGiroVendaDia(Integer id) {

        CurvaGiroVendaDia curvaGiroVendaDia = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                curvaGiroVendaDia = curvaGiroVendaDiaRepo.obterPorIdCurvaGiroVendaDia(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                curvaGiroVendaDia = curvaGiroVendaDiaRepoWinthor.obterPorIdCurvaGiroVendaDia(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                curvaGiroVendaDia = curvaGiroVendaDiaRepoGCF.obterPorIdCurvaGiroVendaDia(id);
                break;
            }
        }

        return curvaGiroVendaDia;
    }
}


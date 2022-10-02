package com.mines.CurvaABCZ.Service;

import java.util.List;
import com.mines.CurvaABCZ.Model.CurvaAbcz;
import com.mines.CurvaABCZ.Repository.CurvaAbczRepository;
import com.mines.CurvaABCZ.Repository.CurvaAbczRepositoryGCF;
import com.mines.CurvaABCZ.Repository.CurvaAbczRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CurvaAbczServiceImpl implements CurvaAbczService{
    
    @Autowired
    private CurvaAbczRepository curvaAbczRepo;

    @Autowired 
    private CurvaAbczRepositoryWinthor curvaAbczRepoWinthor;

    @Autowired
    private CurvaAbczRepositoryGCF curvaAbczRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<CurvaAbcz> obterTodosCurvasABCZ(Integer entidade) {

        List<CurvaAbcz> curvaAbcz = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                curvaAbcz = curvaAbczRepo.obterTodosCurvasABCZ(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                curvaAbcz = curvaAbczRepoWinthor.obterTodosCurvasABCZ(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                curvaAbcz = curvaAbczRepoGCF.obterTodosCurvasABCZ(entidade);
                break;
            }
        }

        return curvaAbcz;
    }

    @Override
    public CurvaAbcz obterPorIdCurvaABCZ(Integer id) {

        CurvaAbcz curvaAbcz = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                curvaAbcz = curvaAbczRepo.obterPorIdCurvaABCZ(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                curvaAbcz = curvaAbczRepoWinthor.obterPorIdCurvaABCZ(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                curvaAbcz = curvaAbczRepoGCF.obterPorIdCurvaABCZ(id);
                break;
            }
        }

        return curvaAbcz;
    }
}


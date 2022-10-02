package com.mines.CurvaABCZ.Service;

import java.util.List;
import com.mines.CurvaABCZ.Model.TipoCurvaAbcz;
import com.mines.CurvaABCZ.Repository.TipoCurvaAbczRepository;
import com.mines.CurvaABCZ.Repository.TipoCurvaAbczRepositoryGCF;
import com.mines.CurvaABCZ.Repository.TipoCurvaAbczRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TipoCurvaAbczServiceImpl implements TipoCurvaAbczService{
    
    @Autowired
    private TipoCurvaAbczRepository tipoCurvaAbczRepo;

    @Autowired 
    private TipoCurvaAbczRepositoryWinthor tipoCurvaAbczRepoWinthor;

    @Autowired
    private TipoCurvaAbczRepositoryGCF tipoCurvaAbczRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<TipoCurvaAbcz> obterTodosTiposCurvaAbcz(Integer entidade) {

        List<TipoCurvaAbcz> tipoCurvaAbcz = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                tipoCurvaAbcz = tipoCurvaAbczRepo.obterTodosTiposCurvaAbcz(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                tipoCurvaAbcz = tipoCurvaAbczRepoWinthor.obterTodosTiposCurvaAbcz(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                tipoCurvaAbcz = tipoCurvaAbczRepoGCF.obterTodosTiposCurvaAbcz(entidade);
                break;
            }
        }

        return tipoCurvaAbcz;
    }

    @Override
    public TipoCurvaAbcz obterPorIdTipoCurvaAbcz(Integer id) {

        TipoCurvaAbcz tipoCurvaAbcz = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                tipoCurvaAbcz = tipoCurvaAbczRepo.obterPorIdTipoCurvaAbcz(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                tipoCurvaAbcz = tipoCurvaAbczRepoWinthor.obterPorIdTipoCurvaAbcz(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                tipoCurvaAbcz = tipoCurvaAbczRepoGCF.obterPorIdTipoCurvaAbcz(id);
                break;
            }
        }

        return tipoCurvaAbcz;
    }
}


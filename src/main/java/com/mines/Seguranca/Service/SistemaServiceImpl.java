package com.mines.Seguranca.Service;

import java.util.List;
import com.mines.Seguranca.Model.Sistema;
import com.mines.Seguranca.Repository.SistemaRepository;
import com.mines.Seguranca.Repository.SistemaRepositoryGCF;
import com.mines.Seguranca.Repository.SistemaRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SistemaServiceImpl implements SistemaService{
    
    @Autowired
    private SistemaRepository sistemaRepo;

    @Autowired 
    private SistemaRepositoryWinthor sistemaRepoWinthor;

    @Autowired
    private SistemaRepositoryGCF sistemaRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<Sistema> obterTodosSistemas(Integer entidade) {

        List<Sistema> sistema = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                sistema = sistemaRepo.obterTodosSistemas(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                sistema = sistemaRepoWinthor.obterTodosSistemas(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                sistema = sistemaRepoGCF.obterTodosSistemas(entidade);
                break;
            }
        }

        return sistema;
    }

    @Override
    public Sistema obterPorIdSistema(Integer id) {

        Sistema sistema = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                sistema = sistemaRepo.obterPorIdSistema(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                sistema = sistemaRepoWinthor.obterPorIdSistema(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                sistema = sistemaRepoGCF.obterPorIdSistema(id);
                break;
            }
        }

        return sistema;
    }
}


package com.mines.Seguranca.Service;

import java.util.List;
import com.mines.Seguranca.Model.Perfil;
import com.mines.Seguranca.Repository.PerfilRepository;
import com.mines.Seguranca.Repository.PerfilRepositoryGCF;
import com.mines.Seguranca.Repository.PerfilRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PerfilServiceImpl implements PerfilService{
    
    @Autowired
    private PerfilRepository perfilRepo;

    @Autowired 
    private PerfilRepositoryWinthor perfilRepoWinthor;

    @Autowired
    private PerfilRepositoryGCF perfilRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<Perfil> obterTodosPerfis(Integer entidade) {

        List<Perfil> perfil = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                perfil = perfilRepo.obterTodosPerfis(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                perfil = perfilRepoWinthor.obterTodosPerfis(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                perfil = perfilRepoGCF.obterTodosPerfis(entidade);
                break;
            }
        }

        return perfil;
    }

    @Override
    public Perfil obterPorIdPerfil(Integer id) {

        Perfil perfil = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                perfil = perfilRepo.obterPorIdPerfil(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                perfil = perfilRepoWinthor.obterPorIdPerfil(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                perfil = perfilRepoGCF.obterPorIdPerfil(id);
                break;
            }
        }

        return perfil;
    }
}


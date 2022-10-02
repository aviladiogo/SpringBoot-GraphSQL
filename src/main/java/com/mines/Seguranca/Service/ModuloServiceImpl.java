package com.mines.Seguranca.Service;

import java.util.List;
import com.mines.Seguranca.Model.Modulo;
import com.mines.Seguranca.Repository.ModuloRepository;
import com.mines.Seguranca.Repository.ModuloRepositoryGCF;
import com.mines.Seguranca.Repository.ModuloRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ModuloServiceImpl implements ModuloService{
    
    @Autowired
    private ModuloRepository moduloRepo;

    @Autowired 
    private ModuloRepositoryWinthor moduloRepoWinthor;

    @Autowired
    private ModuloRepositoryGCF moduloRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<Modulo> obterTodosModulos(Integer entidade) {

        List<Modulo> modulo = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                modulo = moduloRepo.obterTodosModulos(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                modulo = moduloRepoWinthor.obterTodosModulos(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                modulo = moduloRepoGCF.obterTodosModulos(entidade);
                break;
            }
        }

        return modulo;
    }

    @Override
    public Modulo obterPorIdModulo(Integer id) {

        Modulo modulo = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                modulo = moduloRepo.obterPorIdModulo(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                modulo = moduloRepoWinthor.obterPorIdModulo(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                modulo = moduloRepoGCF.obterPorIdModulo(id);
                break;
            }
        }

        return modulo;
    }
}


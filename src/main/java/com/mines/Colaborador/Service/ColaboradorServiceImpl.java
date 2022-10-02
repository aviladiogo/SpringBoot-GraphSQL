package com.mines.Colaborador.Service;

import java.util.List;
import com.mines.Colaborador.Model.Colaborador;
import com.mines.Colaborador.Repository.ColaboradorRepository;
import com.mines.Colaborador.Repository.ColaboradorRepositoryGCF;
import com.mines.Colaborador.Repository.ColaboradorRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ColaboradorServiceImpl implements ColaboradorService{
    
    @Autowired
    private ColaboradorRepository colaboradorRepo;

    @Autowired 
    private ColaboradorRepositoryWinthor colaboradorRepoWinthor;

    @Autowired
    private ColaboradorRepositoryGCF colaboradorRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<Colaborador> obterTodosColaboradores(Integer entidade) {

        List<Colaborador> colaborador = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                colaborador = colaboradorRepo.obterTodosColaboradores(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                colaborador = colaboradorRepoWinthor.obterTodosColaboradores(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                colaborador = colaboradorRepoGCF.obterTodosColaboradores(entidade);
                break;
            }
        }

        return colaborador;
    }

    @Override
    public Colaborador obterPorIdColaborador(Integer id) {

        Colaborador colaborador = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                colaborador = colaboradorRepo.obterPorIdColaborador(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                colaborador = colaboradorRepoWinthor.obterPorIdColaborador(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                colaborador = colaboradorRepoGCF.obterPorIdColaborador(id);
                break;
            }
        }

        return colaborador;
    }
}


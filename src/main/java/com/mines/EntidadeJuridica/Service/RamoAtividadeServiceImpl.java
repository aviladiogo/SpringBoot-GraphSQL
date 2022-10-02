package com.mines.EntidadeJuridica.Service;

import java.util.List;
import com.mines.EntidadeJuridica.Model.RamoAtividade;
import com.mines.EntidadeJuridica.Repository.RamoAtividadeRepository;
import com.mines.EntidadeJuridica.Repository.RamoAtividadeRepositoryGCF;
import com.mines.EntidadeJuridica.Repository.RamoAtividadeRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RamoAtividadeServiceImpl implements RamoAtividadeService{
    
    @Autowired
    private RamoAtividadeRepository ramoAtividadeRepo;

    @Autowired 
    private RamoAtividadeRepositoryWinthor ramoAtividadeRepoWinthor;

    @Autowired
    private RamoAtividadeRepositoryGCF ramoAtividadeRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<RamoAtividade> obterTodosRamoAtividades(Integer entidade) {

        List<RamoAtividade> ramoAtividade = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                ramoAtividade = ramoAtividadeRepo.obterTodosRamoAtividades(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                ramoAtividade = ramoAtividadeRepoWinthor.obterTodosRamoAtividades(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                ramoAtividade = ramoAtividadeRepoGCF.obterTodosRamoAtividades(entidade);
                break;
            }
        }

        return ramoAtividade;
    }

    @Override
    public RamoAtividade obterPorIdRamoAtividade(Integer id) {

        RamoAtividade ramoAtividade = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                ramoAtividade = ramoAtividadeRepo.obterPorIdRamoAtividade(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                ramoAtividade = ramoAtividadeRepoWinthor.obterPorIdRamoAtividade(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                ramoAtividade = ramoAtividadeRepoGCF.obterPorIdRamoAtividade(id);
                break;
            }
        }

        return ramoAtividade;
    }
}


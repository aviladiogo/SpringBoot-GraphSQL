package com.mines.EntidadeJuridica.Service;

import java.util.List;
import com.mines.EntidadeJuridica.Model.AtividadeComercial;
import com.mines.EntidadeJuridica.Repository.AtividadeComercialRepository;
import com.mines.EntidadeJuridica.Repository.AtividadeComercialRepositoryGCF;
import com.mines.EntidadeJuridica.Repository.AtividadeComercialRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AtividadeComercialServiceImpl implements AtividadeComercialService {

    @Autowired
    private AtividadeComercialRepository atividadeComercialRepo;

    @Autowired
    private AtividadeComercialRepositoryWinthor atividadeComercialRepoWinthor;

    @Autowired
    private AtividadeComercialRepositoryGCF atividadeComercialRepoGCF;

    @Value("${sistema_erp}")
    public Integer sistema_erp;

    @Override
    public List<AtividadeComercial> obterTodosAtividadesComercial(Integer entidade) {

        List<AtividadeComercial> atividadeComercial = null;

        switch (sistema_erp) {

            case ConstantesERP.k_minesSistemas: {
                atividadeComercial = atividadeComercialRepo.obterTodosAtividadesComercial(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas: {
                atividadeComercial = atividadeComercialRepoWinthor.obterTodosAtividadesComercial(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas: {
                atividadeComercial = atividadeComercialRepoGCF.obterTodosAtividadesComercial(entidade);
                break;
            }
        }

        return atividadeComercial;
    }

    @Override
    public List<AtividadeComercial> obterTodosAtividadesComercialPorRamoAtividade(Integer entidade,
            Integer ramoAtividade) {

        List<AtividadeComercial> atividadeComercial = null;

        switch (sistema_erp) {

            case ConstantesERP.k_minesSistemas: {
                atividadeComercial = atividadeComercialRepo.obterTodosAtividadesComercialPorRamoAtividade(entidade,
                        ramoAtividade);
                break;
            }

            case ConstantesERP.k_winthorSistemas: {
                atividadeComercial = atividadeComercialRepoWinthor
                        .obterTodosAtividadesComercialPorRamoAtividade(entidade, ramoAtividade);
                break;
            }

            case ConstantesERP.k_gcfSistemas: {
                atividadeComercial = atividadeComercialRepoGCF.obterTodosAtividadesComercialPorRamoAtividade(entidade,
                        ramoAtividade);
                break;
            }
        }

        return atividadeComercial;
    }

    @Override
    public AtividadeComercial obterPorIdAtividadeComercial(Integer id) {

        AtividadeComercial atividadeComercial = null;

        switch (sistema_erp) {

            case ConstantesERP.k_minesSistemas: {
                atividadeComercial = atividadeComercialRepo.obterPorIdAtividadeComercial(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas: {
                atividadeComercial = atividadeComercialRepoWinthor.obterPorIdAtividadeComercial(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas: {
                atividadeComercial = atividadeComercialRepoGCF.obterPorIdAtividadeComercial(id);
                break;
            }
        }

        return atividadeComercial;
    }

}

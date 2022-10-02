package com.mines.EntidadeJuridica.Service;

import java.util.List;
import com.mines.EntidadeJuridica.Model.EntidadeJuridica;
import com.mines.EntidadeJuridica.Repository.EntidadeJuridicaRepository;
import com.mines.EntidadeJuridica.Repository.EntidadeJuridicaRepositoryGCF;
import com.mines.EntidadeJuridica.Repository.EntidadeJuridicaRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EntidadeJuridicaServiceImpl implements EntidadeJuridicaService {

    @Autowired
    private EntidadeJuridicaRepository entidadeJuridicaRepo;

    @Autowired
    private EntidadeJuridicaRepositoryWinthor entidadeJuridicaRepoWinthor;

    @Autowired
    private EntidadeJuridicaRepositoryGCF entidadeJuridicaRepoGCF;

    @Value("${sistema_erp}")
    public Integer sistema_erp;

    @Override
    public List<EntidadeJuridica> obterTodosEntidadesJuridicas(Integer entidade) {

        List<EntidadeJuridica> entidadeJuridica = null;

        switch (sistema_erp) {

            case ConstantesERP.k_minesSistemas: {
                entidadeJuridica = entidadeJuridicaRepo.obterTodosEntidadesJuridicas(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas: {
                entidadeJuridica = entidadeJuridicaRepoWinthor.obterTodosEntidadesJuridicas(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas: {
                entidadeJuridica = entidadeJuridicaRepoGCF.obterTodosEntidadesJuridicas(entidade);
                break;
            }
        }

        return entidadeJuridica;
    }

    @Override
    public EntidadeJuridica obterPorIdEntidadeJuridica(Integer id) {

        EntidadeJuridica entidadeJuridica = null;

        switch (sistema_erp) {

            case ConstantesERP.k_minesSistemas: {
                entidadeJuridica = entidadeJuridicaRepo.obterPorIdEntidadeJuridica(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas: {
                entidadeJuridica = entidadeJuridicaRepoWinthor.obterPorIdEntidadeJuridica(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas: {
                entidadeJuridica = entidadeJuridicaRepoGCF.obterPorIdEntidadeJuridica(id);
                break;
            }
        }

        return entidadeJuridica;
    }

}

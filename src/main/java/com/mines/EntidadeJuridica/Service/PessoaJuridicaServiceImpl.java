package com.mines.EntidadeJuridica.Service;

import java.util.List;
import com.mines.EntidadeJuridica.Model.PessoaJuridica;
import com.mines.EntidadeJuridica.Repository.PessoaJuridicaRepository;
import com.mines.EntidadeJuridica.Repository.PessoaJuridicaRepositoryGCF;
import com.mines.EntidadeJuridica.Repository.PessoaJuridicaRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PessoaJuridicaServiceImpl implements PessoaJuridicaService{
    
    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepo;

    @Autowired 
    private PessoaJuridicaRepositoryWinthor pessoaJuridicaRepoWinthor;

    @Autowired
    private PessoaJuridicaRepositoryGCF pessoaJuridicaRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<PessoaJuridica> obterTodosPessoasJuridica(Integer entidade) {

        List<PessoaJuridica> pessoaJuridica = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                pessoaJuridica = pessoaJuridicaRepo.obterTodosPessoasJuridica(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                pessoaJuridica = pessoaJuridicaRepoWinthor.obterTodosPessoasJuridica(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                pessoaJuridica = pessoaJuridicaRepoGCF.obterTodosPessoasJuridica(entidade);
                break;
            }
        }

        return pessoaJuridica;
    }

    @Override
    public PessoaJuridica obterPorIdPessoaJuridica(Integer id) {

        PessoaJuridica pessoaJuridica = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                pessoaJuridica = pessoaJuridicaRepo.obterPorIdPessoaJuridica(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                pessoaJuridica = pessoaJuridicaRepoWinthor.obterPorIdPessoaJuridica(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                pessoaJuridica = pessoaJuridicaRepoGCF.obterPorIdPessoaJuridica(id);
                break;
            }
        }

        return pessoaJuridica;
    }
}


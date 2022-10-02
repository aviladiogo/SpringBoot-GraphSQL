package com.mines.EntidadeJuridica.Service;

import java.util.List;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepositoryGCF;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PessoaFisicaServiceImpl implements PessoaFisicaService{
    
    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired 
    private PessoaFisicaRepositoryWinthor pessoaFisicaRepoWinthor;

    @Autowired
    private PessoaFisicaRepositoryGCF pessoaFisicaRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<PessoaFisica> obterTodosPessoasFisica(Integer entidade) {

        List<PessoaFisica> pessoaFisica = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                pessoaFisica = pessoaFisicaRepo.obterTodosPessoasFisica(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                pessoaFisica = pessoaFisicaRepoWinthor.obterTodosPessoasFisica(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                pessoaFisica = pessoaFisicaRepoGCF.obterTodosPessoasFisica(entidade);
                break;
            }
        }

        return pessoaFisica;
    }

    @Override
    public PessoaFisica obterPorIdPessoaFisica(Integer id) {

        PessoaFisica pessoaFisica = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                pessoaFisica = pessoaFisicaRepo.obterPorIdPessoaFisica(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                pessoaFisica = pessoaFisicaRepoWinthor.obterPorIdPessoaFisica(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                pessoaFisica = pessoaFisicaRepoGCF.obterPorIdPessoaFisica(id);
                break;
            }
        }

        return pessoaFisica;
    }
}


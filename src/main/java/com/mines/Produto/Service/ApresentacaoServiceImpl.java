package com.mines.Produto.Service;

import java.util.List;
import com.mines.Produto.Model.Apresentacao;
import com.mines.Produto.Repository.ApresentacaoRepository;
import com.mines.Produto.Repository.ApresentacaoRepositoryGCF;
import com.mines.Produto.Repository.ApresentacaoRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ApresentacaoServiceImpl implements ApresentacaoService{
    
    @Autowired
    private ApresentacaoRepository apresentacaoRepo;

    @Autowired 
    private ApresentacaoRepositoryWinthor apresentacaoRepoWinthor;

    @Autowired
    private ApresentacaoRepositoryGCF apresentacaoRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<Apresentacao> obterTodosApresentacoes(Integer entidade) {

        List<Apresentacao> apresentacao = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                apresentacao = apresentacaoRepo.obterTodosApresentacoes(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                apresentacao = apresentacaoRepoWinthor.obterTodosApresentacoes(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                apresentacao = apresentacaoRepoGCF.obterTodosApresentacoes(entidade);
                break;
            }
        }

        return apresentacao;
    }

    @Override
    public Apresentacao obterPorIdApresentacao(Integer id) {

        Apresentacao apresentacao = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                apresentacao = apresentacaoRepo.obterPorIdApresentacao(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                apresentacao = apresentacaoRepoWinthor.obterPorIdApresentacao(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                apresentacao = apresentacaoRepoGCF.obterPorIdApresentacao(id);
                break;
            }
        }

        return apresentacao;
    }
}


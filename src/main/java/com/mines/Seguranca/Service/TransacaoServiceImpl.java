package com.mines.Seguranca.Service;

import java.util.List;
import com.mines.Seguranca.Model.Transacao;
import com.mines.Seguranca.Repository.TransacaoRepository;
import com.mines.Seguranca.Repository.TransacaoRepositoryGCF;
import com.mines.Seguranca.Repository.TransacaoRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TransacaoServiceImpl implements TransacaoService{
    
    @Autowired
    private TransacaoRepository transacaoRepo;

    @Autowired 
    private TransacaoRepositoryWinthor transacaoRepoWinthor;

    @Autowired
    private TransacaoRepositoryGCF transacaoRepoGCF;

    @Value("${sistema_erp}") public Integer Transacao_erp;

    @Override
    public List<Transacao> obterTodosTransacoes(Integer entidade) {

        List<Transacao> transacao = null;

        switch(Transacao_erp){

            case ConstantesERP.k_minesSistemas:{
                transacao = transacaoRepo.obterTodosTransacoes(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                transacao = transacaoRepoWinthor.obterTodosTransacoes(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                transacao = transacaoRepoGCF.obterTodosTransacoes(entidade);
                break;
            }
        }

        return transacao;
    }

    @Override
    public Transacao obterPorIdTransacao(Integer id) {

        Transacao transacao = null;

        switch(Transacao_erp){

            case ConstantesERP.k_minesSistemas:{
                transacao = transacaoRepo.obterPorIdTransacao(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                transacao = transacaoRepoWinthor.obterPorIdTransacao(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                transacao = transacaoRepoGCF.obterPorIdTransacao(id);
                break;
            }
        }

        return transacao;
    }
}


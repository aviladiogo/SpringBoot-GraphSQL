package com.mines.Seguranca.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.Seguranca.Model.Modulo;
import com.mines.Seguranca.Model.Transacao;
import com.mines.Seguranca.Model.TransacaoInput;
import com.mines.Seguranca.Repository.TransacaoRepository;
import com.mines.Seguranca.Service.TransacaoService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransacaoGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private TransacaoRepository transacaoRepo;

    @Autowired
    private TransacaoService transacaoServ;

    public Transacao transacao(Integer id){

        Transacao transacao = null;
        try {
            transacao = transacaoServ.obterPorIdTransacao(id);
        } catch (Exception e) {
            return transacao;
        }
        return transacao;
    }

    public List<Transacao> transacoes(Integer entidade){
        
        List<Transacao> lista = transacaoServ.obterTodosTransacoes(entidade);
        return lista;
    }

    public Boolean deletarTransacao(Integer id){

        Boolean ret = false;

        try{
            Transacao transacao = transacaoRepo.obterPorIdTransacao(id);
            ret = transacaoRepo.deletarTransacao(transacao);
        }catch(Exception e){
            return ret;
        }
        return ret;    
    }   

    public Transacao salvarTransacao(TransacaoInput transacaoInput) throws SQLException{

        Transacao transacao = new Transacao();

        transacao.setDescricao(transacaoInput.getDescricao());
        transacao.setAtivo(transacaoInput.getAtivo());

        Modulo modulo = new Modulo();
        modulo.setId(transacaoInput.getModulo());
        transacao.setModulo(modulo);
        
        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(transacaoInput.getEntidadeGestora());
        transacao.setEntidadeGestora(entidadeGestora);
        
        try{
            if(transacaoInput.getId() == 0){
                transacao = transacaoRepo.salvarTransacao(transacao);
            }else{
                transacao.setId(transacaoInput.getId());
                transacao = transacaoRepo.atualizarTransacao(transacao);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return transacao;

    }

}
package com.mines.Produto.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.Produto.Model.Apresentacao;
import com.mines.Produto.Model.ApresentacaoInput;
import com.mines.Produto.Repository.ApresentacaoRepository;
import com.mines.Produto.Service.ApresentacaoService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApresentacaoGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private ApresentacaoRepository apresentacaoRepo;

    @Autowired
    private ApresentacaoService apresentacaoServ;

    public Apresentacao apresentacao(Integer id){

        Apresentacao apresentacao = null;
        try {
            apresentacao = apresentacaoServ.obterPorIdApresentacao(id);
        } catch (Exception e) {
            return apresentacao;
        }
        return apresentacao;
    }

    public List<Apresentacao> apresentacoes(Integer entidade){

        List<Apresentacao> lista = apresentacaoServ.obterTodosApresentacoes(entidade);
        return lista;
    }

    public Boolean deletarApresentacao(Integer id){

        Boolean ret = false;

        try{
            Apresentacao apresentacao = apresentacaoRepo.obterPorIdApresentacao(id);
            ret = apresentacaoRepo.deletarApresentacao(apresentacao);
        }catch(Exception e){
            return ret;
        }
        return ret;    
    }   

    public Apresentacao salvarApresentacao(ApresentacaoInput apresentacaoInput) throws SQLException{

        Apresentacao apresentacao = new Apresentacao();

        apresentacao.setDescricao(apresentacaoInput.getDescricao());

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(apresentacaoInput.getEntidadeGestora());
        apresentacao.setEntidadeGestora(entidadeGestora);

        try{
            if(apresentacaoInput.getId() == 0){
                apresentacao = apresentacaoRepo.salvarApresentacao(apresentacao);
            }else{
                apresentacao.setId(apresentacaoInput.getId());
                apresentacao = apresentacaoRepo.atualizarApresentacao(apresentacao);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return apresentacao;

    }

}

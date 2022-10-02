package com.mines.Produto.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.Produto.Model.Embalagem;
import com.mines.Produto.Model.EmbalagemInput;
import com.mines.Produto.Repository.EmbalagemRepository;
import com.mines.Produto.Service.EmbalagemService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmbalagemGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private EmbalagemRepository embalagemRepo;

    @Autowired
    private EmbalagemService embalagemServ;

    public Embalagem embalagem(Integer id){

        Embalagem embalagem = null;
        try {
            embalagem = embalagemServ.obterPorIdEmbalagem(id);
        } catch (Exception e) {
            return embalagem;
        }
        return embalagem;
    }

    public List<Embalagem> embalagens(Integer entidade){

        List<Embalagem> lista = embalagemServ.obterTodosEmbalagens(entidade);
        return lista;
    }

    public Boolean deletarEmbalagem(Integer id){

        Boolean ret = false;

        try{
            Embalagem embalagem = embalagemRepo.obterPorIdEmbalagem(id);
            ret = embalagemRepo.deletarEmbalagem(embalagem);
        }catch(Exception e){
            return ret;
        }
        return ret;    
    }   

    public Embalagem salvarEmbalagem(EmbalagemInput embalagemInput) throws SQLException{

        Embalagem embalagem = new Embalagem();

        embalagem.setDescricao(embalagemInput.getDescricao());

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(embalagemInput.getEntidadeGestora());
        embalagem.setEntidadeGestora(entidadeGestora);

        try{
            if(embalagemInput.getId() == 0){
                embalagem = embalagemRepo.salvarEmbalagem(embalagem);
            }else{
                embalagem.setId(embalagemInput.getId());
                embalagem = embalagemRepo.atualizarEmbalagem(embalagem);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return embalagem;

    }

}

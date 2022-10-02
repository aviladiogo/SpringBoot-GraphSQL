package com.mines.PrecoEOferta.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.Fornecedor.Model.Fornecedor;
import com.mines.PrecoEOferta.Model.Oferta;
import com.mines.PrecoEOferta.Model.OfertaInput;
import com.mines.PrecoEOferta.Repository.OfertaRepository;
import com.mines.PrecoEOferta.Service.OfertaService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfertaGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private OfertaRepository ofertaRepo;

    @Autowired
    private OfertaService ofertaServ;

    public Oferta oferta(Integer id){

        Oferta oferta = null;
        try {
            oferta = ofertaServ.obterPorIdOferta(id);
        } catch (Exception e) {
            return oferta;
        }
        return oferta;
    }

    public List<Oferta> ofertas(Integer entidade){

        List<Oferta> lista = ofertaServ.obterTodosOfertas(entidade);
        return lista;

    }

    public Boolean deletarOferta(Integer id){

        Boolean ret = false;

        try{
            Oferta oferta = ofertaRepo.obterPorIdOferta(id);
            ret = ofertaRepo.deletarOferta(oferta);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public Oferta salvarOferta(OfertaInput ofertaInput) throws SQLException{

        Oferta oferta = new Oferta();

        oferta.setTitulo(ofertaInput.getTitulo());

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(ofertaInput.getFornecedor());
        oferta.setFornecedor(fornecedor);

        oferta.setInicioValidade(ofertaInput.getInicioValidade());
        oferta.setFinalValidade(ofertaInput.getFinalValidade());

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(ofertaInput.getEntidadeGestora());
        oferta.setEntidadeGestora(entidadeGestora);

        try{
            if(ofertaInput.getId() == 0){
                oferta = ofertaRepo.salvarOferta(oferta);
            }else{
                oferta.setId(ofertaInput.getId());
                oferta = ofertaRepo.atualizarOferta(oferta);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return oferta;

    }

}

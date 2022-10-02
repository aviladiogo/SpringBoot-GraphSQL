package com.mines.PrecoEOferta.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.Fornecedor.Model.Fornecedor;
import com.mines.PrecoEOferta.Model.ItemOferta;
import com.mines.PrecoEOferta.Model.ItemOfertaInput;
import com.mines.PrecoEOferta.Repository.ItemOfertaRepository;
import com.mines.PrecoEOferta.Service.ItemOfertaService;
import com.mines.Produto.Model.Produto;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemOfertaGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private ItemOfertaRepository itemOfertaRepo;

    @Autowired
    private ItemOfertaService itemOfertaServ;

    public ItemOferta itemOferta(Integer id){

        ItemOferta itemOferta = null;
        try {
            itemOferta = itemOfertaServ.obterPorIdItemOferta(id);
        } catch (Exception e) {
            return itemOferta;
        }
        return itemOferta;
    }

    public List<ItemOferta> itensOferta(Integer entidade){

        List<ItemOferta> lista = itemOfertaServ.obterTodosItensOferta(entidade);
        return lista;

    }

    public Boolean deletarItemOferta(Integer id){

        Boolean ret = false;

        try{
            ItemOferta itemOferta = itemOfertaRepo.obterPorIdItemOferta(id);
            ret = itemOfertaRepo.deletarItemOferta(itemOferta);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public ItemOferta salvarItemOferta(ItemOfertaInput itemOfertaInput) throws SQLException{

        ItemOferta itemOferta = new ItemOferta();

        itemOferta.setDescricao(itemOfertaInput.getDescricao());

        Produto produto = new Produto();
        produto.setId(itemOfertaInput.getProduto());
        itemOferta.setProduto(produto);

        Fornecedor fabricante = new Fornecedor();
        fabricante.setId(itemOfertaInput.getfabricante());
        itemOferta.setFabricante(fabricante);

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(itemOfertaInput.getEntidadeGestora());
        itemOferta.setEntidadeGestora(entidadeGestora);

        try{
            if(itemOfertaInput.getId() == 0){
                itemOferta = itemOfertaRepo.salvarItemOferta(itemOferta);
            }else{
                itemOferta.setId(itemOfertaInput.getId());
                itemOferta = itemOfertaRepo.atualizarItemOferta(itemOferta);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return itemOferta;

    }

}


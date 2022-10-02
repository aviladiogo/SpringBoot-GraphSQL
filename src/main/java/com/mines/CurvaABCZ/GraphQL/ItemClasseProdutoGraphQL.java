package com.mines.CurvaABCZ.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.CurvaABCZ.Model.ItemClasseProduto;
import com.mines.CurvaABCZ.Repository.CurvaAbczRepository;
import com.mines.CurvaABCZ.Repository.ItemClasseProdutoRepository;
import com.mines.CurvaABCZ.Service.ItemClasseProdutoService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemClasseProdutoGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private ItemClasseProdutoRepository itemClasseProdutoRepo;

    @Autowired
    private ItemClasseProdutoService itemClasseProdutoServ;

    @Autowired
    private CurvaAbczRepository curvaAbczRepo;

    public List<ItemClasseProduto> itemClasseProduto(Integer classeProdutoId){

        List<ItemClasseProduto> lista = itemClasseProdutoServ.obterPorIdItemClasseProduto(classeProdutoId);
        return lista;

    }

    public List<ItemClasseProduto> itensClassesProdutos(){

        List<ItemClasseProduto> lista = itemClasseProdutoServ.obterTodosItemClasseProduto();
        return lista;

    }

    public Boolean deletarItemClasseProduto(Integer id){

        Boolean ret = false;

        try{
            ret = itemClasseProdutoRepo.deletarItemClasseProduto(id);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public ItemClasseProduto salvarItemClasseProduto(Integer classeProdutoId, List<Integer> curvas) throws SQLException{

        ItemClasseProduto itemclasseProduto = new ItemClasseProduto();

        itemclasseProduto.setClasseProduto(classeProdutoId);

        try{
            for(int i=0 ; i<curvas.size(); i++){
                itemclasseProduto.setCurvaAbcz(curvaAbczRepo.obterPorIdCurvaABCZ(curvas.get(i)));
                itemclasseProduto = itemClasseProdutoRepo.salvarItemClasseProduto(itemclasseProduto);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return itemclasseProduto;

    }

}
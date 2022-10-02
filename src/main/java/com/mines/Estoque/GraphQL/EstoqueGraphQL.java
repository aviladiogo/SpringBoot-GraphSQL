package com.mines.Estoque.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.Estoque.Model.Almoxarifado;
import com.mines.Estoque.Model.Estoque;
import com.mines.Estoque.Model.EstoqueHistorico;
import com.mines.Estoque.Model.EstoqueInput;
import com.mines.Estoque.Repository.EstoqueHistoricoRepository;
import com.mines.Estoque.Repository.EstoqueRepository;
import com.mines.Estoque.Service.EstoqueService;
import com.mines.Produto.Model.Produto;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstoqueGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private EstoqueRepository estoqueRepo;

    @Autowired
    private EstoqueService estoqueServ;

    @Autowired
    private EstoqueHistoricoRepository estoqueHistoricoRepo;

    public Estoque estoque(Integer id){

        Estoque estoque = null;
        try {
            estoque = estoqueServ.obterPorIdEstoque(id);
        } catch (Exception e) {
            return estoque;
        }
        return estoque;
    }

    public List<Estoque> estoques(Integer entidade){

        List<Estoque> lista = estoqueServ.obterTodosEstoques(entidade);
        return lista;

    }

    public Boolean deletarEstoque(Integer id){

        Boolean ret = false;

        try{
            Estoque estoque = estoqueRepo.obterPorIdEstoque(id);
            ret = estoqueRepo.deletarEstoque(estoque);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public Estoque salvarEstoque(EstoqueInput estoqueInput) throws SQLException{

        Estoque estoque = new Estoque();
        EstoqueHistorico estoqueHistorico = new EstoqueHistorico();

        Empresa entidadeEstoque = new Empresa();
        entidadeEstoque.setId(estoqueInput.getEntidadeEstoque());
        estoque.setEntidadeEstoque(entidadeEstoque);
        estoqueHistorico.setEntidadeEstoque(entidadeEstoque);

        Almoxarifado almoxarifado = new Almoxarifado();
        almoxarifado.setId(estoqueInput.getAlmoxarifado());
        estoque.setAlmoxarifado(almoxarifado);
        estoqueHistorico.setAlmoxarifado(almoxarifado);

        PessoaFisica responsavel = new PessoaFisica();
        responsavel.setId(estoqueInput.getResponsavel());
        estoque.setResponsavel(responsavel);
        estoqueHistorico.setResponsavel(responsavel);

        Produto produto = new Produto();
        produto.setId(estoqueInput.getProduto());
        estoque.setProduto(produto);
        estoqueHistorico.setProduto(produto);

        estoque.setQuantidade(estoqueInput.getQuantidade());
        estoque.setGiroVendaDia(estoqueInput.getGiroVendaDia());

        estoqueHistorico.setQuantidade(estoqueInput.getQuantidade());
        estoqueHistorico.setGiroVendaDia(estoqueInput.getGiroVendaDia());

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(estoqueInput.getEntidadeGestora());
        estoque.setEntidadeGestora(entidadeGestora);
        estoqueHistorico.setEntidadeGestora(entidadeGestora);

        try{
            if(estoqueInput.getId() == 0){
                estoque = estoqueRepo.salvarEstoque(estoque);
                estoqueHistoricoRepo.salvarEstoqueHistorico(estoqueHistorico);
            }else{
                estoque.setId(estoqueInput.getId());
                estoque = estoqueRepo.atualizarEstoque(estoque);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return estoque;

    }

}



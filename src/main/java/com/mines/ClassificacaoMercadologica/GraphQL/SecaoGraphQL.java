package com.mines.ClassificacaoMercadologica.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.ClassificacaoMercadologica.Model.Departamento;
import com.mines.ClassificacaoMercadologica.Model.Secao;
import com.mines.ClassificacaoMercadologica.Model.SecaoInput;
import com.mines.ClassificacaoMercadologica.Repository.SecaoRepository;
import com.mines.ClassificacaoMercadologica.Service.SecaoService;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Model.PessoaJuridica;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecaoGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private SecaoRepository secaoRepo;

    @Autowired
    private SecaoService secaoServ;

    public Secao secao(Integer id){

        Secao secao = null;
        try {
            secao = secaoServ.obterPorIdSecao(id);
        } catch (Exception e) {
            return secao;
        }
        return secao;
    }

    public List<Secao> secoes(Integer entidade){

        List<Secao> lista = secaoServ.obterTodosSecoes(entidade);
        return lista;

    }

    public List<Secao> secoesPorDepartamento(Integer entidade, Integer departamento){

        List<Secao> lista = secaoServ.obterSecoesPorDepartamento(entidade, departamento);
        return lista;

    }

    public Boolean deletarSecao(Integer id){

        Boolean ret = false;

        try{
            Secao secao = secaoRepo.obterPorIdSecao(id);
            ret = secaoRepo.deletarSecao(secao);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public Secao salvarSecao(SecaoInput secaoInput) throws SQLException{

        Secao secao = new Secao();

        secao.setDescricao(secaoInput.getDescricao());

        Departamento departamento = new Departamento();
        departamento.setId(secaoInput.getDepartamento());
        secao.setDepartamento(departamento);

        PessoaFisica usuario = new PessoaFisica();
        usuario.setId(secaoInput.getUsuario());
        secao.setUsuario(usuario);

        PessoaJuridica entidade = new PessoaJuridica();
        entidade.setId(secaoInput.getEntidade());
        secao.setEntidade(entidade);

        try{
            if(secaoInput.getId() == 0){
                secao = secaoRepo.salvarSecao(secao);
            }else{
                secao.setId(secaoInput.getId());
                secao = secaoRepo.atualizarSecao(secao);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return secao;

    }

}
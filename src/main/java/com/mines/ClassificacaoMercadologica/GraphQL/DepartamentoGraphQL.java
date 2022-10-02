package com.mines.ClassificacaoMercadologica.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.ClassificacaoMercadologica.Model.Departamento;
import com.mines.ClassificacaoMercadologica.Model.DepartamentoInput;
import com.mines.ClassificacaoMercadologica.Repository.DepartamentoRepository;
import com.mines.ClassificacaoMercadologica.Service.DepartamentoService;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Model.PessoaJuridica;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartamentoGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private DepartamentoRepository departamentoRepo;

    @Autowired
    private DepartamentoService departamentoServ;

    public Departamento departamento(Integer id){

        Departamento departamento = null;
        try {
            departamento = departamentoServ.obterPorIdDepartamento(id);
        } catch (Exception e) {
            return departamento;
        }
        return departamento;
    }

    public List<Departamento> departamentos(Integer entidade){

        List<Departamento> lista = departamentoServ.obterTodosDepartamentos(entidade);
        return lista;

    }

    public Boolean deletarDepartamento(Integer id){

        Boolean ret = false;

        try{
            Departamento departamento = departamentoRepo.obterPorIdDepartamento(id);
            ret = departamentoRepo.deletarDepartamento(departamento);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public Departamento salvarDepartamento(DepartamentoInput departamentoInput) throws SQLException{

        Departamento departamento = new Departamento();

        departamento.setDescricao(departamentoInput.getDescricao());

        PessoaFisica usuario = new PessoaFisica();
        usuario.setId(departamentoInput.getUsuario());
        departamento.setUsuario(usuario);

        PessoaJuridica entidade = new PessoaJuridica();
        entidade.setId(departamentoInput.getEntidade());
        departamento.setEntidade(entidade);

        try{
            if(departamentoInput.getId() == 0){
                departamento = departamentoRepo.salvarDepartamento(departamento);
            }else{
                departamento.setId(departamentoInput.getId());
                departamento = departamentoRepo.atualizarDepartamento(departamento);
            }
    
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }
        
        return departamento;

    }

}


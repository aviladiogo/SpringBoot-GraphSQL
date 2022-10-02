package com.mines.Empresa.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Model.EmpresaInput;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.Empresa.Service.EmpresaService;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmpresaGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private EmpresaRepository empresaRepo;

    @Autowired
    private EmpresaService empresaServ;

    public Empresa empresa(Integer id){

        Empresa empresa = null;
        try {
            empresa = empresaServ.obterPorIdEmpresa(id);
        } catch (Exception e) {
            return empresa;
        }
        return empresa;
    }

    public List<Empresa> empresas(Integer entidade){

        List<Empresa> lista = empresaServ.obterTodosEmpresas(entidade);
        return lista;

    }

    public Boolean deletarEmpresa(Integer id){

        Boolean ret = false;

        try{
            Empresa empresa = empresaRepo.obterPorIdEmpresa(id);
            ret = empresaRepo.deletarEmpresa(empresa);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public Empresa salvarEmpresa(EmpresaInput empresaInput) throws SQLException{

        Empresa empresa = new Empresa();

        empresa.setNomeFantasia(empresaInput.getNomeFantasia());
        empresa.setRazaoSocial(empresaInput.getRazaoSocial());
        empresa.setCnpj(empresaInput.getCnpj());

        PessoaFisica usuario = new PessoaFisica();
        usuario.setId(empresaInput.getUsuario());
        empresa.setUsuario(usuario);

        empresa.setAtiva(empresaInput.getAtiva());
        empresa.setEntidadeGestora(empresaInput.getEntidadeGestora());

        try{
            if(empresaInput.getId() == 0){
                empresa = empresaRepo.salvarEmpresa(empresa);
            }else{
                empresa.setId(empresaInput.getId());
                empresa = empresaRepo.atualizarEmpresa(empresa);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return empresa;

    }

}

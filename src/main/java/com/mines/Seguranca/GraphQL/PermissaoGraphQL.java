package com.mines.Seguranca.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.Seguranca.Model.Perfil;
import com.mines.Seguranca.Model.Permissao;
import com.mines.Seguranca.Model.PermissaoInput;
import com.mines.Seguranca.Model.Transacao;
import com.mines.Seguranca.Model.Usuario;
import com.mines.Seguranca.Repository.PermissaoRepository;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PermissaoGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private PermissaoRepository permissaoRepo;

    public Permissao permissao(Integer id){

        Permissao permissao = null;
        try {
            permissao = permissaoRepo.obterPorIdPermissao(id);
        } catch (Exception e) {
            return permissao;
        }
        return permissao;
    }

    public List<Permissao> permissoes(Integer entidade){
        
        List<Permissao> lista = permissaoRepo.obterTodosPermissoes(entidade);
        return lista;
    }

    public Boolean deletarPermissao(Integer id){

        Boolean ret = false;

        try{
            Permissao permissao = permissaoRepo.obterPorIdPermissao(id);
            ret = permissaoRepo.deletarPermissao(permissao);
        }catch(Exception e){
            return ret;
        }
        return ret;    
    }   

    public Permissao salvarPermissao(PermissaoInput permissaoInput) throws SQLException{

        Permissao permissao = new Permissao();

        permissao.setDescricao(permissaoInput.getDescricao());

        Perfil perfil = new Perfil();
        perfil.setId(permissaoInput.getPerfil());
        permissao.setPerfil(perfil);

        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setId(permissaoInput.getUsuario());
        Usuario usuario = new Usuario();
        usuario.setPessoaFisicaId(pessoaFisica);
        permissao.setUsuario(usuario);

        Transacao transacao = new Transacao();
        transacao.setId(permissaoInput.getTransacao());
        permissao.setTransacao(transacao);
        
        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(permissaoInput.getEntidadeGestora());
        permissao.setEntidadeGestora(entidadeGestora);
        
        try{
            if(permissaoInput.getId() == 0){
                permissao = permissaoRepo.salvarPermissao(permissao);
            }else{
                permissao.setId(permissaoInput.getId());
                permissao = permissaoRepo.atualizarPermissao(permissao);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return permissao;

    }

}
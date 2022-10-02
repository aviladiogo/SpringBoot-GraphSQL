package com.mines.Seguranca.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.Seguranca.Model.Perfil;
import com.mines.Seguranca.Model.Usuario;
import com.mines.Seguranca.Model.UsuarioInput;
import com.mines.Seguranca.Repository.UsuarioRepository;
import com.mines.Seguranca.Service.UsuarioService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private UsuarioService usuarioServ;

    public Usuario usuario(Integer id){

        Usuario usuario = null;
        try {
            usuario = usuarioServ.obterPorIdUsuario(id);
        } catch (Exception e) {
            return usuario;
        }
        return usuario;
    }

    public List<Usuario> usuarios(Integer entidade){
        
        List<Usuario> lista = usuarioServ.obterTodosUsuarios(entidade);
        return lista;
    }

    public Boolean deletarUsuario(Integer id){

        Boolean ret = false;

        try{
            Usuario usuario = usuarioRepo.obterPorIdUsuario(id);
            ret = usuarioRepo.deletarUsuario(usuario);
        }catch(Exception e){
            return ret;
        }
        return ret;    
    }

    public Usuario validarLogin(String login, String senha){

        Usuario usuario = null;
        try {
            usuario = usuarioRepo.validarLogin(login, senha);
        } catch (Exception e) {
            return usuario;
        }
        return usuario;
    }

    public Usuario inserirUsuario(UsuarioInput usuarioInput) throws SQLException{

        Usuario usuario = new Usuario();

        try{
            usuario.setLogin(usuarioInput.getLogin());
            usuario.setSenha(usuarioInput.getSenha());
            usuario.setAtivo(usuarioInput.getAtivo());

            PessoaFisica pessoaFisica = new PessoaFisica();
            pessoaFisica.setId(usuarioInput.getPessoaFisicaId());
            usuario.setPessoaFisicaId(pessoaFisica);

            Perfil perfil = new Perfil();
            perfil.setId(usuarioInput.getPerfil());
            usuario.setPerfil(perfil);
            
            Empresa entidadeGestora = new Empresa();
            entidadeGestora.setId(usuarioInput.getEntidadeGestora());
            usuario.setEntidadeGestora(entidadeGestora);
            
            usuario = usuarioRepo.inserirUsuario(usuario);
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return usuario;
    }

    public Usuario atualizarUsuario(UsuarioInput usuarioInput) throws SQLException{

        Usuario usuario = new Usuario();

        try{
        usuario.setLogin(usuarioInput.getLogin());
        usuario.setSenha(usuarioInput.getSenha());
        usuario.setAtivo(usuarioInput.getAtivo());

        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setId(usuarioInput.getPessoaFisicaId());
        usuario.setPessoaFisicaId(pessoaFisica);

        Perfil perfil = new Perfil();
        perfil.setId(usuarioInput.getPerfil());
        usuario.setPerfil(perfil);
        
        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(usuarioInput.getEntidadeGestora());
        usuario.setEntidadeGestora(entidadeGestora);
        
        usuario = usuarioRepo.atualizarUsuario(usuario);
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return usuario;
    }

}

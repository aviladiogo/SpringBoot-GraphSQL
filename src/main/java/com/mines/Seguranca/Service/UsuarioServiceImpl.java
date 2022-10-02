package com.mines.Seguranca.Service;

import java.util.List;
import com.mines.Seguranca.Model.Usuario;
import com.mines.Seguranca.Repository.UsuarioRepository;
import com.mines.Seguranca.Repository.UsuarioRepositoryGCF;
import com.mines.Seguranca.Repository.UsuarioRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired 
    private UsuarioRepositoryWinthor usuarioRepoWinthor;

    @Autowired
    private UsuarioRepositoryGCF usuarioRepoGCF;

    @Value("${sistema_erp}") public Integer Usuario_erp;

    @Override
    public List<Usuario> obterTodosUsuarios(Integer entidade) {

        List<Usuario> usuario = null;

        switch(Usuario_erp){

            case ConstantesERP.k_minesSistemas:{
                usuario = usuarioRepo.obterTodosUsuarios(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                usuario = usuarioRepoWinthor.obterTodosUsuarios(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                usuario = usuarioRepoGCF.obterTodosUsuarios(entidade);
                break;
            }
        }

        return usuario;
    }

    @Override
    public Usuario obterPorIdUsuario(Integer id) {

        Usuario usuario = null;

        switch(Usuario_erp){

            case ConstantesERP.k_minesSistemas:{
                usuario = usuarioRepo.obterPorIdUsuario(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                usuario = usuarioRepoWinthor.obterPorIdUsuario(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                usuario = usuarioRepoGCF.obterPorIdUsuario(id);
                break;
            }
        }

        return usuario;
    }

}


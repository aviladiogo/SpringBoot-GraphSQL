package com.mines.Seguranca.Service;

import java.util.List;
import com.mines.Seguranca.Model.Permissao;
import com.mines.Seguranca.Repository.PermissaoRepository;
import com.mines.Seguranca.Repository.PermissaoRepositoryGCF;
import com.mines.Seguranca.Repository.PermissaoRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PermissaoServiceImpl implements PermissaoService{
    
    @Autowired
    private PermissaoRepository permissaoRepo;

    @Autowired 
    private PermissaoRepositoryWinthor permissaoRepoWinthor;

    @Autowired
    private PermissaoRepositoryGCF permissaoRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<Permissao> obterTodosPermissoes(Integer entidade) {

        List<Permissao> permissao = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                permissao = permissaoRepo.obterTodosPermissoes(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                permissao = permissaoRepoWinthor.obterTodosPermissoes(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                permissao = permissaoRepoGCF.obterTodosPermissoes(entidade);
                break;
            }
        }

        return permissao;
    }

    @Override
    public Permissao obterPorIdPermissao(Integer id) {

        Permissao permissao = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                permissao = permissaoRepo.obterPorIdPermissao(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                permissao = permissaoRepoWinthor.obterPorIdPermissao(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                permissao = permissaoRepoGCF.obterPorIdPermissao(id);
                break;
            }
        }

        return permissao;
    }
}


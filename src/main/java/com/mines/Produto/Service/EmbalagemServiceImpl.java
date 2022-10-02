package com.mines.Produto.Service;

import java.util.List;
import com.mines.Produto.Model.Embalagem;
import com.mines.Produto.Repository.EmbalagemRepository;
import com.mines.Produto.Repository.EmbalagemRepositoryGCF;
import com.mines.Produto.Repository.EmbalagemRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmbalagemServiceImpl implements EmbalagemService{
    
    @Autowired
    private EmbalagemRepository embalagemRepo;

    @Autowired 
    private EmbalagemRepositoryWinthor embalagemRepoWinthor;

    @Autowired
    private EmbalagemRepositoryGCF embalagemRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<Embalagem> obterTodosEmbalagens(Integer entidade) {

        List<Embalagem> embalagem = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                embalagem = embalagemRepo.obterTodosEmbalagens(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                embalagem = embalagemRepoWinthor.obterTodosEmbalagens(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                embalagem = embalagemRepoGCF.obterTodosEmbalagens(entidade);
                break;
            }
        }

        return embalagem;
    }

    @Override
    public Embalagem obterPorIdEmbalagem(Integer id) {

        Embalagem embalagem = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                embalagem = embalagemRepo.obterPorIdEmbalagem(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                embalagem = embalagemRepoWinthor.obterPorIdEmbalagem(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                embalagem = embalagemRepoGCF.obterPorIdEmbalagem(id);
                break;
            }
        }

        return embalagem;
    }
}


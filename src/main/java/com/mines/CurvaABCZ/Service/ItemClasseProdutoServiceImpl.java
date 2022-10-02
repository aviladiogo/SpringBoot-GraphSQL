package com.mines.CurvaABCZ.Service;

import java.util.List;
import com.mines.CurvaABCZ.Model.ItemClasseProduto;
import com.mines.CurvaABCZ.Repository.ItemClasseProdutoRepository;
import com.mines.CurvaABCZ.Repository.ItemClasseProdutoRepositoryGCF;
import com.mines.CurvaABCZ.Repository.ItemClasseProdutoRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ItemClasseProdutoServiceImpl implements ItemClasseProdutoService{
    
    @Autowired
    private ItemClasseProdutoRepository itemClasseProdutoRepo;

    @Autowired 
    private ItemClasseProdutoRepositoryWinthor itemClasseProdutoRepoWinthor;

    @Autowired
    private ItemClasseProdutoRepositoryGCF itemClasseProdutoRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<ItemClasseProduto> obterTodosItemClasseProduto() {

        List<ItemClasseProduto> itensClasseProduto = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                itensClasseProduto = itemClasseProdutoRepo.obterTodosItemClasseProduto();
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                itensClasseProduto = itemClasseProdutoRepoWinthor.obterTodosItemClasseProduto();
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                itensClasseProduto = itemClasseProdutoRepoGCF.obterTodosItemClasseProduto();
                break;
            }
        }

        return itensClasseProduto;
    }

    @Override
    public List<ItemClasseProduto> obterPorIdItemClasseProduto(Integer id) {

        List<ItemClasseProduto> itensClasseProduto = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                itensClasseProduto = itemClasseProdutoRepo.obterPorIdItemClasseProduto(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                itensClasseProduto = itemClasseProdutoRepoWinthor.obterPorIdItemClasseProduto(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                itensClasseProduto = itemClasseProdutoRepoGCF.obterPorIdItemClasseProduto(id);
                break;
            }
        }

        return itensClasseProduto;
    }
}


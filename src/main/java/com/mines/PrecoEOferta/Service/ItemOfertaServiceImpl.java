package com.mines.PrecoEOferta.Service;

import java.util.List;
import com.mines.PrecoEOferta.Model.ItemOferta;
import com.mines.PrecoEOferta.Repository.ItemOfertaRepository;
import com.mines.PrecoEOferta.Repository.ItemOfertaRepositoryGCF;
import com.mines.PrecoEOferta.Repository.ItemOfertaRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ItemOfertaServiceImpl implements ItemOfertaService{
    
    @Autowired
    private ItemOfertaRepository itemOfertaRepo;

    @Autowired 
    private ItemOfertaRepositoryWinthor itemOfertaRepoWinthor;

    @Autowired
    private ItemOfertaRepositoryGCF itemOfertaRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<ItemOferta> obterTodosItensOferta(Integer entidade) {

        List<ItemOferta> itemOferta = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                itemOferta = itemOfertaRepo.obterTodosItensOferta(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                itemOferta = itemOfertaRepoWinthor.obterTodosItensOferta(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                itemOferta = itemOfertaRepoGCF.obterTodosItensOferta(entidade);
                break;
            }
        }

        return itemOferta;
    }

    @Override
    public ItemOferta obterPorIdItemOferta(Integer id) {

        ItemOferta itemOferta = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                itemOferta = itemOfertaRepo.obterPorIdItemOferta(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                itemOferta = itemOfertaRepoWinthor.obterPorIdItemOferta(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                itemOferta = itemOfertaRepoGCF.obterPorIdItemOferta(id);
                break;
            }
        }

        return itemOferta;
    }
}


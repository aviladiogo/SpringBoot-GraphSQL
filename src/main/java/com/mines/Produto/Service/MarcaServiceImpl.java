package com.mines.Produto.Service;

import java.util.List;
import com.mines.Produto.Model.Marca;
import com.mines.Produto.Repository.MarcaRepository;
import com.mines.Produto.Repository.MarcaRepositoryGCF;
import com.mines.Produto.Repository.MarcaRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MarcaServiceImpl implements MarcaService{
    
    @Autowired
    private MarcaRepository marcaRepo;

    @Autowired 
    private MarcaRepositoryWinthor marcaRepoWinthor;

    @Autowired
    private MarcaRepositoryGCF marcaRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<Marca> obterTodosMarcas(Integer entidade) {

        List<Marca> marca = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                marca = marcaRepo.obterTodosMarcas(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                marca = marcaRepoWinthor.obterTodosMarcas(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                marca = marcaRepoGCF.obterTodosMarcas(entidade);
                break;
            }
        }

        return marca;
    }

    @Override
    public Marca obterPorIdMarca(Integer id) {

        Marca marca = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                marca = marcaRepo.obterPorIdMarca(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                marca = marcaRepoWinthor.obterPorIdMarca(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                marca = marcaRepoGCF.obterPorIdMarca(id);
                break;
            }
        }

        return marca;
    }
}


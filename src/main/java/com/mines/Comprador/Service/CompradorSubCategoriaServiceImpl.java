package com.mines.Comprador.Service;

import java.util.List;
import com.mines.Comprador.Model.CompradorSubCategoria;
import com.mines.Comprador.Repository.CompradorSubCategoriaRepository;
import com.mines.Comprador.Repository.CompradorSubCategoriaRepositoryGCF;
import com.mines.Comprador.Repository.CompradorSubCategoriaRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CompradorSubCategoriaServiceImpl implements CompradorSubCategoriaService{
    
    @Autowired
    private CompradorSubCategoriaRepository compradorSubCategoriaRepo;

    @Autowired 
    private CompradorSubCategoriaRepositoryWinthor compradorSubCategoriaRepoWinthor;

    @Autowired
    private CompradorSubCategoriaRepositoryGCF compradorSubCategoriaRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<CompradorSubCategoria> obterTodosCompradoresSubCategoria(Integer entidade) {

        List<CompradorSubCategoria> compradorSubCategoria = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                compradorSubCategoria = compradorSubCategoriaRepo.obterTodosCompradoresSubCategoria(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                compradorSubCategoria = compradorSubCategoriaRepoWinthor.obterTodosCompradoresSubCategoria(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                compradorSubCategoria = compradorSubCategoriaRepoGCF.obterTodosCompradoresSubCategoria(entidade);
                break;
            }
        }

        return compradorSubCategoria;
    }

    @Override
    public CompradorSubCategoria obterPorIdCompradorSubCategoria(Integer id) {

        CompradorSubCategoria compradorSubCategoria = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                compradorSubCategoria = compradorSubCategoriaRepo.obterPorIdCompradorSubCategoria(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                compradorSubCategoria = compradorSubCategoriaRepoWinthor.obterPorIdCompradorSubCategoria(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                compradorSubCategoria = compradorSubCategoriaRepoGCF.obterPorIdCompradorSubCategoria(id);
                break;
            }
        }

        return compradorSubCategoria;
    }
}


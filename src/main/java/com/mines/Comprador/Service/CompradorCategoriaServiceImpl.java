package com.mines.Comprador.Service;

import java.util.List;
import com.mines.Comprador.Model.CompradorCategoria;
import com.mines.Comprador.Repository.CompradorCategoriaRepository;
import com.mines.Comprador.Repository.CompradorCategoriaRepositoryGCF;
import com.mines.Comprador.Repository.CompradorCategoriaRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CompradorCategoriaServiceImpl implements CompradorCategoriaService{
    
    @Autowired
    private CompradorCategoriaRepository compradorCategoriaRepo;

    @Autowired 
    private CompradorCategoriaRepositoryWinthor compradorCategoriaRepoWinthor;

    @Autowired
    private CompradorCategoriaRepositoryGCF compradorCategoriaRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<CompradorCategoria> obterTodosCompradoresCategoria(Integer entidade) {

        List<CompradorCategoria> compradorCategoria = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                compradorCategoria = compradorCategoriaRepo.obterTodosCompradoresCategoria(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                compradorCategoria = compradorCategoriaRepoWinthor.obterTodosCompradoresCategoria(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                compradorCategoria = compradorCategoriaRepoGCF.obterTodosCompradoresCategoria(entidade);
                break;
            }
        }

        return compradorCategoria;
    }

    @Override
    public CompradorCategoria obterPorIdCompradorCategoria(Integer id) {

        CompradorCategoria compradorCategoria = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                compradorCategoria = compradorCategoriaRepo.obterPorIdCompradorCategoria(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                compradorCategoria = compradorCategoriaRepoWinthor.obterPorIdCompradorCategoria(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                compradorCategoria = compradorCategoriaRepoGCF.obterPorIdCompradorCategoria(id);
                break;
            }
        }

        return compradorCategoria;
    }
}


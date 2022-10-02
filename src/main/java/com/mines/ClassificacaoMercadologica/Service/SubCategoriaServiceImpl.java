package com.mines.ClassificacaoMercadologica.Service;

import java.util.List;
import com.mines.ClassificacaoMercadologica.Model.SubCategoria;
import com.mines.ClassificacaoMercadologica.Repository.SubCategoriaRepository;
import com.mines.ClassificacaoMercadologica.Repository.SubCategoriaRepositoryGCF;
import com.mines.ClassificacaoMercadologica.Repository.SubCategoriaRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SubCategoriaServiceImpl implements SubCategoriaService {
    
    @Autowired
    private SubCategoriaRepository subCategoriaRepo;

    @Autowired 
    private SubCategoriaRepositoryWinthor subCategoriaRepoWinthor;

    @Autowired
    private SubCategoriaRepositoryGCF subCategoriaRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<SubCategoria> obterTodosSubCategorias(Integer entidade) {

        List<SubCategoria> subCategorias = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                subCategorias = subCategoriaRepo.obterTodosSubCategorias(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                subCategorias = subCategoriaRepoWinthor.obterTodosSubCategorias(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                subCategorias = subCategoriaRepoGCF.obterTodosSubCategorias(entidade);
                break;
            }
        }

        return subCategorias;
    }

    @Override
    public List<SubCategoria> obterSubCategoriasPorCategoria(Integer entidade, Integer categoria) {

        List<SubCategoria> subCategorias = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                subCategorias = subCategoriaRepo.obterSubCategoriasPorCategoria(entidade, categoria);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                subCategorias = subCategoriaRepoWinthor.obterSubCategoriasPorCategoria(entidade, categoria);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                subCategorias = subCategoriaRepoGCF.obterSubCategoriasPorCategoria(entidade, categoria);
                break;
            }
        }

        return subCategorias;
    }

    @Override
    public SubCategoria obterPorIdSubCategoria(Integer id) {

        SubCategoria subCategoria = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                subCategoria = subCategoriaRepo.obterPorIdSubCategoria(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                subCategoria = subCategoriaRepoWinthor.obterPorIdSubCategoria(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                subCategoria = subCategoriaRepoGCF.obterPorIdSubCategoria(id);
                break;
            }
        }

        return subCategoria;
    }
}


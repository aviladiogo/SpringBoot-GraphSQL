package com.mines.ClassificacaoMercadologica.Service;

import java.util.List;
import com.mines.ClassificacaoMercadologica.Model.Categoria;
import com.mines.ClassificacaoMercadologica.Repository.CategoriaRepository;
import com.mines.ClassificacaoMercadologica.Repository.CategoriaRepositoryGCF;
import com.mines.ClassificacaoMercadologica.Repository.CategoriaRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepo;

    @Autowired 
    private CategoriaRepositoryWinthor categoriaRepoWinthor;

    @Autowired
    private CategoriaRepositoryGCF categoriaRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<Categoria> obterTodosCategorias(Integer entidade) {

        List<Categoria> categorias = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                categorias = categoriaRepo.obterTodosCategorias(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                categorias = categoriaRepoWinthor.obterTodosCategorias(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                categorias = categoriaRepoGCF.obterTodosCategorias(entidade);
                break;
            }
        }

        return categorias;
    }

    @Override
    public List<Categoria> obterCategoriasPorSecao(Integer entidade, Integer secao) {

        List<Categoria> categorias = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                categorias = categoriaRepo.obterCategoriasPorSecao(entidade, secao);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                categorias = categoriaRepoWinthor.obterCategoriasPorSecao(entidade, secao);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                categorias = categoriaRepoGCF.obterCategoriasPorSecao(entidade, secao);
                break;
            }
        }

        return categorias;
    }

    @Override
    public Categoria obterPorIdCategoria(Integer id) {

        Categoria categoria = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                categoria = categoriaRepo.obterPorIdCategoria(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                categoria = categoriaRepoWinthor.obterPorIdCategoria(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                categoria = categoriaRepoGCF.obterPorIdCategoria(id);
                break;
            }
        }

        return categoria;
    }
}


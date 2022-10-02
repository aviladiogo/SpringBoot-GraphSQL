package com.mines.CurvaABCZ.Service;

import java.util.List;
import com.mines.CurvaABCZ.Model.ClasseProduto;
import com.mines.CurvaABCZ.Repository.ClasseProdutoRepository;
import com.mines.CurvaABCZ.Repository.ClasseProdutoRepositoryGCF;
import com.mines.CurvaABCZ.Repository.ClasseProdutoRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ClasseProdutoServiceImpl implements ClasseProdutoService{
    
    @Autowired
    private ClasseProdutoRepository classeProdutoRepo;

    @Autowired 
    private ClasseProdutoRepositoryWinthor classeProdutoRepoWinthor;

    @Autowired
    private ClasseProdutoRepositoryGCF classeProdutoRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<ClasseProduto> obterTodosClasseProduto(Integer entidade) {

        List<ClasseProduto> classeProduto = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                classeProduto = classeProdutoRepo.obterTodosClasseProduto(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                classeProduto = classeProdutoRepoWinthor.obterTodosClasseProduto(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                classeProduto = classeProdutoRepoGCF.obterTodosClasseProduto(entidade);
                break;
            }
        }

        return classeProduto;
    }

    @Override
    public ClasseProduto obterPorIdClasseProduto(Integer id) {

        ClasseProduto classeProduto = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                classeProduto = classeProdutoRepo.obterPorIdClasseProduto(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                classeProduto = classeProdutoRepoWinthor.obterPorIdClasseProduto(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                classeProduto = classeProdutoRepoGCF.obterPorIdClasseProduto(id);
                break;
            }
        }

        return classeProduto;
    }
}


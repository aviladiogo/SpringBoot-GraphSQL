package com.mines.Empresa.Service;

import java.util.List;
import com.mines.Empresa.Model.Filial;
import com.mines.Empresa.Repository.FilialRepository;
import com.mines.Empresa.Repository.FilialRepositoryGCF;
import com.mines.Empresa.Repository.FilialRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FilialServiceImpl implements FilialService{
    
    @Autowired
    private FilialRepository filialRepo;

    @Autowired 
    private FilialRepositoryWinthor filialRepoWinthor;

    @Autowired
    private FilialRepositoryGCF filialRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<Filial> obterTodosFiliais(Integer entidade) {

        List<Filial> filial = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                filial = filialRepo.obterTodosFiliais(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                filial = filialRepoWinthor.obterTodosFiliais(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                filial = filialRepoGCF.obterTodosFiliais(entidade);
                break;
            }
        }

        return filial;
    }

    @Override
    public List<Filial> obterTodosFiliaisPorGrupoCompraInterno(Integer entidade, Integer grupoCompraInterno) {

        List<Filial> filial = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                filial = filialRepo.obterTodosFiliaisPorGrupoCompraInterno(entidade, grupoCompraInterno);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                filial = filialRepoWinthor.obterTodosFiliaisPorGrupoCompraInterno(entidade, grupoCompraInterno);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                filial = filialRepoGCF.obterTodosFiliaisPorGrupoCompraInterno(entidade, grupoCompraInterno);
                break;
            }
        }

        return filial;
    }

    @Override
    public Filial obterPorIdFilial(Integer id) {

        Filial filial = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                filial = filialRepo.obterPorIdFilial(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                filial = filialRepoWinthor.obterPorIdFilial(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                filial = filialRepoGCF.obterPorIdFilial(id);
                break;
            }
        }

        return filial;
    }
    
}


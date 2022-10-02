package com.mines.Colaborador.Service;

import java.util.List;
import com.mines.Colaborador.Model.Funcao;
import com.mines.Colaborador.Repository.FuncaoRepository;
import com.mines.Colaborador.Repository.FuncaoRepositoryGCF;
import com.mines.Colaborador.Repository.FuncaoRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FuncaoServiceImpl implements FuncaoService{
    
    @Autowired
    private FuncaoRepository funcaoRepo;

    @Autowired 
    private FuncaoRepositoryWinthor funcaoRepoWinthor;

    @Autowired
    private FuncaoRepositoryGCF funcaoRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<Funcao> obterTodosFuncoes(Integer entidade) {

        List<Funcao> funcao = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                funcao = funcaoRepo.obterTodosFuncoes(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                funcao = funcaoRepoWinthor.obterTodosFuncoes(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                funcao = funcaoRepoGCF.obterTodosFuncoes(entidade);
                break;
            }
        }

        return funcao;
    }

    @Override
    public Funcao obterPorIdFuncao(Integer id) {

        Funcao funcao = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                funcao = funcaoRepo.obterPorIdFuncao(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                funcao = funcaoRepoWinthor.obterPorIdFuncao(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                funcao = funcaoRepoGCF.obterPorIdFuncao(id);
                break;
            }
        }

        return funcao;
    }
}


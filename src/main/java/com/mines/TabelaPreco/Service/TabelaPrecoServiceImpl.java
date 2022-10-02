package com.mines.TabelaPreco.Service;

import java.util.List;
import com.mines.TabelaPreco.Model.TabelaPreco;
import com.mines.TabelaPreco.Repository.TabelaPrecoRepository;
import com.mines.TabelaPreco.Repository.TabelaPrecoRepositoryGCF;
import com.mines.TabelaPreco.Repository.TabelaPrecoRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TabelaPrecoServiceImpl implements TabelaPrecoService{
    
    @Autowired
    private TabelaPrecoRepository tabelaPrecoRepo;

    @Autowired 
    private TabelaPrecoRepositoryWinthor tabelaPrecoRepoWinthor;

    @Autowired
    private TabelaPrecoRepositoryGCF tabelaPrecoRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<TabelaPreco> obterTodosTabelasPreco(Integer entidade) {

        List<TabelaPreco> tabelaPreco = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                tabelaPreco = tabelaPrecoRepo.obterTodosTabelasPreco(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                tabelaPreco = tabelaPrecoRepoWinthor.obterTodosTabelasPreco(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                tabelaPreco = tabelaPrecoRepoGCF.obterTodosTabelasPreco(entidade);
                break;
            }
        }

        return tabelaPreco;
    }

    @Override
    public TabelaPreco obterPorIdTabelaPreco(Integer id) {

        TabelaPreco tabelaPreco = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                tabelaPreco = tabelaPrecoRepo.obterPorIdTabelaPreco(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                tabelaPreco = tabelaPrecoRepoWinthor.obterPorIdTabelaPreco(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                tabelaPreco = tabelaPrecoRepoGCF.obterPorIdTabelaPreco(id);
                break;
            }
        }

        return tabelaPreco;
    }
}


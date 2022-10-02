package com.mines.Estoque.Service;

import java.util.List;
import com.mines.Estoque.Model.EstoqueHistorico;
import com.mines.Estoque.Repository.EstoqueHistoricoRepository;
import com.mines.Estoque.Repository.EstoqueHistoricoRepositoryGCF;
import com.mines.Estoque.Repository.EstoqueHistoricoRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EstoqueHistoricoServiceImpl implements EstoqueHistoricoService{

    @Autowired
    private EstoqueHistoricoRepository estoqueHistoricoRepo;

    @Autowired 
    private EstoqueHistoricoRepositoryWinthor estoqueHistoricoRepoWinthor;

    @Autowired
    private EstoqueHistoricoRepositoryGCF estoqueHistoricoRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<EstoqueHistorico> obterTodosEstoquesHistorico(Integer entidade) {

        List<EstoqueHistorico> estoqueHistorico = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                estoqueHistorico = estoqueHistoricoRepo.obterTodosEstoquesHistorico(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                estoqueHistorico = estoqueHistoricoRepoWinthor.obterTodosEstoquesHistorico(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                estoqueHistorico = estoqueHistoricoRepoGCF.obterTodosEstoquesHistorico(entidade);
                break;
            }
        }

        return estoqueHistorico;
    }

    @Override
    public EstoqueHistorico obterPorIdEstoqueHistorico(Integer fornecedor) {

        EstoqueHistorico estoqueHistorico = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                estoqueHistorico = estoqueHistoricoRepo.obterPorIdEstoqueHistorico(fornecedor);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                estoqueHistorico = estoqueHistoricoRepoWinthor.obterPorIdEstoqueHistorico(fornecedor);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                estoqueHistorico = estoqueHistoricoRepoGCF.obterPorIdEstoqueHistorico(fornecedor);
                break;
            }
        }

        return estoqueHistorico;
    }
    
}

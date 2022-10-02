package com.mines.SugestaoCompra.Service;

import java.util.List;
import com.mines.SugestaoCompra.Model.SituacaoSugestaoCompra;
import com.mines.SugestaoCompra.Repository.SituacaoSugestaoCompraRepository;
import com.mines.SugestaoCompra.Repository.SituacaoSugestaoCompraRepositoryGCF;
import com.mines.SugestaoCompra.Repository.SituacaoSugestaoCompraRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SituacaoSugestaoCompraServiceImpl implements SituacaoSugestaoCompraService {

    @Autowired
    private SituacaoSugestaoCompraRepository situacaoSugestaoCompraRepo;

    @Autowired
    private SituacaoSugestaoCompraRepositoryWinthor situacaoSugestaoCompraRepoWinthor;

    @Autowired
    private SituacaoSugestaoCompraRepositoryGCF situacaoSugestaoCompraRepoGCF;

    @Value("${sistema_erp}")
    public Integer sistema_erp;

    @Override
    public List<SituacaoSugestaoCompra> obterTodosSituacoesSugestaoCompras(Integer entidade) {

        List<SituacaoSugestaoCompra> situacaoSugestaoCompra = null;

        switch (sistema_erp) {

            case ConstantesERP.k_minesSistemas: {
                situacaoSugestaoCompra = situacaoSugestaoCompraRepo.obterTodosSituacoesSugestaoCompras(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas: {
                situacaoSugestaoCompra = situacaoSugestaoCompraRepoWinthor.obterTodosSituacoesSugestaoCompras(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas: {
                situacaoSugestaoCompra = situacaoSugestaoCompraRepoGCF.obterTodosSituacoesSugestaoCompras(entidade);
                break;
            }
        }

        return situacaoSugestaoCompra;
    }

    @Override
    public SituacaoSugestaoCompra obterPorIdSituacaoSugestaoCompra(Integer id) {

        SituacaoSugestaoCompra situacaoSugestaoCompra = null;

        switch (sistema_erp) {

            case ConstantesERP.k_minesSistemas: {
                situacaoSugestaoCompra = situacaoSugestaoCompraRepo.obterPorIdSituacaoSugestaoCompra(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas: {
                situacaoSugestaoCompra = situacaoSugestaoCompraRepoWinthor.obterPorIdSituacaoSugestaoCompra(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas: {
                situacaoSugestaoCompra = situacaoSugestaoCompraRepoGCF.obterPorIdSituacaoSugestaoCompra(id);
                break;
            }
        }

        return situacaoSugestaoCompra;
    }
}

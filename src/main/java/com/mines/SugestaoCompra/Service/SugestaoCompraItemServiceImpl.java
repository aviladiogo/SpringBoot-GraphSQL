package com.mines.SugestaoCompra.Service;

import java.util.List;
import com.mines.SugestaoCompra.Model.SugestaoCompraItem;
import com.mines.SugestaoCompra.Repository.SugestaoCompraItemRepository;
import com.mines.SugestaoCompra.Repository.SugestaoCompraItemRepositoryGCF;
import com.mines.SugestaoCompra.Repository.SugestaoCompraItemRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SugestaoCompraItemServiceImpl implements SugestaoCompraItemService {

    @Autowired
    private SugestaoCompraItemRepository sugestaoCompraItemRepo;

    @Autowired
    private SugestaoCompraItemRepositoryWinthor sugestaoCompraItemRepoWinthor;

    @Autowired
    private SugestaoCompraItemRepositoryGCF sugestaoCompraItemRepoGCF;

    @Value("${sistema_erp}")
    public Integer sistema_erp;

    @Override
    public List<SugestaoCompraItem> obterTodosSugestoesCompraItem(Integer entidade) {

        List<SugestaoCompraItem> sugestaoCompraItem = null;

        switch (sistema_erp) {

            case ConstantesERP.k_minesSistemas: {
                sugestaoCompraItem = sugestaoCompraItemRepo.obterTodosSugestoesCompraItem(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas: {
                sugestaoCompraItem = sugestaoCompraItemRepoWinthor.obterTodosSugestoesCompraItem(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas: {
                sugestaoCompraItem = sugestaoCompraItemRepoGCF.obterTodosSugestoesCompraItem(entidade);
                break;
            }
        }

        return sugestaoCompraItem;
    }

    @Override
    public SugestaoCompraItem obterPorIdSugestaoCompraItem(Integer id) {

        SugestaoCompraItem sugestaoCompraItem = null;

        switch (sistema_erp) {

            case ConstantesERP.k_minesSistemas: {
                sugestaoCompraItem = sugestaoCompraItemRepo.obterPorIdSugestaoCompraItem(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas: {
                sugestaoCompraItem = sugestaoCompraItemRepoWinthor.obterPorIdSugestaoCompraItem(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas: {
                sugestaoCompraItem = sugestaoCompraItemRepoGCF.obterPorIdSugestaoCompraItem(id);
                break;
            }
        }

        return sugestaoCompraItem;
    }
}

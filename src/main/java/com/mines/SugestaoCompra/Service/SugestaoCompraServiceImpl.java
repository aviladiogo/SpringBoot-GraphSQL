package com.mines.SugestaoCompra.Service;

import java.util.List;

import com.mines.SugestaoCompra.Model.FiltroSugestaoCompraInput;
import com.mines.SugestaoCompra.Model.SugestaoCompra;
import com.mines.SugestaoCompra.Repository.SugestaoCompraRepository;
import com.mines.SugestaoCompra.Repository.SugestaoCompraRepositoryGCF;
import com.mines.SugestaoCompra.Repository.SugestaoCompraRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SugestaoCompraServiceImpl implements SugestaoCompraService {

    @Autowired
    private SugestaoCompraRepository sugestaoCompraRepo;

    @Autowired
    private SugestaoCompraRepositoryWinthor sugestaoCompraRepoWinthor;

    @Autowired
    private SugestaoCompraRepositoryGCF sugestaoCompraRepoGCF;

    @Value("${sistema_erp}")
    public Integer sistema_erp;

    @Override
    public List<SugestaoCompra> obterTodosSugestaoCompra(Integer entidade) {

        List<SugestaoCompra> sugestaoCompra = null;

        switch (sistema_erp) {

            case ConstantesERP.k_minesSistemas: {
                sugestaoCompra = sugestaoCompraRepo.obterTodosSugestaoCompra(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas: {
                sugestaoCompra = sugestaoCompraRepoWinthor.obterTodosSugestaoCompra(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas: {
                sugestaoCompra = sugestaoCompraRepoGCF.obterTodosSugestaoCompra(entidade);
                break;
            }
        }

        return sugestaoCompra;
    }

    @Override
    public List<SugestaoCompra> obterTodosSugestaoCompraPorFiltro(Integer entidade,
            FiltroSugestaoCompraInput filtroSugestaoCompraInput) {

        List<SugestaoCompra> sugestaoCompra = null;

        switch (sistema_erp) {

            case ConstantesERP.k_minesSistemas: {
                sugestaoCompra = sugestaoCompraRepo.obterTodosSugestaoCompraPorFiltro(entidade,
                        filtroSugestaoCompraInput);
                break;
            }

            case ConstantesERP.k_winthorSistemas: {
                sugestaoCompra = sugestaoCompraRepoWinthor.obterTodosSugestaoCompraPorFiltro(entidade,
                        filtroSugestaoCompraInput);
                break;
            }

            case ConstantesERP.k_gcfSistemas: {
                sugestaoCompra = sugestaoCompraRepoGCF.obterTodosSugestaoCompraPorFiltro(entidade,
                        filtroSugestaoCompraInput);
                break;
            }
        }

        return sugestaoCompra;
    }

    @Override
    public SugestaoCompra obterPorIdSugestaoCompra(Integer id) {

        SugestaoCompra sugestaoCompra = null;

        switch (sistema_erp) {

            case ConstantesERP.k_minesSistemas: {
                sugestaoCompra = sugestaoCompraRepo.obterPorIdSugestaoCompra(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas: {
                sugestaoCompra = sugestaoCompraRepoWinthor.obterPorIdSugestaoCompra(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas: {
                sugestaoCompra = sugestaoCompraRepoGCF.obterPorIdSugestaoCompra(id);
                break;
            }
        }

        return sugestaoCompra;
    }

}

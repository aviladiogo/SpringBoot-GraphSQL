package com.mines.TabelaPreco.Service;

import java.util.List;
import com.mines.TabelaPreco.Model.ItemPedidoVenda;
import com.mines.TabelaPreco.Repository.ItemPedidoVendaRepository;
import com.mines.TabelaPreco.Repository.ItemPedidoVendaRepositoryGCF;
import com.mines.TabelaPreco.Repository.ItemPedidoVendaRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoVendaServiceImpl implements ItemPedidoVendaService {

    @Autowired
    private ItemPedidoVendaRepository itemPedidoVendaRepo;

    @Autowired
    private ItemPedidoVendaRepositoryWinthor itemPedidoVendaRepoWinthor;

    @Autowired
    private ItemPedidoVendaRepositoryGCF itemPedidoVendaRepoGCF;

    @Value("${sistema_erp}")
    public Integer sistema_erp;

    @Override
    public List<ItemPedidoVenda> obterTodosItensPedidoVenda(Integer entidade) {

        List<ItemPedidoVenda> itemPedido = null;

        switch (sistema_erp) {

            case ConstantesERP.k_minesSistemas: {
                itemPedido = itemPedidoVendaRepo.obterTodosItensPedidoVenda(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas: {
                itemPedido = itemPedidoVendaRepoWinthor.obterTodosItensPedidoVenda(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas: {
                itemPedido = itemPedidoVendaRepoGCF.obterTodosItensPedidoVenda(entidade);
                break;
            }
        }

        return itemPedido;
    }

    @Override
    public ItemPedidoVenda obterPorIdItemPedidoVenda(Integer id) {

        ItemPedidoVenda itemPedido = null;

        switch (sistema_erp) {

            case ConstantesERP.k_minesSistemas: {
                itemPedido = itemPedidoVendaRepo.obterPorIdItemPedidoVenda(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas: {
                itemPedido = itemPedidoVendaRepoWinthor.obterPorIdItemPedidoVenda(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas: {
                itemPedido = itemPedidoVendaRepoGCF.obterPorIdItemPedidoVenda(id);
                break;
            }
        }

        return itemPedido;
    }

    @Override
    public List<ItemPedidoVenda> obterTodosItensPedidoVendaPorTabelaPreco(Integer entidade, Integer tabelaPreco) {

        List<ItemPedidoVenda> itemPedido = null;

        switch (sistema_erp) {

            case ConstantesERP.k_minesSistemas: {
                itemPedido = itemPedidoVendaRepo.obterTodosItensPedidoVendaPorTabelaPreco(entidade, tabelaPreco);
                break;
            }

            case ConstantesERP.k_winthorSistemas: {
                itemPedido = itemPedidoVendaRepoWinthor.obterTodosItensPedidoVendaPorTabelaPreco(entidade, tabelaPreco);
                break;
            }

            case ConstantesERP.k_gcfSistemas: {
                itemPedido = itemPedidoVendaRepoGCF.obterTodosItensPedidoVendaPorTabelaPreco(entidade, tabelaPreco);
                break;
            }
        }

        return itemPedido;
    }
}

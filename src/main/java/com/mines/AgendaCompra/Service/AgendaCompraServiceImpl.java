package com.mines.AgendaCompra.Service;

import java.text.ParseException;
import java.util.List;
import com.mines.AgendaCompra.Model.AgendaCompra;
import com.mines.AgendaCompra.Model.FiltroAgendaCompraInput;
import com.mines.AgendaCompra.Repository.AgendaCompraRepository;
import com.mines.AgendaCompra.Repository.AgendaCompraRepositoryGCF;
import com.mines.AgendaCompra.Repository.AgendaCompraRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AgendaCompraServiceImpl implements AgendaCompraService{
    
    @Autowired
    private AgendaCompraRepository agendaCompraRepo;

    @Autowired 
    private AgendaCompraRepositoryWinthor agendaCompraRepoWinthor;

    @Autowired
    private AgendaCompraRepositoryGCF agendaCompraRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<AgendaCompra> obterTodosAgendaCompra(Integer entidade) {

        List<AgendaCompra> agendaCompra = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                agendaCompra = agendaCompraRepo.obterTodosAgendaCompra(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                agendaCompra = agendaCompraRepoWinthor.obterTodosAgendaCompra(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                agendaCompra = agendaCompraRepoGCF.obterTodosAgendaCompra(entidade);
                break;
            }
        }

        return agendaCompra;
    }

    @Override
    public AgendaCompra obterPorIdAgendaCompra(Integer id) {

        AgendaCompra agendaCompra = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                agendaCompra = agendaCompraRepo.obterPorIdAgendaCompra(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                agendaCompra = agendaCompraRepoWinthor.obterPorIdAgendaCompra(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                agendaCompra = agendaCompraRepoGCF.obterPorIdAgendaCompra(id);
                break;
            }
        }

        return agendaCompra;
    }

    @Override
    public List<AgendaCompra> obterTodosAgendaCompraPorFiltro(FiltroAgendaCompraInput filtroAgendaCompraInput) throws ParseException {
        
        List<AgendaCompra> agendaCompra = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                agendaCompra = agendaCompraRepo.obterTodosAgendaCompraPorFiltro(filtroAgendaCompraInput);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                agendaCompra = agendaCompraRepoWinthor.obterTodosAgendaCompraPorFiltro(filtroAgendaCompraInput);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                agendaCompra = agendaCompraRepoGCF.obterTodosAgendaCompraPorFiltro(filtroAgendaCompraInput);
                break;
            }
        }

        return agendaCompra;
    }
}


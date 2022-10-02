package com.mines.AgendaCompra.GraphQL;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.AgendaCompra.Model.AgendaCompra;
import com.mines.AgendaCompra.Model.AgendaCompraInput;
import com.mines.AgendaCompra.Model.FiltroAgendaCompraInput;
import com.mines.AgendaCompra.Repository.AgendaCompraRepository;
import com.mines.AgendaCompra.Service.AgendaCompraService;
import com.mines.ClassificacaoMercadologica.Model.Categoria;
import com.mines.ClassificacaoMercadologica.Model.Departamento;
import com.mines.ClassificacaoMercadologica.Model.Secao;
import com.mines.ClassificacaoMercadologica.Model.SubCategoria;
import com.mines.Comprador.Model.Comprador;
import com.mines.Empresa.Model.Empresa;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgendaCompraGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private AgendaCompraRepository agendaCompraRepo;

    @Autowired
    private AgendaCompraService agendaCompraServ;

    public AgendaCompra agendaCompra(Integer id) {

        AgendaCompra agendaCompra = null;
        try {
            agendaCompra = agendaCompraServ.obterPorIdAgendaCompra(id);
        } catch (Exception e) {
            return agendaCompra;
        }
        return agendaCompra;
    }

    public List<AgendaCompra> agendasCompra(Integer entidade) {

        List<AgendaCompra> lista = agendaCompraServ.obterTodosAgendaCompra(entidade);
        return lista;
    }

    public List<AgendaCompra> agendasCompraPorFiltro(FiltroAgendaCompraInput filtroAgendaCompraInput) {

        List<AgendaCompra> lista = new ArrayList<AgendaCompra>();
        try {
            lista = agendaCompraRepo.obterTodosAgendaCompraPorFiltro(filtroAgendaCompraInput);
        } catch (ParseException e) {
            return lista;
        }
        return lista;
    }

    public Boolean deletarAgendaCompra(Integer id) {

        Boolean ret = false;

        try {
            AgendaCompra agendaCompra = agendaCompraRepo.obterPorIdAgendaCompra(id);
            agendaCompraRepo.deletarAgendaCompraFornecedor(agendaCompra);
            ret = agendaCompraRepo.deletarAgendaCompra(agendaCompra);
        } catch (Exception e) {
            return ret;
        }
        return ret;
    }

    public AgendaCompra salvarAgendaCompra(AgendaCompraInput agendaCompraInput) throws SQLException {

        AgendaCompra agendaCompra = new AgendaCompra();

        agendaCompra.setTitulo(agendaCompraInput.getTitulo());
        agendaCompra.setFrequencia(agendaCompraInput.getFrequencia());
        agendaCompra.setInicioAgendaCompra(agendaCompraInput.getInicioAgendaCompra());
        agendaCompra.setTerminoAgendaCompra(agendaCompraInput.getTerminoAgendaCompra());
        agendaCompra.setDomingo(agendaCompraInput.getDomingo());
        agendaCompra.setSegunda(agendaCompraInput.getSegunda());
        agendaCompra.setTerca(agendaCompraInput.getTerca());
        agendaCompra.setQuarta(agendaCompraInput.getQuarta());
        agendaCompra.setQuinta(agendaCompraInput.getQuinta());
        agendaCompra.setSexta(agendaCompraInput.getSexta());
        agendaCompra.setSabado(agendaCompraInput.getSabado());
        agendaCompra.setAtivo(agendaCompraInput.getAtivo());

        Comprador comprador = new Comprador();
        comprador.setId(agendaCompraInput.getComprador());
        agendaCompra.setComprador(comprador);

        Departamento departamento = new Departamento();
        if (agendaCompraInput.getDepartamento() != null) {
            departamento.setId(agendaCompraInput.getDepartamento());
            agendaCompra.setDepartamento(departamento);
        } else {
            departamento.setId(0);
            agendaCompra.setDepartamento(departamento);
        }

        Secao secao = new Secao();
        if (agendaCompraInput.getSecao() != null) {
            secao.setId(agendaCompraInput.getSecao());
            agendaCompra.setSecao(secao);
        } else {
            secao.setId(0);
            agendaCompra.setSecao(secao);
        }

        Categoria categoria = new Categoria();
        if (agendaCompraInput.getCategoria() != null) {
            categoria.setId(agendaCompraInput.getCategoria());
            agendaCompra.setCategoria(categoria);
        } else {
            categoria.setId(0);
            agendaCompra.setCategoria(categoria);
        }

        SubCategoria subCategoria = new SubCategoria();
        if (agendaCompraInput.getSubCategoria() != null) {
            subCategoria.setId(agendaCompraInput.getSubCategoria());
            agendaCompra.setSubCategoria(subCategoria);
        } else {
            subCategoria.setId(0);
            agendaCompra.setSubCategoria(subCategoria);
        }

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(agendaCompraInput.getEntidadeGestora());
        agendaCompra.setEntidadeGestora(entidadeGestora);

        try {
            if (agendaCompraInput.getId() == 0) {
                agendaCompra = agendaCompraRepo.salvarAgendaCompra(agendaCompra);

                if (agendaCompraInput.getFornecedores() != null) {
                    agendaCompraRepo.salvarAgendaCompraFornecedor(
                            agendaCompra.getId(), agendaCompraInput.getFornecedores());
                }
            } else {
                agendaCompra.setId(agendaCompraInput.getId());
                agendaCompra = agendaCompraRepo.atualizarAgendaCompra(agendaCompra);
            }
        } catch (Exception e) {
            deletarAgendaCompra(agendaCompra.getId());
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return agendaCompra;

    }

}
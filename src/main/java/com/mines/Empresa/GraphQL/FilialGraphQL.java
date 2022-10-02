package com.mines.Empresa.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Model.Filial;
import com.mines.Empresa.Model.FilialInput;
import com.mines.Empresa.Model.GrupoCompra;
import com.mines.Empresa.Model.GrupoFilial;
import com.mines.Empresa.Model.TipoFilial;
import com.mines.Empresa.Repository.FilialRepository;
import com.mines.Empresa.Service.FilialService;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.util.exceptions.DomainException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilialGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private FilialRepository filialRepo;

    @Autowired
    private FilialService filialServ;

    public Filial filial(Integer id) {

        Filial filial = null;
        try {
            filial = filialServ.obterPorIdFilial(id);
        } catch (Exception e) {
            return filial;
        }
        return filial;
    }

    public List<Filial> filiais(Integer entidade) {

        List<Filial> lista = filialServ.obterTodosFiliais(entidade);
        return lista;

    }

    public List<Filial> filiaisPorGrupoCompraInterno(Integer entidade, Integer grupoCompraInterno) {

        List<Filial> lista = filialServ.obterTodosFiliaisPorGrupoCompraInterno(entidade, grupoCompraInterno);
        return lista;

    }

    public Boolean deletarFilial(Integer id) {

        Boolean ret = false;

        try {
            Filial filial = filialRepo.obterPorIdFilial(id);
            ret = filialRepo.deletarFilial(filial);
        } catch (Exception e) {
            return ret;
        }

        return ret;

    }

    public Filial salvarFilial(FilialInput filialInput) throws SQLException {

        Filial filial = new Filial();

        PessoaFisica responsavel = new PessoaFisica();
        responsavel.setId(filialInput.getResponsavel());
        filial.setResponsavel(responsavel);

        PessoaFisica supervisor = new PessoaFisica();
        supervisor.setId(filialInput.getSupervisor());
        filial.setSupervisor(supervisor);

        filial.setAtivo(filialInput.getAtivo());
        filial.setUf(filialInput.getUf());

        GrupoFilial grupoFilial = new GrupoFilial();
        grupoFilial.setId(filialInput.getGrupoFilial());
        filial.setGrupoFilial(grupoFilial);

        GrupoCompra grupoCompraExterno = new GrupoCompra();
        grupoCompraExterno.setId(filialInput.getGrupoCompraExterno());
        filial.setGrupoCompraExterno(grupoCompraExterno);

        GrupoCompra grupoCompraInterno = new GrupoCompra();
        grupoCompraInterno.setId(filialInput.getGrupoCompraInterno());
        filial.setGrupoCompraInterno(grupoCompraInterno);

        TipoFilial tipoFilial = new TipoFilial();
        tipoFilial.setId(filialInput.getTipoFilial());
        filial.setTipoFilial(tipoFilial);

        filial.setObservacao(filialInput.getObservacao());

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(filialInput.getEntidadeGestora());
        filial.setEntidadeGestora(entidadeGestora);

        try {
            if (filialInput.getId() == 0) {
                filial = filialRepo.salvarFilial(filial);
            } else {
                filial.setId(filialInput.getId());
                filial = filialRepo.atualizarFilial(filial);
            }
        } catch (Exception e) {
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return filial;

    }

}
package com.mines.EntidadeJuridica.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.AtividadeComercial;
import com.mines.EntidadeJuridica.Model.AtividadeComercialInput;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Model.RamoAtividade;
import com.mines.EntidadeJuridica.Repository.AtividadeComercialRepository;
import com.mines.EntidadeJuridica.Service.AtividadeComercialService;
import com.mines.util.exceptions.DomainException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtividadeComercialGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private AtividadeComercialRepository atividadeComercialRepo;

    @Autowired
    private AtividadeComercialService atividadeComercialServ;

    public AtividadeComercial atividadeComercial(Integer id) {

        AtividadeComercial atividadeComercial = null;
        try {
            atividadeComercial = atividadeComercialServ.obterPorIdAtividadeComercial(id);
        } catch (Exception e) {
            return atividadeComercial;
        }
        return atividadeComercial;
    }

    public List<AtividadeComercial> atividadesComerciais(Integer entidade) {

        List<AtividadeComercial> lista = atividadeComercialServ.obterTodosAtividadesComercial(entidade);
        return lista;

    }

    public List<AtividadeComercial> atividadesComerciaisPorRamoAtividade(Integer entidade, Integer ramoAtividade) {

        List<AtividadeComercial> lista = atividadeComercialServ.obterTodosAtividadesComercialPorRamoAtividade(entidade,
                ramoAtividade);
        return lista;

    }

    public Boolean deletarAtividadeComercial(Integer id) {

        Boolean ret = false;

        try {
            AtividadeComercial atividadeComercial = atividadeComercialRepo.obterPorIdAtividadeComercial(id);
            ret = atividadeComercialRepo.deletarAtividadeComercial(atividadeComercial);
        } catch (Exception e) {
            return ret;
        }

        return ret;

    }

    public AtividadeComercial salvarAtividadeComercial(AtividadeComercialInput atividadeComercialInput)
            throws SQLException {

        AtividadeComercial atividadeComercial = new AtividadeComercial();

        atividadeComercial.setDescricao(atividadeComercialInput.getDescricao());

        RamoAtividade ramoAtividade = new RamoAtividade();
        ramoAtividade.setId(atividadeComercialInput.getRamoAtividade());
        atividadeComercial.setRamoAtividade(ramoAtividade);

        PessoaFisica usuario = new PessoaFisica();
        usuario.setId(atividadeComercialInput.getUsuario());
        atividadeComercial.setUsuario(usuario);

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(atividadeComercialInput.getEntidadeGestora());
        atividadeComercial.setEntidadeGestora(entidadeGestora);

        try {
            if (atividadeComercialInput.getId() == 0) {
                atividadeComercial = atividadeComercialRepo.salvarAtividadeComercial(atividadeComercial);
            } else {
                atividadeComercial.setId(atividadeComercialInput.getId());
                atividadeComercial = atividadeComercialRepo.atualizarAtividadeComercial(atividadeComercial);
            }
        } catch (Exception e) {
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return atividadeComercial;

    }

}

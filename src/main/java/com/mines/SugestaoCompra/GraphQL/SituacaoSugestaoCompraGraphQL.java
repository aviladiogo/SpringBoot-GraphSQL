package com.mines.SugestaoCompra.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.SugestaoCompra.Model.SituacaoSugestaoCompra;
import com.mines.SugestaoCompra.Model.SituacaoSugestaoCompraInput;
import com.mines.SugestaoCompra.Repository.SituacaoSugestaoCompraRepository;
import com.mines.SugestaoCompra.Service.SituacaoSugestaoCompraService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SituacaoSugestaoCompraGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private SituacaoSugestaoCompraRepository situacaoSugestaoCompraRepo;

    @Autowired
    private SituacaoSugestaoCompraService situacaoSugestaoCompraServ;

    public SituacaoSugestaoCompra situacaoSugestaoCompra(Integer id) {

        SituacaoSugestaoCompra situacaoSugestaoCompra = null;
        try {
            situacaoSugestaoCompra = situacaoSugestaoCompraServ.obterPorIdSituacaoSugestaoCompra(id);
        } catch (Exception e) {
            return situacaoSugestaoCompra;
        }
        return situacaoSugestaoCompra;
    }

    public List<SituacaoSugestaoCompra> situacoesSugestaoCompras(Integer entidade) {

        List<SituacaoSugestaoCompra> lista = situacaoSugestaoCompraServ.obterTodosSituacoesSugestaoCompras(entidade);
        return lista;
    }

    public Boolean deletarSituacaoSugestaoCompra(Integer id) {

        Boolean ret = false;

        try {
            SituacaoSugestaoCompra situacaoSugestaoCompra = situacaoSugestaoCompraRepo
                    .obterPorIdSituacaoSugestaoCompra(id);
            ret = situacaoSugestaoCompraRepo.deletarSituacaoSugestaoCompra(situacaoSugestaoCompra);
        } catch (Exception e) {
            return ret;
        }
        return ret;
    }

    public SituacaoSugestaoCompra salvarSituacaoSugestaoCompra(SituacaoSugestaoCompraInput situacaoSugestaoCompraInput)
            throws SQLException {

        SituacaoSugestaoCompra situacaoSugestaoCompra = new SituacaoSugestaoCompra();

        situacaoSugestaoCompra.setDescricao(situacaoSugestaoCompraInput.getDescricao());
        situacaoSugestaoCompra.setPermiteEditar(situacaoSugestaoCompraInput.getPermiteEditar());

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(situacaoSugestaoCompraInput.getEntidadeGestora());
        situacaoSugestaoCompra.setEntidadeGestora(entidadeGestora);

        try {
            if (situacaoSugestaoCompraInput.getId() == 0) {
                situacaoSugestaoCompra = situacaoSugestaoCompraRepo
                        .salvarSituacaoSugestaoCompra(situacaoSugestaoCompra);
            } else {
                situacaoSugestaoCompra.setId(situacaoSugestaoCompraInput.getId());
                situacaoSugestaoCompra = situacaoSugestaoCompraRepo
                        .atualizarSituacaoSugestaoCompra(situacaoSugestaoCompra);
            }
        } catch (Exception e) {
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return situacaoSugestaoCompra;

    }

}

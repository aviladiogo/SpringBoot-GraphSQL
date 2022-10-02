package com.mines.CurvaABCZ.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.CurvaABCZ.Model.CurvaMargemContribuicao;
import com.mines.CurvaABCZ.Model.CurvaMargemContribuicaoInput;
import com.mines.CurvaABCZ.Repository.CurvaMargemContribuicaoRepository;
import com.mines.CurvaABCZ.Service.CurvaMargemContribuicaoService;
import com.mines.Empresa.Model.Empresa;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurvaMargemContribuicaoGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private CurvaMargemContribuicaoRepository curvaMargemContribuicaoRepo;

    @Autowired
    private CurvaMargemContribuicaoService curvaMargemContribuicaoServ;

    public CurvaMargemContribuicao curvaMargemContribuicao(Integer id) {

        CurvaMargemContribuicao curvaMargemContribuicao = null;
        try {
            curvaMargemContribuicao = curvaMargemContribuicaoServ.obterPorIdCurvaMargemContribuicao(id);
        } catch (Exception e) {
            return curvaMargemContribuicao;
        }
        return curvaMargemContribuicao;
    }

    public List<CurvaMargemContribuicao> curvasMargemContribuicao(Integer entidade) {

        List<CurvaMargemContribuicao> lista = curvaMargemContribuicaoServ.obterTodosCurvasMargemContribuicao(entidade);
        return lista;

    }

    public Boolean deletarCurvaMargemContribuicao(Integer id) {

        Boolean ret = false;

        try {
            CurvaMargemContribuicao curvaMargemContribuicao = curvaMargemContribuicaoRepo
                    .obterPorIdCurvaMargemContribuicao(id);
            ret = curvaMargemContribuicaoRepo.deletarCurvaMargemContribuicao(curvaMargemContribuicao);
        } catch (Exception e) {
            return ret;
        }

        return ret;

    }

    public CurvaMargemContribuicao salvarCurvaMargemContribuicao(
            CurvaMargemContribuicaoInput curvaMargemContribuicaoInput) throws SQLException {

        CurvaMargemContribuicao curvaMargemContribuicao = new CurvaMargemContribuicao();

        curvaMargemContribuicao.setMargem(curvaMargemContribuicaoInput.getMargem());
        curvaMargemContribuicao.setFrequencia(curvaMargemContribuicaoInput.getFrequencia());

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(curvaMargemContribuicaoInput.getEntidadeGestora());
        curvaMargemContribuicao.setEntidadeGestora(entidadeGestora);

        try {
            if (curvaMargemContribuicaoInput.getId() == 0) {
                curvaMargemContribuicao = curvaMargemContribuicaoRepo
                        .salvarCurvaMargemContribuicao(curvaMargemContribuicao);
            } else {
                curvaMargemContribuicao.setId(curvaMargemContribuicaoInput.getId());
                curvaMargemContribuicao = curvaMargemContribuicaoRepo
                        .atualizarCurvaMargemContribuicao(curvaMargemContribuicao);
            }
        } catch (Exception e) {
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return curvaMargemContribuicao;

    }

}

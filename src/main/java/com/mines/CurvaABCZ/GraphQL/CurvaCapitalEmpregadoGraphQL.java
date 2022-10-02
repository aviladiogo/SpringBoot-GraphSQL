package com.mines.CurvaABCZ.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.CurvaABCZ.Model.CurvaCapitalEmpregado;
import com.mines.CurvaABCZ.Model.CurvaCapitalEmpregadoInput;
import com.mines.CurvaABCZ.Repository.CurvaCapitalEmpregadoRepository;
import com.mines.CurvaABCZ.Service.CurvaCapitalEmpregadoService;
import com.mines.Empresa.Model.Empresa;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurvaCapitalEmpregadoGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private CurvaCapitalEmpregadoRepository curvaCapitalEmpregadoRepo;

    @Autowired
    private CurvaCapitalEmpregadoService curvaCapitalEmpregadoServ;

    public CurvaCapitalEmpregado curvaCapitalEmpregado(Integer id) {

        CurvaCapitalEmpregado curvaCapitalEmpregado = null;
        try {
            curvaCapitalEmpregado = curvaCapitalEmpregadoServ.obterPorIdCurvaCapitalEmpregado(id);
        } catch (Exception e) {
            return curvaCapitalEmpregado;
        }
        return curvaCapitalEmpregado;
    }

    public List<CurvaCapitalEmpregado> curvasCapitalEmpregado(Integer entidade) {

        List<CurvaCapitalEmpregado> lista = curvaCapitalEmpregadoServ.obterTodosCurvasCapitalEmpregado(entidade);
        return lista;

    }

    public Boolean deletarCurvaCapitalEmpregado(Integer id) {

        Boolean ret = false;

        try {
            CurvaCapitalEmpregado curvaCapitalEmpregado = curvaCapitalEmpregadoRepo.obterPorIdCurvaCapitalEmpregado(id);
            ret = curvaCapitalEmpregadoRepo.deletarCurvaCapitalEmpregado(curvaCapitalEmpregado);
        } catch (Exception e) {
            return ret;
        }

        return ret;

    }

    public CurvaCapitalEmpregado salvarCurvaCapitalEmpregado(CurvaCapitalEmpregadoInput curvaCapitalEmpregadoInput)
            throws SQLException {

        CurvaCapitalEmpregado curvaCapitalEmpregado = new CurvaCapitalEmpregado();

        curvaCapitalEmpregado.setValorCapital(curvaCapitalEmpregadoInput.getValorCapital());
        curvaCapitalEmpregado.setFrequencia(curvaCapitalEmpregadoInput.getFrequencia());

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(curvaCapitalEmpregadoInput.getEntidadeGestora());
        curvaCapitalEmpregado.setEntidadeGestora(entidadeGestora);

        try {
            if (curvaCapitalEmpregadoInput.getId() == 0) {
                curvaCapitalEmpregado = curvaCapitalEmpregadoRepo.salvarCurvaCapitalEmpregado(curvaCapitalEmpregado);
            } else {
                curvaCapitalEmpregado.setId(curvaCapitalEmpregadoInput.getId());
                curvaCapitalEmpregado = curvaCapitalEmpregadoRepo.atualizarCurvaCapitalEmpregado(curvaCapitalEmpregado);
            }
        } catch (Exception e) {
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return curvaCapitalEmpregado;

    }

}

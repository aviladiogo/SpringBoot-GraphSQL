package com.mines.SugestaoCompra.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.CurvaABCZ.Model.CurvaAbcz;
import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.SugestaoCompra.Model.FiltroSugestaoCompraInput;
import com.mines.SugestaoCompra.Model.SituacaoSugestaoCompra;
import com.mines.SugestaoCompra.Model.SugestaoCompra;
import com.mines.SugestaoCompra.Model.SugestaoCompraInput;
import com.mines.SugestaoCompra.Repository.SugestaoCompraRepository;
import com.mines.SugestaoCompra.Service.SugestaoCompraService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SugestaoCompraGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private SugestaoCompraRepository sugestaoCompraRepo;

    @Autowired
    private SugestaoCompraService sugestaoCompraServ;

    public SugestaoCompra sugestaoCompra(Integer id) {

        SugestaoCompra sugestaoCompra = null;
        try {
            sugestaoCompra = sugestaoCompraServ.obterPorIdSugestaoCompra(id);
        } catch (Exception e) {
            return sugestaoCompra;
        }
        return sugestaoCompra;
    }

    public List<SugestaoCompra> sugestoesCompras(Integer entidade) {

        List<SugestaoCompra> lista = sugestaoCompraServ.obterTodosSugestaoCompra(entidade);
        return lista;
    }

    public List<SugestaoCompra> sugestoesComprasPorFiltro(Integer entidade,
            FiltroSugestaoCompraInput filtroSugestaoCompraInput) {

        List<SugestaoCompra> lista = sugestaoCompraServ.obterTodosSugestaoCompraPorFiltro(entidade,
                filtroSugestaoCompraInput);

        return lista;
    }

    public Boolean deletarSugestaoCompra(Integer id) {

        Boolean ret = false;

        try {
            SugestaoCompra sugestaoCompra = sugestaoCompraRepo.obterPorIdSugestaoCompra(id);
            sugestaoCompraRepo.deletarSugestaoCompraFornecedor(sugestaoCompra);
            ret = sugestaoCompraRepo.deletarSugestaoCompra(sugestaoCompra);
        } catch (Exception e) {
            return ret;
        }
        return ret;
    }

    public SugestaoCompra salvarSugestaoCompra(SugestaoCompraInput sugestaoCompraInput) throws SQLException {

        SugestaoCompra sugestaoCompra = new SugestaoCompra();

        CurvaAbcz curvaCalculo = new CurvaAbcz();
        curvaCalculo.setId(sugestaoCompraInput.getCurvaCalculo());
        sugestaoCompra.setCurvaCalculo(curvaCalculo);

        sugestaoCompra.setCoberturaDiasA(sugestaoCompraInput.getCoberturaDiasA());
        sugestaoCompra.setCoberturaDiasB(sugestaoCompraInput.getCoberturaDiasB());
        sugestaoCompra.setCoberturaDiasC(sugestaoCompraInput.getCoberturaDiasC());
        sugestaoCompra.setCoberturaDiasZ(sugestaoCompraInput.getCoberturaDiasZ());
        sugestaoCompra.setPrazoEntregaCD(sugestaoCompraInput.getPrazoEntregaCD());
        sugestaoCompra.setPrazoEntregaLoja(sugestaoCompraInput.getPrazoEntregaLoja());
        sugestaoCompra.setPrazoEntregaTotal(sugestaoCompraInput.getPrazoEntregaTotal());
        sugestaoCompra.setOpcaoGiroDia(sugestaoCompraInput.getOpcaoGiroDia());
        sugestaoCompra.setCondicaoPagto(sugestaoCompraInput.getCondicaoPagto());
        sugestaoCompra.setObterEstoqueCD(sugestaoCompraInput.getObterEstoqueCD());
        sugestaoCompra.setGiroDiaMinimo(sugestaoCompraInput.getGiroDiaMinimo());
        sugestaoCompra.setCompraGrupo(sugestaoCompraInput.getCompraGrupo());

        PessoaFisica usuario = new PessoaFisica();
        usuario.setId(sugestaoCompraInput.getUsuario());
        sugestaoCompra.setUsuario(usuario);

        SituacaoSugestaoCompra situacao = new SituacaoSugestaoCompra();
        situacao.setId(sugestaoCompraInput.getSituacao());
        sugestaoCompra.setSituacao(situacao);

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(sugestaoCompraInput.getEntidadeGestora());
        sugestaoCompra.setEntidadeGestora(entidadeGestora);

        try {
            if (sugestaoCompraInput.getId() == 0) {
                sugestaoCompra = sugestaoCompraRepo.salvarSugestaoCompra(sugestaoCompra);

                if(sugestaoCompraInput.getFornecedores()!=null){
                    sugestaoCompraRepo.salvarSugestaoCompraFornecedor(
                        sugestaoCompra.getId(), sugestaoCompraInput.getFornecedores());
                }
            } else {
                sugestaoCompra.setId(sugestaoCompraInput.getId());
                sugestaoCompra = sugestaoCompraRepo.atualizarSugestaoCompra(sugestaoCompra);
            }
        } catch (Exception e) {
            deletarSugestaoCompra(sugestaoCompra.getId());
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return sugestaoCompra;

    }

}

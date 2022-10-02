package com.mines.Fornecedor.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Model.PessoaJuridica;
import com.mines.Fornecedor.Model.Fornecedor;
import com.mines.Fornecedor.Model.FornecedorInput;
import com.mines.Fornecedor.Repository.FornecedorRepository;
import com.mines.Fornecedor.Service.FornecedorService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FornecedorGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private FornecedorRepository fornecedorRepo;

    @Autowired
    private FornecedorService fornecedorServ;

    public Fornecedor fornecedor(Integer id) {

        Fornecedor fornecedor = null;
        try {
            fornecedor = fornecedorServ.obterPorIdFornecedor(id);
        } catch (Exception e) {
            return fornecedor;
        }
        return fornecedor;
    }

    public List<Fornecedor> fornecedores(Integer entidade) {

        List<Fornecedor> lista = fornecedorServ.obterTodosFornecedores(entidade);
        return lista;

    }

    public List<Fornecedor> obterFornecedoresPorProduto(Integer entidade, Integer produto) {

        List<Fornecedor> lista = fornecedorServ.obterTodosFornecedoresPorProduto(entidade, produto);
        return lista;

    }

    public List<Fornecedor> obterFornecedoresPorCriterio(Integer entidade, String criterioBusca) {

        List<Fornecedor> lista = fornecedorServ.obterFornecedoresPorCriterio(entidade, '%' + criterioBusca + '%');
        return lista;

    }

    public List<Fornecedor> obterFornecedoresPorDepartamento(Integer entidade, Integer departamento) {

        List<Fornecedor> lista = fornecedorServ.obterFornecedoresPorDepartamento(entidade, departamento);
        return lista;

    }

    public List<Fornecedor> obterFornecedoresPorSecao(Integer entidade, Integer secao) {

        List<Fornecedor> lista = fornecedorServ.obterFornecedoresPorSecao(entidade, secao);
        return lista;

    }

    public List<Fornecedor> obterFornecedoresPorCategoria(Integer entidade, Integer categoria) {

        List<Fornecedor> lista = fornecedorServ.obterFornecedoresPorCategoria(entidade, categoria);
        return lista;

    }

    public List<Fornecedor> obterFornecedoresPorSubCategoria(Integer entidade, Integer subCategoria) {

        List<Fornecedor> lista = fornecedorServ.obterFornecedoresPorSubCategoria(entidade, subCategoria);
        return lista;

    }

    public Boolean deletarFornecedor(Integer id) {

        Boolean ret = false;

        try {
            Fornecedor fornecedor = fornecedorRepo.obterPorIdFornecedor(id);
            fornecedorRepo.deletarFornecedorProduto(fornecedor);
            ret = fornecedorRepo.deletarFornecedor(fornecedor);
        } catch (Exception e) {
            return ret;
        }

        return ret;

    }

    public Fornecedor salvarFornecedor(FornecedorInput fornecedorInput) throws SQLException {

        Fornecedor fornecedor = new Fornecedor();

        fornecedor.setTipoPessoa(fornecedorInput.getTipoPessoa());

        if (fornecedorInput.getResponsavel() != null) {
            PessoaFisica responsavel = new PessoaFisica();
            responsavel.setId(fornecedorInput.getResponsavel());
            fornecedor.setResponsavel(responsavel);
        }

        fornecedor.setAtivo(fornecedorInput.getAtivo());
        fornecedor.setPrazoEntrega(fornecedorInput.getPrazoEntrega());
        fornecedor.setPedidoMinimo(fornecedorInput.getPedidoMinimo());

        PessoaFisica compradorPadrao = new PessoaFisica();
        compradorPadrao.setId(fornecedorInput.getCompradorPadrao());
        fornecedor.setCompradorPadrao(compradorPadrao);

        if (fornecedor.getTipoPessoa().equals("F")) {
            PessoaFisica pessoaFisica = new PessoaFisica();
            pessoaFisica.setId(fornecedorInput.getPessoaFisica());
            fornecedor.setPessoaFisica(pessoaFisica);
        } else if (fornecedor.getTipoPessoa().equals("J")) {
            PessoaJuridica pessoaJuridica = new PessoaJuridica();
            pessoaJuridica.setId(fornecedorInput.getPessoaJuridica());
            fornecedor.setPessoaJuridica(pessoaJuridica);
        }

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(fornecedorInput.getEntidadeGestora());
        fornecedor.setEntidadeGestora(entidadeGestora);

        try {
            if (fornecedorInput.getId() == 0) {
                fornecedor = fornecedorRepo.salvarFornecedor(fornecedor);
                if (fornecedorInput.getProdutos() != null) {
                    fornecedorRepo.salvarFornecedorProduto(fornecedor.getId(), fornecedorInput.getProdutos());
                }
            } else {
                fornecedor.setId(fornecedorInput.getId());
                fornecedor = fornecedorRepo.atualizarFornecedor(fornecedor);
            }
        } catch (Exception e) {
            deletarFornecedor(fornecedor.getId());
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return fornecedor;

    }

    public Fornecedor salvarProdutosPorFornecedor(Integer id, List<Integer> listaProdutos) {

        Fornecedor fornecedor = new Fornecedor();

        try {
            fornecedor = fornecedorRepo.obterPorIdFornecedor(id);
            fornecedorRepo.salvarFornecedorProduto(fornecedor.getId(), listaProdutos);
            fornecedor = fornecedorRepo.obterPorIdFornecedor(id);
        } catch (Exception e) {
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return fornecedor;
    }

}
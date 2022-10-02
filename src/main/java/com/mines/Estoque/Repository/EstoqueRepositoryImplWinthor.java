package com.mines.Estoque.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;
import com.mines.Estoque.Model.Almoxarifado;
import com.mines.Estoque.Model.Estoque;
import com.mines.Produto.Model.Produto;
import com.mines.Produto.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EstoqueRepositoryImplWinthor implements EstoqueRepositoryWinthor {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private AlmoxarifadoRepository almoxarifadoRepo;

    @Autowired
    private ProdutoRepository produtoRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Estoque obterPorIdEstoque(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Estoque>() {
            @Override
            public Estoque mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Estoque estoque = new Estoque();

                estoque.setId(rs.getInt("id"));
                estoque.setQuantidade(rs.getDouble("quantidade"));
                estoque.setGiroVendaDia(rs.getDouble("girovendadia"));

                Integer entidadeEstoqueId = rs.getInt("entidadeestoque");
                Empresa entidadeEstoque = empresaRepo.obterPorIdEmpresa(entidadeEstoqueId);
                estoque.setEntidadeEstoque(entidadeEstoque);

                Integer almoxarifadoId = rs.getInt("almoxarifado");
                Almoxarifado almoxarifado = almoxarifadoRepo.obterPorIdAlmoxarifado(almoxarifadoId);
                estoque.setAlmoxarifado(almoxarifado);

                Integer responsavelId = rs.getInt("responsavel");
                PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                estoque.setResponsavel(responsavel);

                Integer produtoId = rs.getInt("produto");
                Produto produto = produtoRepo.obterPorIdProduto(produtoId);
                estoque.setProduto(produto);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                estoque.setEntidadeGestora(entidadeGestora);

                return estoque;
            }
        });

    }
    
    public List<Estoque> obterTodosEstoques(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Estoque>() {
            @Override
            public Estoque mapRow(ResultSet rs, int rownumber) throws SQLException {

                Estoque estoque = new Estoque();

                estoque.setId(rs.getInt("id"));
                estoque.setQuantidade(rs.getDouble("quantidade"));
                estoque.setGiroVendaDia(rs.getDouble("girovendadia"));

                Integer entidadeEstoqueId = rs.getInt("entidadeestoque");
                Empresa entidadeEstoque = empresaRepo.obterPorIdEmpresa(entidadeEstoqueId);
                estoque.setEntidadeEstoque(entidadeEstoque);

                Integer almoxarifadoId = rs.getInt("almoxarifado");
                Almoxarifado almoxarifado = almoxarifadoRepo.obterPorIdAlmoxarifado(almoxarifadoId);
                estoque.setAlmoxarifado(almoxarifado);

                Integer responsavelId = rs.getInt("responsavel");
                PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                estoque.setResponsavel(responsavel);

                Integer produtoId = rs.getInt("produto");
                Produto produto = produtoRepo.obterPorIdProduto(produtoId);
                estoque.setProduto(produto);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                estoque.setEntidadeGestora(entidadeGestora);

                return estoque;
            }
        }, entidade);

    }
    
}

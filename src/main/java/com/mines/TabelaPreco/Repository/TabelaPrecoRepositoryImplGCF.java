package com.mines.TabelaPreco.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;
import com.mines.Fornecedor.Model.Fornecedor;
import com.mines.Fornecedor.Repository.FornecedorRepository;
import com.mines.TabelaPreco.Model.TabelaPreco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TabelaPrecoRepositoryImplGCF implements TabelaPrecoRepositoryGCF{
    
    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    private static String SELECT_ALL_FORNECEDOR_POR_TABELA_PRECO = " ";

    @Autowired
    private JdbcTemplate jbdcTemplate;

    @Autowired
    private FornecedorRepository fornecedorRepo;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    public TabelaPreco obterPorIdTabelaPreco(Integer id) {

        return jbdcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<TabelaPreco>() {
            @Override
            public TabelaPreco mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                TabelaPreco tabelaPreco = new TabelaPreco();

                tabelaPreco.setId(rs.getInt("id"));
                tabelaPreco.setAtivo(rs.getBoolean("ativo"));
                tabelaPreco.setPromocao(rs.getBoolean("promocao"));
                tabelaPreco.setTitulo(rs.getString("titulo"));
                tabelaPreco.setInicioValidade(rs.getDate("iniciovalidade"));
                tabelaPreco.setTerminoValidade(rs.getDate("terminovalidade"));
                if (obterPorIdTabelaPrecoFornecedor(tabelaPreco.getId()) != null) {
                    tabelaPreco.setFornecedores(obterPorIdTabelaPrecoFornecedor(tabelaPreco.getId()));
                }

                Integer responsavelId = rs.getInt("responsavel");
                PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                tabelaPreco.setResponsavel(responsavel);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                tabelaPreco.setEntidadeGestora(entidadeGestora);
                
                return tabelaPreco;
            }
        });

    }

    public List<TabelaPreco> obterTodosTabelasPreco(Integer entidade) {

        return jbdcTemplate.query(SELECT_ALL, new RowMapper<TabelaPreco>() {
            @Override
            public TabelaPreco mapRow(ResultSet rs, int rownumber) throws SQLException {

                TabelaPreco tabelaPreco = new TabelaPreco();

                tabelaPreco.setId(rs.getInt("id"));
                tabelaPreco.setAtivo(rs.getBoolean("ativo"));
                tabelaPreco.setPromocao(rs.getBoolean("promocao"));
                tabelaPreco.setTitulo(rs.getString("titulo"));
                tabelaPreco.setInicioValidade(rs.getDate("iniciovalidade"));
                tabelaPreco.setTerminoValidade(rs.getDate("terminovalidade"));
                if (obterPorIdTabelaPrecoFornecedor(tabelaPreco.getId()) != null) {
                    tabelaPreco.setFornecedores(obterPorIdTabelaPrecoFornecedor(tabelaPreco.getId()));
                }

                Integer responsavelId = rs.getInt("responsavel");
                PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                tabelaPreco.setResponsavel(responsavel);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                tabelaPreco.setEntidadeGestora(entidadeGestora);
                
                return tabelaPreco;
            }
        }, entidade);
    }

    public List<Fornecedor> obterPorIdTabelaPrecoFornecedor(Integer tabelapreco) {

        return jbdcTemplate.query(SELECT_ALL_FORNECEDOR_POR_TABELA_PRECO, new RowMapper<Fornecedor>() {
            @Override
            public Fornecedor mapRow(ResultSet rs, int rownumber) throws SQLException {

                Integer fornecedorId = rs.getInt("fornecedor");
                Fornecedor fornecedor = fornecedorRepo.obterPorIdFornecedor(fornecedorId);

                return fornecedor;
            }
        }, tabelapreco);

    }

}

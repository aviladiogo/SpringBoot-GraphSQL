package com.mines.Fornecedor.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Model.Filial;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.Empresa.Repository.FilialRepository;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;
import com.mines.Fornecedor.Model.Fornecedor;
import com.mines.Fornecedor.Model.FornecedorFilial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class FornecedorFilialRepositoryImplWinthor implements FornecedorFilialRepositoryWinthor{

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";

    @Autowired
    private JdbcTemplate jbdcTemplate;

    @Autowired
    private FornecedorRepository fornecedorRepo;

    @Autowired
    private FilialRepository filialRepo;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    public FornecedorFilial obterPorIdFornecedorFilial(Integer fornecedor, Integer filial) {

        return jbdcTemplate.queryForObject(SELECT_ONE, new Object[] { fornecedor, filial },
                new RowMapper<FornecedorFilial>() {
                    @Override
                    public FornecedorFilial mapRow(ResultSet rs, int rownumber) throws SQLException {

                        FornecedorFilial fornecedorFilial = new FornecedorFilial();

                        fornecedorFilial.setPrazoEntrega(rs.getInt("prazoentrega"));
                        fornecedorFilial.setPercentualIcms(rs.getDouble("percentualicms"));
                        fornecedorFilial.setPercentualCofins(rs.getDouble("percentualcofins"));

                        Integer fornecedorId = rs.getInt("fornecedor");
                        Fornecedor fornecedor = fornecedorRepo.obterPorIdFornecedor(fornecedorId);
                        fornecedorFilial.setFornecedor(fornecedor);

                        Integer filialId = rs.getInt("filial");
                        Filial filial = filialRepo.obterPorIdFilial(filialId);
                        fornecedorFilial.setFilial(filial);

                        Integer compradorPadraoId = rs.getInt("compradorpadrao");
                        PessoaFisica compradorPadrao = pessoaFisicaRepo.obterPorIdPessoaFisica(compradorPadraoId);
                        fornecedorFilial.setCompradorPadrao(compradorPadrao);

                        Integer entidadeGestoraId = rs.getInt("entidadegestora");
                        Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                        fornecedorFilial.setEntidadeGestora(entidadeGestora);

                        return fornecedorFilial;
                    }
                });

    }

    public List<FornecedorFilial> obterTodosFornecedoresFilial(Integer entidade) {

        return jbdcTemplate.query(SELECT_ALL, new RowMapper<FornecedorFilial>() {
            @Override
            public FornecedorFilial mapRow(ResultSet rs, int rownumber) throws SQLException {

                FornecedorFilial fornecedorFilial = new FornecedorFilial();

                fornecedorFilial.setPrazoEntrega(rs.getInt("prazoentrega"));
                fornecedorFilial.setPercentualIcms(rs.getDouble("percentualicms"));
                fornecedorFilial.setPercentualCofins(rs.getDouble("percentualcofins"));

                Integer fornecedorId = rs.getInt("fornecedor");
                Fornecedor fornecedor = fornecedorRepo.obterPorIdFornecedor(fornecedorId);
                fornecedorFilial.setFornecedor(fornecedor);

                Integer filialId = rs.getInt("filial");
                Filial filial = filialRepo.obterPorIdFilial(filialId);
                fornecedorFilial.setFilial(filial);

                Integer compradorPadraoId = rs.getInt("compradorpadrao");
                PessoaFisica compradorPadrao = pessoaFisicaRepo.obterPorIdPessoaFisica(compradorPadraoId);
                fornecedorFilial.setCompradorPadrao(compradorPadrao);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                fornecedorFilial.setEntidadeGestora(entidadeGestora);

                return fornecedorFilial;
            }
        }, entidade);
    }
    
}

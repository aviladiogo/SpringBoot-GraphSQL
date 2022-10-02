package com.mines.Fornecedor.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class FornecedorFilialRepositoryImpl implements FornecedorFilialRepository {

    private static String SELECT_ALL = " select * from dx_fornecedorfilial where entidadegestora = ?";
    private static String SELECT_ONE = " select * from dx_fornecedorfilial where fornecedor = ? and filial = ? ";
    private static String INSERT = " insert into dx_fornecedorfilial (fornecedor, filial, prazoentrega, "
            + " percentualicms, percentualcofins, compradorpadrao, entidadegestora) "
            + " values (?,?,?,?,?,?,?) ";
    private static String UPDATE = " update dx_fornecedorfilial set prazoentrega = ?, percentualicms = ?, "
            + " percentualcofins = ? where fornecedor = ? and filial = ? ";
    private static String DELETE = " delete from dx_fornecedorfilial where fornecedor = ? and filial = ?";

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

    public FornecedorFilial atualizarFornecedorFilial(FornecedorFilial fornecedorFilial) {

        jbdcTemplate.update(UPDATE, new Object[] { fornecedorFilial.getPrazoEntrega(),
                fornecedorFilial.getPercentualIcms(), fornecedorFilial.getPercentualCofins(),
                fornecedorFilial.getFornecedor().getId(), fornecedorFilial.getFilial().getId() });

        return fornecedorFilial;
    }

    public Boolean deletarFornecedorFilial(FornecedorFilial fornecedorFilial) {

        Boolean ret = false;

        Integer rows = jbdcTemplate.update(DELETE, new Object[] { fornecedorFilial.getFornecedor().getId(),
                fornecedorFilial.getFilial().getId() });

        if (rows > 0) {
            ret = true;
        }

        return ret;

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

    public FornecedorFilial salvarFornecedorFilial(FornecedorFilial fornecedorFilial) {

        KeyHolder holder = new GeneratedKeyHolder();

        jbdcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, fornecedorFilial.getFornecedor().getId());
                ps.setInt(2, fornecedorFilial.getFilial().getId());
                ps.setInt(3, fornecedorFilial.getPrazoEntrega());
                ps.setDouble(4, fornecedorFilial.getPercentualIcms());
                ps.setDouble(5, fornecedorFilial.getPercentualCofins());
                ps.setInt(6, fornecedorFilial.getCompradorPadrao().getId());
                ps.setInt(7, fornecedorFilial.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        return fornecedorFilial;
    }
}

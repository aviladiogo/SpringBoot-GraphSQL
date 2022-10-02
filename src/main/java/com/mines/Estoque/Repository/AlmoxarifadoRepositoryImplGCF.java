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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AlmoxarifadoRepositoryImplGCF implements AlmoxarifadoRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Almoxarifado obterPorIdAlmoxarifado(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Almoxarifado>() {
            @Override
            public Almoxarifado mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Almoxarifado almoxarifado = new Almoxarifado();

                almoxarifado.setId(rs.getInt("id"));
                almoxarifado.setDescricao(rs.getString("descricao"));
                almoxarifado.setAtivo(rs.getBoolean("ativo"));

                Integer entidadeEstoqueId = rs.getInt("entidadeestoque");
                Empresa entidadeEstoque = empresaRepo.obterPorIdEmpresa(entidadeEstoqueId);
                almoxarifado.setEntidadeEstoque(entidadeEstoque);

                Integer responsavelId = rs.getInt("responsavel");
                PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                almoxarifado.setResponsavel(responsavel);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                almoxarifado.setEntidadeGestora(entidadeGestora);

                return almoxarifado;
            }
        });

    }
    
    public List<Almoxarifado> obterTodosAlmoxarifados(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Almoxarifado>() {
            @Override
            public Almoxarifado mapRow(ResultSet rs, int rownumber) throws SQLException {

                Almoxarifado almoxarifado = new Almoxarifado();

                almoxarifado.setId(rs.getInt("id"));
                almoxarifado.setDescricao(rs.getString("descricao"));
                almoxarifado.setAtivo(rs.getBoolean("ativo"));

                Integer entidadeEstoqueId = rs.getInt("entidadeestoque");
                Empresa entidadeEstoque = empresaRepo.obterPorIdEmpresa(entidadeEstoqueId);
                almoxarifado.setEntidadeEstoque(entidadeEstoque);

                Integer responsavelId = rs.getInt("responsavel");
                PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                almoxarifado.setResponsavel(responsavel);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                almoxarifado.setEntidadeGestora(entidadeGestora);

                return almoxarifado;
            }
        }, entidade);

    }
    
}
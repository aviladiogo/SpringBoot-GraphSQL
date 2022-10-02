package com.mines.Empresa.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Model.Filial;
import com.mines.Empresa.Model.GrupoCompra;
import com.mines.Empresa.Model.GrupoFilial;
import com.mines.Empresa.Model.TipoFilial;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class FilialRepositoryImplWinthor implements FilialRepositoryWinthor {

    private static String SELECT_ALL = " ";
    private static String SELECT_ALL_POR_GRUPO_COMPRA_INTERNO = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private GrupoFilialRepository grupoFilialRepo;

    @Autowired
    private GrupoCompraRepository grupoCompraRepo;

    @Autowired
    private TipoFilialRepository tipoFilialRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Filial obterPorIdFilial(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Filial>() {
            @Override
            public Filial mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Filial filial = new Filial();

                filial.setId(rs.getInt("id"));
                filial.setAtivo(rs.getBoolean("ativo"));
                filial.setObservacao(rs.getString("observacao"));
                filial.setDataAbertura(rs.getDate("dataabertura"));
                filial.setUf(rs.getString("uf"));

                Integer responsavelId = rs.getInt("responsavel");
                PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                filial.setResponsavel(responsavel);

                Integer supervisorId = rs.getInt("supervisor");
                PessoaFisica supervisor = pessoaFisicaRepo.obterPorIdPessoaFisica(supervisorId);
                filial.setSupervisor(supervisor);

                Integer grupoFilialId = rs.getInt("Grupofilial");
                GrupoFilial GrupoFilial = grupoFilialRepo.obterPorIdGrupoFilial(grupoFilialId);
                filial.setGrupoFilial(GrupoFilial);

                Integer grupoCompraInternoId = rs.getInt("grupocomprainterno");
                GrupoCompra grupoCompraInterno = grupoCompraRepo.obterPorIdGrupoCompra(grupoCompraInternoId);
                filial.setGrupoCompraInterno(grupoCompraInterno);

                Integer grupoCompraExternoId = rs.getInt("grupocompraexterno");
                GrupoCompra grupoCompraExterno = grupoCompraRepo.obterPorIdGrupoCompra(grupoCompraExternoId);
                filial.setGrupoCompraExterno(grupoCompraExterno);

                Integer tipoFilialId = rs.getInt("tipofilial");
                TipoFilial tipoFilial = tipoFilialRepo.obterPorIdTipoFilial(tipoFilialId);
                filial.setTipoFilial(tipoFilial);

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                filial.setEntidadeGestora(entidadeGestora);

                return filial;
            }
        });

    }
    
    public List<Filial> obterTodosFiliais(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Filial>() {
            @Override
            public Filial mapRow(ResultSet rs, int rownumber) throws SQLException {

                Filial filial = new Filial();

                filial.setId(rs.getInt("id"));
                filial.setAtivo(rs.getBoolean("ativo"));
                filial.setObservacao(rs.getString("observacao"));
                filial.setDataAbertura(rs.getDate("dataabertura"));
                filial.setUf(rs.getString("uf"));

                Integer responsavelId = rs.getInt("responsavel");
                PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                filial.setResponsavel(responsavel);

                Integer supervisorId = rs.getInt("supervisor");
                PessoaFisica supervisor = pessoaFisicaRepo.obterPorIdPessoaFisica(supervisorId);
                filial.setSupervisor(supervisor);

                Integer grupoFilialId = rs.getInt("grupofilial");
                GrupoFilial GrupoFilial = grupoFilialRepo.obterPorIdGrupoFilial(grupoFilialId);
                filial.setGrupoFilial(GrupoFilial);

                Integer grupoCompraInternoId = rs.getInt("grupocomprainterno");
                GrupoCompra grupoCompraInterno = grupoCompraRepo.obterPorIdGrupoCompra(grupoCompraInternoId);
                filial.setGrupoCompraInterno(grupoCompraInterno);

                Integer grupoCompraExternoId = rs.getInt("grupocompraexterno");
                GrupoCompra grupoCompraExterno = grupoCompraRepo.obterPorIdGrupoCompra(grupoCompraExternoId);
                filial.setGrupoCompraExterno(grupoCompraExterno);

                Integer tipoFilialId = rs.getInt("tipofilial");
                TipoFilial tipoFilial = tipoFilialRepo.obterPorIdTipoFilial(tipoFilialId);
                filial.setTipoFilial(tipoFilial);

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                filial.setEntidadeGestora(entidadeGestora);

                return filial;
            }
        }, entidade);

    }

    public List<Filial> obterTodosFiliaisPorGrupoCompraInterno(Integer entidade, Integer grupoCompraInterno) {

        return jdbcTemplate.query(SELECT_ALL_POR_GRUPO_COMPRA_INTERNO, new RowMapper<Filial>() {
            @Override
            public Filial mapRow(ResultSet rs, int rownumber) throws SQLException {

                Filial filial = new Filial();

                filial.setId(rs.getInt("id"));
                filial.setAtivo(rs.getBoolean("ativo"));
                filial.setObservacao(rs.getString("observacao"));
                filial.setDataAbertura(rs.getDate("dataabertura"));
                filial.setUf(rs.getString("uf"));

                Integer responsavelId = rs.getInt("responsavel");
                PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                filial.setResponsavel(responsavel);

                Integer supervisorId = rs.getInt("supervisor");
                PessoaFisica supervisor = pessoaFisicaRepo.obterPorIdPessoaFisica(supervisorId);
                filial.setSupervisor(supervisor);

                Integer grupoFilialId = rs.getInt("grupofilial");
                GrupoFilial GrupoFilial = grupoFilialRepo.obterPorIdGrupoFilial(grupoFilialId);
                filial.setGrupoFilial(GrupoFilial);

                Integer grupoCompraInternoId = rs.getInt("grupocomprainterno");
                GrupoCompra grupoCompraInterno = grupoCompraRepo.obterPorIdGrupoCompra(grupoCompraInternoId);
                filial.setGrupoCompraInterno(grupoCompraInterno);

                Integer grupoCompraExternoId = rs.getInt("grupocompraexterno");
                GrupoCompra grupoCompraExterno = grupoCompraRepo.obterPorIdGrupoCompra(grupoCompraExternoId);
                filial.setGrupoCompraExterno(grupoCompraExterno);

                Integer tipoFilialId = rs.getInt("tipofilial");
                TipoFilial tipoFilial = tipoFilialRepo.obterPorIdTipoFilial(tipoFilialId);
                filial.setTipoFilial(tipoFilial);

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                filial.setEntidadeGestora(entidadeGestora);

                return filial;
            }
        }, entidade, grupoCompraInterno);

    }
    
}

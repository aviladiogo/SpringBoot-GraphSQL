package com.mines.EntidadeJuridica.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Model.RamoAtividade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RamoAtividadeRepositoryImplGCF implements RamoAtividadeRepositoryGCF {

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
    
    public RamoAtividade obterPorIdRamoAtividade(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<RamoAtividade>() {
            @Override
            public RamoAtividade mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                RamoAtividade ramoAtividade = new RamoAtividade();

                ramoAtividade.setId(rs.getInt("id"));
                ramoAtividade.setDescricao(rs.getString("descricao"));
                ramoAtividade.setRegistro(rs.getDate("registro"));

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                ramoAtividade.setUsuario(usuario);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                ramoAtividade.setEntidadeGestora(entidadeGestora);

                return ramoAtividade;
            }
        });

    }
    
    public List<RamoAtividade> obterTodosRamoAtividades(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<RamoAtividade>() {
            @Override
            public RamoAtividade mapRow(ResultSet rs, int rownumber) throws SQLException {

                RamoAtividade ramoAtividade = new RamoAtividade();

                ramoAtividade.setId(rs.getInt("id"));
                ramoAtividade.setDescricao(rs.getString("descricao"));
                ramoAtividade.setRegistro(rs.getDate("registro"));

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                ramoAtividade.setUsuario(usuario);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                ramoAtividade.setEntidadeGestora(entidadeGestora);

                return ramoAtividade;
            }
        }, entidade);

    }
    
}

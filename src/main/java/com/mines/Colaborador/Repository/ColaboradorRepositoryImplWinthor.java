package com.mines.Colaborador.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Colaborador.Model.Colaborador;
import com.mines.Colaborador.Model.Funcao;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ColaboradorRepositoryImplWinthor implements ColaboradorRepositoryWinthor {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FuncaoRepository funcaoRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Colaborador obterPorIdColaborador(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Colaborador>() {
            @Override
            public Colaborador mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Colaborador colaborador = new Colaborador();

                colaborador.setId(rs.getInt("id"));
                colaborador.setDescricao(rs.getString("descricao"));

                Integer funcaoId = rs.getInt("funcao");
                Funcao funcao = funcaoRepo.obterPorIdFuncao(funcaoId);
                colaborador.setFuncao(funcao);

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                colaborador.setEntidadeGestora(entidadeGestora);

                return colaborador;
            }
        });

    }
    
    public List<Colaborador> obterTodosColaboradores(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Colaborador>() {
            @Override
            public Colaborador mapRow(ResultSet rs, int rownumber) throws SQLException {

                Colaborador colaborador = new Colaborador();

                colaborador.setId(rs.getInt("id"));
                colaborador.setDescricao(rs.getString("descricao"));

                Integer funcaoId = rs.getInt("funcao");
                Funcao funcao = funcaoRepo.obterPorIdFuncao(funcaoId);
                colaborador.setFuncao(funcao);

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                colaborador.setEntidadeGestora(entidadeGestora);

                return colaborador;
            }
        }, entidade);

    }
    
}




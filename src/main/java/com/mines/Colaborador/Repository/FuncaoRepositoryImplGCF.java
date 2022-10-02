package com.mines.Colaborador.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Colaborador.Model.Funcao;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class FuncaoRepositoryImplGCF implements FuncaoRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Funcao obterPorIdFuncao(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Funcao>() {
            @Override
            public Funcao mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Funcao funcao = new Funcao();

                funcao.setId(rs.getInt("id"));
                funcao.setTitulo(rs.getString("titulo"));

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                funcao.setEntidadeGestora(entidadeGestora);

                return funcao;
            }
        });

    }

    public List<Funcao> obterTodosFuncoes(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Funcao>() {
            @Override
            public Funcao mapRow(ResultSet rs, int rownumber) throws SQLException {

                Funcao funcao = new Funcao();

                funcao.setId(rs.getInt("id"));
                funcao.setTitulo(rs.getString("titulo"));

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                funcao.setEntidadeGestora(entidadeGestora);

                return funcao;
            }
        }, entidade);

    }
    
}



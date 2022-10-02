package com.mines.CondicaoPagto.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.CondicaoPagto.Model.TipoPagto;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TipoPagtoRepositoryImplWinthor implements TipoPagtoRepositoryWinthor {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public TipoPagto obterPorIdTipoPagto(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<TipoPagto>() {
            @Override
            public TipoPagto mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                TipoPagto tipoPagto = new TipoPagto();

                tipoPagto.setId(rs.getInt("id"));
                tipoPagto.setTitulo(rs.getString("titulo"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                tipoPagto.setEntidadeGestora(entidadeGestora);

                return tipoPagto;
            }
        });

    }
    
    public List<TipoPagto> obterTodosTiposPagto(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<TipoPagto>() {
            @Override
            public TipoPagto mapRow(ResultSet rs, int rownumber) throws SQLException {

                TipoPagto tipoPagto = new TipoPagto();

                tipoPagto.setId(rs.getInt("id"));
                tipoPagto.setTitulo(rs.getString("titulo"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                tipoPagto.setEntidadeGestora(entidadeGestora);

                return tipoPagto;
            }
        }, entidade);

    }
    
}

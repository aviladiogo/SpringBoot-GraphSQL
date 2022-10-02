package com.mines.CondicaoPagto.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.CondicaoPagto.Model.CondicaoPagto;
import com.mines.CondicaoPagto.Model.PrazoParcela;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CondicaoPagtoRepositoryImplWinthor implements CondicaoPagtoRepositoryWinthor {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PrazoParcelaRepository prazoEntregaRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public CondicaoPagto obterPorIdCondicaoPagto(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<CondicaoPagto>() {
            @Override
            public CondicaoPagto mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                CondicaoPagto condicaoPagto = new CondicaoPagto();

                condicaoPagto.setId(rs.getInt("id"));
                condicaoPagto.setTitulo(rs.getString("titulo"));
                condicaoPagto.setDescricao(rs.getString("descricao"));
                condicaoPagto.setParcelas(rs.getInt("parcelas"));

                Integer prazoParcelasId = rs.getInt("prazoparcelas");
                PrazoParcela prazoParcelas = prazoEntregaRepo.obterPorIdPrazoParcela(prazoParcelasId);
                condicaoPagto.setPrazoParcelas(prazoParcelas);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                condicaoPagto.setEntidadeGestora(entidadeGestora);

                return condicaoPagto;
            }
        });

    }
    
    public List<CondicaoPagto> obterTodosCondicoesPagto(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<CondicaoPagto>() {
            @Override
            public CondicaoPagto mapRow(ResultSet rs, int rownumber) throws SQLException {

                CondicaoPagto condicaoPagto = new CondicaoPagto();

                condicaoPagto.setId(rs.getInt("id"));
                condicaoPagto.setTitulo(rs.getString("titulo"));
                condicaoPagto.setDescricao(rs.getString("descricao"));
                condicaoPagto.setParcelas(rs.getInt("parcelas"));

                Integer prazoParcelasId = rs.getInt("prazoparcelas");
                PrazoParcela prazoParcelas = prazoEntregaRepo.obterPorIdPrazoParcela(prazoParcelasId);
                condicaoPagto.setPrazoParcelas(prazoParcelas);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                condicaoPagto.setEntidadeGestora(entidadeGestora);

                return condicaoPagto;
            }
        }, entidade);

    }
    
}

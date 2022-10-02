package com.mines.CurvaABCZ.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.CurvaABCZ.Model.PoliticaCurva;
import com.mines.CurvaABCZ.Model.TipoCurvaAbcz;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PoliticaCurvaRepositoryImplGCF implements PoliticaCurvaRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TipoCurvaAbczRepository tipoCurvaAbczRepo;

    @Autowired
    private EmpresaRepository empresaRepo;


    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public PoliticaCurva obterPorIdPoliticaCurva(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<PoliticaCurva>() {
            @Override
            public PoliticaCurva mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                PoliticaCurva politicaCurva = new PoliticaCurva();

                politicaCurva.setId(rs.getInt("id"));

                Integer tipoCurvaId = rs.getInt("tipocurva");
                TipoCurvaAbcz tipoCurva = tipoCurvaAbczRepo.obterPorIdTipoCurvaAbcz(tipoCurvaId);
                politicaCurva.setTipoCurva(tipoCurva);

                politicaCurva.setDiasA(rs.getInt("diasa"));
                politicaCurva.setDiasB(rs.getInt("diasb"));
                politicaCurva.setDiasC(rs.getInt("diasc"));
                politicaCurva.setDiasZ(rs.getInt("diasz"));
                politicaCurva.setFrequenciaA(rs.getInt("frequenciaa"));
                politicaCurva.setFrequenciaB(rs.getInt("frequenciab"));
                politicaCurva.setFrequenciaC(rs.getInt("frequenciac"));
                politicaCurva.setFrequenciaZ(rs.getInt("frequenciaz"));
                politicaCurva.setMaxDiasA(rs.getInt("maxdiasa"));
                politicaCurva.setMaxDiasB(rs.getInt("maxdiasb"));
                politicaCurva.setMaxDiasC(rs.getInt("maxdiasc"));
                politicaCurva.setMaxDiasZ(rs.getInt("maxdiasz"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                politicaCurva.setEntidadeGestora(entidadeGestora);
                
                return politicaCurva;
            }
        });

    }
    
    public List<PoliticaCurva> obterTodosPoliticaCurvas(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<PoliticaCurva>() {
            @Override
            public PoliticaCurva mapRow(ResultSet rs, int rownumber) throws SQLException {

                PoliticaCurva politicaCurva = new PoliticaCurva();

                politicaCurva.setId(rs.getInt("id"));

                Integer tipoCurvaId = rs.getInt("tipocurva");
                TipoCurvaAbcz tipoCurva = tipoCurvaAbczRepo.obterPorIdTipoCurvaAbcz(tipoCurvaId);
                politicaCurva.setTipoCurva(tipoCurva);

                politicaCurva.setDiasA(rs.getInt("diasa"));
                politicaCurva.setDiasB(rs.getInt("diasb"));
                politicaCurva.setDiasC(rs.getInt("diasc"));
                politicaCurva.setDiasZ(rs.getInt("diasz"));
                politicaCurva.setFrequenciaA(rs.getInt("frequenciaa"));
                politicaCurva.setFrequenciaB(rs.getInt("frequenciab"));
                politicaCurva.setFrequenciaC(rs.getInt("frequenciac"));
                politicaCurva.setFrequenciaZ(rs.getInt("frequenciaz"));
                politicaCurva.setMaxDiasA(rs.getInt("maxdiasa"));
                politicaCurva.setMaxDiasB(rs.getInt("maxdiasb"));
                politicaCurva.setMaxDiasC(rs.getInt("maxdiasc"));
                politicaCurva.setMaxDiasZ(rs.getInt("maxdiasz"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                politicaCurva.setEntidadeGestora(entidadeGestora);
                
                return politicaCurva;
            }
        }, entidade);

    }
   
}
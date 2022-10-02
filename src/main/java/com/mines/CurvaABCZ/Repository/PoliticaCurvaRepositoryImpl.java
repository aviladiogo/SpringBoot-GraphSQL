package com.mines.CurvaABCZ.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.CurvaABCZ.Model.PoliticaCurva;
import com.mines.CurvaABCZ.Model.TipoCurvaAbcz;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class PoliticaCurvaRepositoryImpl implements PoliticaCurvaRepository {

    private static String SELECT_ALL = " select * from dx_politicacurva where entidadegestora = ?";
    private static String SELECT_ONE = " select * from dx_politicacurva where id = ? ";
    private static String INSERT = " insert into dx_politicacurva (id, tipocurva, diasa, diasb, diasc, " 
            + " diasz, frequenciaa, frequenciab, frequenciac, frequenciaz, maxdiasa, maxdiasb, maxdiasc, "
            + " maxdiasz, entidadegestora) values (nextval('dx_politicacurva_id_seq'),?,?,?,?,?,?,?,?,?,"
            + "?,?,?,?,?) ";
    private static String UPDATE = " update dx_politicacurva set diasa = ?, diasb = ?, diasc = ?, diasz = ?,"
            + " frequenciaa = ?, frequenciab = ?, frequenciac = ?, frequenciaz = ?, maxdiasa = ?, maxdiasb = ?,"
            + " maxdiasc = ?, maxdiasz = ?"
            + " where id = ? ";
    private static String DELETE = " delete from dx_politicacurva where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TipoCurvaAbczRepository tipoCurvaAbczRepo;

    @Autowired
    private EmpresaRepository empresaRepo;


    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public PoliticaCurva atualizarPoliticaCurva(PoliticaCurva politicaCurva) {
       
        jdbcTemplate.update(UPDATE, new Object[] {politicaCurva.getDiasA(),politicaCurva.getDiasB(), 
            politicaCurva.getDiasC(), politicaCurva.getDiasZ(), politicaCurva.getFrequenciaA(), 
            politicaCurva.getFrequenciaB(), politicaCurva.getFrequenciaC(), politicaCurva.getFrequenciaZ(), 
            politicaCurva.getMaxDiasA(), politicaCurva.getMaxDiasB(), politicaCurva.getMaxDiasC(), 
            politicaCurva.getMaxDiasZ(), politicaCurva.getId()});
 
        return politicaCurva;

    }

    
    public Boolean deletarPoliticaCurva(PoliticaCurva politicaCurva) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {politicaCurva.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
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

    public PoliticaCurva salvarPoliticaCurva(PoliticaCurva politicaCurva) {

        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setDouble(1, politicaCurva.getTipoCurva().getId());
                ps.setDouble(2, politicaCurva.getDiasA());
                ps.setDouble(3, politicaCurva.getDiasB());
                ps.setDouble(4, politicaCurva.getDiasC());
                ps.setDouble(5, politicaCurva.getDiasZ());
                ps.setDouble(6, politicaCurva.getFrequenciaA());
                ps.setDouble(7, politicaCurva.getFrequenciaB());
                ps.setDouble(8, politicaCurva.getFrequenciaC());
                ps.setDouble(9, politicaCurva.getFrequenciaZ());
                ps.setDouble(10, politicaCurva.getMaxDiasA());
                ps.setDouble(11, politicaCurva.getMaxDiasB());
                ps.setDouble(12, politicaCurva.getMaxDiasC());
                ps.setDouble(13, politicaCurva.getMaxDiasZ());
                ps.setInt(14, politicaCurva.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        politicaCurva.setId(id);
            
        return politicaCurva;

    }
   
}
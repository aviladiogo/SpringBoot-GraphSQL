package com.mines.PrecoEOferta.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.Fornecedor.Model.Fornecedor;
import com.mines.Fornecedor.Repository.FornecedorRepository;
import com.mines.PrecoEOferta.Model.Oferta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class OfertaRepositoryImpl implements OfertaRepository {

    private static String SELECT_ALL = " select * from dx_oferta where entidadegestora=?";
    private static String SELECT_ONE = " select * from dx_oferta where id = ? ";
    private static String INSERT = " insert into dx_oferta (id, titulo, fornecedor, iniciovalidade, "
            + " finalvalidade, entidadegestora) "
            + " values (nextval('dx_oferta_id_seq'),?,?,?,?,?) ";
    private static String UPDATE = " update dx_oferta set titulo = ? where id = ? ";
    private static String DELETE = " delete from dx_oferta where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FornecedorRepository fornecedorRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Oferta atualizarOferta(Oferta oferta) {
       
        jdbcTemplate.update(UPDATE, new Object[] {oferta.getTitulo(),oferta.getId()});

        return oferta;

    }

    
    public Boolean deletarOferta(Oferta oferta) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {oferta.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    
    public Oferta obterPorIdOferta(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Oferta>() {
            @Override
            public Oferta mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Oferta oferta = new Oferta();

                oferta.setId(rs.getInt("id"));
                oferta.setTitulo(rs.getString("titulo"));
                oferta.setInicioValidade(rs.getDate("iniciovalidade"));
                oferta.setFinalValidade(rs.getDate("finalvalidade"));

                Integer fornecedorId = rs.getInt("fornecedor");
                Fornecedor fornecedor = fornecedorRepo.obterPorIdFornecedor(fornecedorId);
                oferta.setFornecedor(fornecedor);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                oferta.setEntidadeGestora(entidadeGestora);

                return oferta;
            }
        });

    }
    
    public List<Oferta> obterTodosOfertas(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Oferta>() {
            @Override
            public Oferta mapRow(ResultSet rs, int rownumber) throws SQLException {

                Oferta oferta = new Oferta();

                oferta.setId(rs.getInt("id"));
                oferta.setTitulo(rs.getString("titulo"));
                oferta.setInicioValidade(rs.getDate("iniciovalidade"));
                oferta.setFinalValidade(rs.getDate("finalvalidade"));

                Integer fornecedorId = rs.getInt("fornecedor");
                Fornecedor fornecedor = fornecedorRepo.obterPorIdFornecedor(fornecedorId);
                oferta.setFornecedor(fornecedor);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                oferta.setEntidadeGestora(entidadeGestora);

                return oferta;
            }
        }, entidade);

    }

    public Oferta salvarOferta(Oferta oferta) {

        oferta.getInicioValidade().setDate(oferta.getInicioValidade().getDate()+1);
        oferta.getFinalValidade().setDate(oferta.getFinalValidade().getDate()+1);

        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, oferta.getTitulo());
                ps.setInt(2, oferta.getFornecedor().getId());
                java.sql.Date inicioValidade = new java.sql.Date(oferta.getInicioValidade().getTime());
                ps.setDate(3, inicioValidade);
                java.sql.Date finalValidade = new java.sql.Date(oferta.getFinalValidade().getTime());
                ps.setDate(4, finalValidade);
                ps.setInt(5, oferta.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        oferta.setId(id);

        return oferta;

    }
    
}


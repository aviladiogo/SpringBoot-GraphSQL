package com.mines.PrecoEOferta.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.Fornecedor.Model.Fornecedor;
import com.mines.Fornecedor.Repository.FornecedorRepository;
import com.mines.PrecoEOferta.Model.Oferta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class OfertaRepositoryImplGCF implements OfertaRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FornecedorRepository fornecedorRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
    
}


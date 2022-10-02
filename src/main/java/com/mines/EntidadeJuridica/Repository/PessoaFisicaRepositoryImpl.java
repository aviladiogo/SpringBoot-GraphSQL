package com.mines.EntidadeJuridica.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class PessoaFisicaRepositoryImpl implements PessoaFisicaRepository{
    
    private static String SELECT_ALL = " select * from dx_pessoafisica where entidade = ?";
    private static String SELECT_ONE = " select * from dx_pessoafisica where id = ? ";
    private static String INSERT = " insert into dx_pessoafisica (id, nome, apelido, cpf, email, entidade)"
            + " values (nextval('dx_pessoafisica_id_seq'),?,?,?,?,?) ";
    private static String UPDATE = " update dx_pessoafisica set nome = ?, apelido = ?, cpf = ?, email = ? "
            + " where id = ? ";
    private static String DELETE = " delete from dx_pessoafisica where id = ? ";

    @Autowired
    private JdbcTemplate jbdcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    public PessoaFisica atualizarPessoaFisica(PessoaFisica pessoaFisica) {
        
        jbdcTemplate.update(UPDATE, new Object[] {pessoaFisica.getNome(), pessoaFisica.getApelido(), 
            pessoaFisica.getCpf(), pessoaFisica.getEmail(), pessoaFisica.getId()});

        return pessoaFisica;
    }

    public Boolean deletarPessoaFisica(PessoaFisica pessoaFisica) {

        Boolean ret = false;

        Integer rows = jbdcTemplate.update(DELETE, new Object[] {pessoaFisica.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;

    }

    public PessoaFisica obterPorIdPessoaFisica(Integer id) {

        return jbdcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<PessoaFisica>() {
            @Override
            public PessoaFisica mapRow(ResultSet rs, int rownumber) throws SQLException {

                PessoaFisica pessoaFisica = new PessoaFisica();

                pessoaFisica.setId(rs.getInt("id"));
                pessoaFisica.setNome(rs.getString("nome"));
                pessoaFisica.setApelido(rs.getString("apelido"));
                pessoaFisica.setCpf(rs.getString("cpf"));
                pessoaFisica.setEmail(rs.getString("email"));

                Integer entidadeId = rs.getInt("entidade");
                Empresa entidade = empresaRepo.obterPorIdEmpresa(entidadeId);
                pessoaFisica.setEntidade(entidade);

                return pessoaFisica;
            }
        });

    }

    public List<PessoaFisica> obterTodosPessoasFisica(Integer entidade) {

        return jbdcTemplate.query(SELECT_ALL, new RowMapper<PessoaFisica>() {
            @Override
            public PessoaFisica mapRow(ResultSet rs, int rownumber) throws SQLException {

                PessoaFisica pessoaFisica = new PessoaFisica();

                pessoaFisica.setId(rs.getInt("id"));
                pessoaFisica.setNome(rs.getString("nome"));
                pessoaFisica.setApelido(rs.getString("apelido"));
                pessoaFisica.setCpf(rs.getString("cpf"));
                pessoaFisica.setEmail(rs.getString("email"));

                Integer entidadeId = rs.getInt("entidade");
                Empresa entidade = empresaRepo.obterPorIdEmpresa(entidadeId);
                pessoaFisica.setEntidade(entidade);

                return pessoaFisica;
            }
        }, entidade);
    }

    public PessoaFisica salvarPessoaFisica(PessoaFisica pessoaFisica) {

        KeyHolder holder = new GeneratedKeyHolder();
        
        jbdcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, pessoaFisica.getNome());
                ps.setString(2, pessoaFisica.getApelido());
                ps.setString(3, pessoaFisica.getCpf());
                ps.setString(4, pessoaFisica.getEmail());
                ps.setInt(5, pessoaFisica.getEntidade().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        pessoaFisica.setId(id);
            
        return pessoaFisica;
    }
}

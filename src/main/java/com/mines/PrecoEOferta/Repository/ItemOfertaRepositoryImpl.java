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
import com.mines.PrecoEOferta.Model.ItemOferta;
import com.mines.Produto.Model.Produto;
import com.mines.Produto.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ItemOfertaRepositoryImpl implements ItemOfertaRepository {

    private static String SELECT_ALL = " select * from dx_itemoferta where entidadegestora=? "
            + " order by descricao";
    private static String SELECT_ONE = " select * from dx_itemoferta where id = ? ";
    private static String INSERT = " insert into dx_itemoferta (id, descricao, produto, fabricante, "
            + " entidadegestora) "
            + " values (nextval('dx_itemoferta_id_seq'),?,?,?,?) ";
    private static String UPDATE = " update dx_itemoferta set descricao = ? where id = ? ";
    private static String DELETE = " delete from dx_itemoferta where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProdutoRepository produtoRepo;

    @Autowired
    private FornecedorRepository fornecedorRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public ItemOferta atualizarItemOferta(ItemOferta itemOferta) {
       
        jdbcTemplate.update(UPDATE, new Object[] {itemOferta.getDescricao(),itemOferta.getId()});

        return itemOferta;

    }

    
    public Boolean deletarItemOferta(ItemOferta itemOferta) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {itemOferta.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    
    public ItemOferta obterPorIdItemOferta(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<ItemOferta>() {
            @Override
            public ItemOferta mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                ItemOferta itemOferta = new ItemOferta();

                itemOferta.setId(rs.getInt("id"));
                itemOferta.setDescricao(rs.getString("descricao"));

                Integer produtoId = rs.getInt("produto");
                Produto produto = produtoRepo.obterPorIdProduto(produtoId);
                itemOferta.setProduto(produto);

                Integer fabricanteId = rs.getInt("fabricante");
                Fornecedor fabricante = fornecedorRepo.obterPorIdFornecedor(fabricanteId);
                itemOferta.setFabricante(fabricante);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                itemOferta.setEntidadeGestora(entidadeGestora);

                return itemOferta;
            }
        });

    }
    
    public List<ItemOferta> obterTodosItensOferta(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<ItemOferta>() {
            @Override
            public ItemOferta mapRow(ResultSet rs, int rownumber) throws SQLException {

                ItemOferta itemOferta = new ItemOferta();

                itemOferta.setId(rs.getInt("id"));
                itemOferta.setDescricao(rs.getString("descricao"));

                Integer produtoId = rs.getInt("produto");
                Produto produto = produtoRepo.obterPorIdProduto(produtoId);
                itemOferta.setProduto(produto);

                Integer fabricanteId = rs.getInt("fabricante");
                Fornecedor fabricante = fornecedorRepo.obterPorIdFornecedor(fabricanteId);
                itemOferta.setFabricante(fabricante);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                itemOferta.setEntidadeGestora(entidadeGestora);

                return itemOferta;
            }
        }, entidade);

    }

    public ItemOferta salvarItemOferta(ItemOferta itemOferta) {

        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, itemOferta.getDescricao());
                ps.setInt(2, itemOferta.getProduto().getId());
                ps.setInt(3, itemOferta.getFabricante().getId());
                ps.setInt(4, itemOferta.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        itemOferta.setId(id);
            
        return itemOferta;

    }
    
}

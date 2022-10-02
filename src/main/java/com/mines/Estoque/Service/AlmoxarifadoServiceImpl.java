package com.mines.Estoque.Service;

import java.util.List;
import com.mines.Estoque.Model.Almoxarifado;
import com.mines.Estoque.Repository.AlmoxarifadoRepository;
import com.mines.Estoque.Repository.AlmoxarifadoRepositoryGCF;
import com.mines.Estoque.Repository.AlmoxarifadoRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AlmoxarifadoServiceImpl implements AlmoxarifadoService{

    @Autowired
    private AlmoxarifadoRepository almoxarifadoRepo;

    @Autowired 
    private AlmoxarifadoRepositoryWinthor almoxarifadoRepoWinthor;

    @Autowired
    private AlmoxarifadoRepositoryGCF almoxarifadoRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<Almoxarifado> obterTodosAlmoxarifados(Integer entidade) {

        List<Almoxarifado> almoxarifado = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                almoxarifado = almoxarifadoRepo.obterTodosAlmoxarifados(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                almoxarifado = almoxarifadoRepoWinthor.obterTodosAlmoxarifados(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                almoxarifado = almoxarifadoRepoGCF.obterTodosAlmoxarifados(entidade);
                break;
            }
        }

        return almoxarifado;
    }

    @Override
    public Almoxarifado obterPorIdAlmoxarifado(Integer fornecedor) {

        Almoxarifado almoxarifado = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                almoxarifado = almoxarifadoRepo.obterPorIdAlmoxarifado(fornecedor);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                almoxarifado = almoxarifadoRepoWinthor.obterPorIdAlmoxarifado(fornecedor);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                almoxarifado = almoxarifadoRepoGCF.obterPorIdAlmoxarifado(fornecedor);
                break;
            }
        }

        return almoxarifado;
    }
    
}

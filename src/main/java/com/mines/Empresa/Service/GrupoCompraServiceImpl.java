package com.mines.Empresa.Service;

import java.util.List;
import com.mines.Empresa.Model.GrupoCompra;
import com.mines.Empresa.Repository.GrupoCompraRepository;
import com.mines.Empresa.Repository.GrupoCompraRepositoryGCF;
import com.mines.Empresa.Repository.GrupoCompraRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GrupoCompraServiceImpl implements GrupoCompraService{
    
    @Autowired
    private GrupoCompraRepository grupoCompraRepo;

    @Autowired 
    private GrupoCompraRepositoryWinthor grupoCompraRepoWinthor;

    @Autowired
    private GrupoCompraRepositoryGCF grupoCompraRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<GrupoCompra> obterTodosGrupoCompras(Integer entidade) {

        List<GrupoCompra> grupoCompra = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                grupoCompra = grupoCompraRepo.obterTodosGrupoCompras(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                grupoCompra = grupoCompraRepoWinthor.obterTodosGrupoCompras(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                grupoCompra = grupoCompraRepoGCF.obterTodosGrupoCompras(entidade);
                break;
            }
        }

        return grupoCompra;
    }

    @Override
    public GrupoCompra obterPorIdGrupoCompra(Integer id) {

        GrupoCompra grupoCompra = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                grupoCompra = grupoCompraRepo.obterPorIdGrupoCompra(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                grupoCompra = grupoCompraRepoWinthor.obterPorIdGrupoCompra(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                grupoCompra = grupoCompraRepoGCF.obterPorIdGrupoCompra(id);
                break;
            }
        }

        return grupoCompra;
    }
}


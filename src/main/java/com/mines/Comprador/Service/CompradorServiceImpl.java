package com.mines.Comprador.Service;

import java.util.List;
import com.mines.Comprador.Model.Comprador;
import com.mines.Comprador.Repository.CompradorRepository;
import com.mines.Comprador.Repository.CompradorRepositoryGCF;
import com.mines.Comprador.Repository.CompradorRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CompradorServiceImpl implements CompradorService{
    
    @Autowired
    private CompradorRepository compradorRepo;

    @Autowired 
    private CompradorRepositoryWinthor compradorRepoWinthor;

    @Autowired
    private CompradorRepositoryGCF compradorRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<Comprador> obterTodosCompradores(Integer entidade) {

        List<Comprador> comprador = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                comprador = compradorRepo.obterTodosCompradores(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                comprador = compradorRepoWinthor.obterTodosCompradores(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                comprador = compradorRepoGCF.obterTodosCompradores(entidade);
                break;
            }
        }

        return comprador;
    }

    @Override
    public Comprador obterPorIdComprador(Integer id) {

        Comprador comprador = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                comprador = compradorRepo.obterPorIdComprador(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                comprador = compradorRepoWinthor.obterPorIdComprador(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                comprador = compradorRepoGCF.obterPorIdComprador(id);
                break;
            }
        }

        return comprador;
    }
}


package com.mines.Comprador.Service;

import java.util.List;
import com.mines.Comprador.Model.CompradorDepartamento;
import com.mines.Comprador.Repository.CompradorDepartamentoRepository;
import com.mines.Comprador.Repository.CompradorDepartamentoRepositoryGCF;
import com.mines.Comprador.Repository.CompradorDepartamentoRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CompradorDepartamentoServiceImpl implements CompradorDepartamentoService{
    
    @Autowired
    private CompradorDepartamentoRepository compradorDepartamentoRepo;

    @Autowired 
    private CompradorDepartamentoRepositoryWinthor compradorDepartamentoRepoWinthor;

    @Autowired
    private CompradorDepartamentoRepositoryGCF compradorDepartamentoRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<CompradorDepartamento> obterTodosCompradoresDepartamento(Integer entidade) {

        List<CompradorDepartamento> compradorDepartamento = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                compradorDepartamento = compradorDepartamentoRepo.obterTodosCompradoresDepartamento(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                compradorDepartamento = compradorDepartamentoRepoWinthor.obterTodosCompradoresDepartamento(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                compradorDepartamento = compradorDepartamentoRepoGCF.obterTodosCompradoresDepartamento(entidade);
                break;
            }
        }

        return compradorDepartamento;
    }

    @Override
    public CompradorDepartamento obterPorIdCompradorDepartamento(Integer id) {

        CompradorDepartamento compradorDepartamento = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                compradorDepartamento = compradorDepartamentoRepo.obterPorIdCompradorDepartamento(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                compradorDepartamento = compradorDepartamentoRepoWinthor.obterPorIdCompradorDepartamento(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                compradorDepartamento = compradorDepartamentoRepoGCF.obterPorIdCompradorDepartamento(id);
                break;
            }
        }

        return compradorDepartamento;
    }
}


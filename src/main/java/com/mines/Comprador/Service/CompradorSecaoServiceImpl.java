package com.mines.Comprador.Service;

import java.util.List;
import com.mines.Comprador.Model.CompradorSecao;
import com.mines.Comprador.Repository.CompradorSecaoRepository;
import com.mines.Comprador.Repository.CompradorSecaoRepositoryGCF;
import com.mines.Comprador.Repository.CompradorSecaoRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CompradorSecaoServiceImpl implements CompradorSecaoService{
    
    @Autowired
    private CompradorSecaoRepository compradorSecaoRepo;

    @Autowired 
    private CompradorSecaoRepositoryWinthor compradorSecaoRepoWinthor;

    @Autowired
    private CompradorSecaoRepositoryGCF compradorSecaoRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<CompradorSecao> obterTodosCompradoresSecao(Integer entidade) {

        List<CompradorSecao> compradorSecao = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                compradorSecao = compradorSecaoRepo.obterTodosCompradoresSecao(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                compradorSecao = compradorSecaoRepoWinthor.obterTodosCompradoresSecao(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                compradorSecao = compradorSecaoRepoGCF.obterTodosCompradoresSecao(entidade);
                break;
            }
        }

        return compradorSecao;
    }

    @Override
    public CompradorSecao obterPorIdCompradorSecao(Integer id) {

        CompradorSecao compradorSecao = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                compradorSecao = compradorSecaoRepo.obterPorIdCompradorSecao(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                compradorSecao = compradorSecaoRepoWinthor.obterPorIdCompradorSecao(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                compradorSecao = compradorSecaoRepoGCF.obterPorIdCompradorSecao(id);
                break;
            }
        }

        return compradorSecao;
    }
}


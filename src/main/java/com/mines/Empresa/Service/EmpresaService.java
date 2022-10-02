package com.mines.Empresa.Service;

import java.util.List;
import com.mines.Empresa.Model.Empresa;

public interface EmpresaService {
    
    List<Empresa> obterTodosEmpresas(Integer entidade);
    
    Empresa obterPorIdEmpresa(Integer id);

}


package br.com.vitavault.controller;

import br.com.vitavault.model.Funcionario;
import java.util.List;

public interface FuncionarioInterface {
    void salvarFuncionario(Funcionario oFuncionario);
    List<Funcionario> buscarFuncionarios();
}

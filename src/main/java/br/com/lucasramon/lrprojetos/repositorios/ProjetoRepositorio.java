package br.com.lucasramon.lrprojetos.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lucasramon.lrprojetos.entidades.Cliente;
import br.com.lucasramon.lrprojetos.entidades.Funcionario;
import br.com.lucasramon.lrprojetos.entidades.Projeto;

public interface ProjetoRepositorio extends JpaRepository<Projeto, Long> {

    @EntityGraph(attributePaths = {"cliente", "lider"})
    List<Projeto> findAll();

    List<Projeto> findByCliente(Cliente cliente);

    List<Projeto> findByLider(Funcionario lider);

}

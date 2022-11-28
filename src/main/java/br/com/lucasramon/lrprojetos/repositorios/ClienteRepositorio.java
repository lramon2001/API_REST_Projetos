package br.com.lucasramon.lrprojetos.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lucasramon.lrprojetos.entidades.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    @EntityGraph(attributePaths = "endereco")

    Optional<Cliente> findByEmail(String email);

    Optional<Cliente> findByCpf(String cpf);

}

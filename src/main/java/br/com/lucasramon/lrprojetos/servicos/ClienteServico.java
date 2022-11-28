package br.com.lucasramon.lrprojetos.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.lucasramon.lrprojetos.entidades.Cliente;
import br.com.lucasramon.lrprojetos.excecoes.ClienteNaoEncontradoException;
import br.com.lucasramon.lrprojetos.excecoes.ClientePossuiProjetosException;
import br.com.lucasramon.lrprojetos.repositorios.ClienteRepositorio;
import br.com.lucasramon.lrprojetos.repositorios.ProjetoRepositorio;

@Service
public class ClienteServico {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private ProjetoRepositorio projetoRepositorio;

    public List<Cliente> buscarTodos() {
        return clienteRepositorio.findAll();
    }

    
    public Page<Cliente> buscarTodos(Pageable paginacao) {
        return clienteRepositorio.findAll(paginacao);
    }



    public Cliente buscarPorId(Long id) {
        return clienteRepositorio.findById(id)
            .orElseThrow(() -> new ClienteNaoEncontradoException(id));
    }

    public Cliente cadastrar(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    public Cliente atualizar(Cliente cliente, Long id) {
        buscarPorId(id);

        return clienteRepositorio.save(cliente);
    }

    public void excluirPorId(Long id) {
        Cliente clienteEncontrado = buscarPorId(id);

        if (projetoRepositorio.findByCliente(clienteEncontrado).isEmpty()) {
            clienteRepositorio.delete(clienteEncontrado);
        } else {
            throw new ClientePossuiProjetosException(id);
        }
    }

}

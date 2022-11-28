package br.com.lucasramon.lrprojetos.servicos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.lucasramon.lrprojetos.api.mapeadores.ProjetoMapeador;
import br.com.lucasramon.lrprojetos.dto.EquipeDTO;
import br.com.lucasramon.lrprojetos.dto.ProjetoDTO;
import br.com.lucasramon.lrprojetos.entidades.Funcionario;
import br.com.lucasramon.lrprojetos.entidades.Projeto;
import br.com.lucasramon.lrprojetos.excecoes.ProjetoNaoEncontradoException;
import br.com.lucasramon.lrprojetos.repositorios.ProjetoRepositorio;

@Service
public class ProjetoServico {

    @Autowired
    private ProjetoRepositorio projetoRepositorio;

    @Autowired
    private ProjetoMapeador projetoMapeador;

    @Autowired
    private FuncionarioServico funcionarioServico;

    public List<Projeto> buscarTodos() {
        return projetoRepositorio.findAll();
    }

    public Page<Projeto> buscarTodos(Pageable paginacao) {
        return projetoRepositorio.findAll(paginacao);
    }

    public Projeto buscarPorId(Long id) {
        return projetoRepositorio.findById(id)
            .orElseThrow(() -> new ProjetoNaoEncontradoException(id));
    }

    public Projeto cadastrar(Projeto projeto) {
        return projetoRepositorio.save(projeto);
    }

    public Projeto cadastrar(ProjetoDTO projetoDTO) {
        Projeto projeto = projetoMapeador.converterParaEntidade(projetoDTO);
        return projetoRepositorio.save(projeto);
    }

    public Projeto atualizar(Projeto projeto, Long id) {
        buscarPorId(id);

        return projetoRepositorio.save(projeto);
    }

    public Projeto atualizar(ProjetoDTO projetoDTO, Long id) {
        buscarPorId(id);
        Projeto projeto = projetoMapeador.converterParaEntidade(projetoDTO);
        projeto.setId(id);
        return projetoRepositorio.save(projeto);
    }

    public List<Funcionario> atualizarEquipe(EquipeDTO equipeDTO, Long id){
        Projeto projeto = buscarPorId(id);
        List<Funcionario> equipe = new ArrayList<Funcionario>();
        equipeDTO.getEquipe().forEach(funcionarioID -> {
            Funcionario funcionario = funcionarioServico.buscarPorId(funcionarioID);
            equipe.add(funcionario);
        });

        projeto.setEquipe(equipe);
        projetoRepositorio.save(projeto);
        return equipe;
    }
    public void excluirPorId(Long id) {
        Projeto projetoEncontrado = buscarPorId(id);

        projetoRepositorio.delete(projetoEncontrado);
    }

   

}

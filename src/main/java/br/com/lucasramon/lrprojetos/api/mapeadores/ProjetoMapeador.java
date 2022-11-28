package br.com.lucasramon.lrprojetos.api.mapeadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.lucasramon.lrprojetos.dto.ProjetoDTO;
import br.com.lucasramon.lrprojetos.entidades.Projeto;
import br.com.lucasramon.lrprojetos.servicos.ClienteServico;
import br.com.lucasramon.lrprojetos.servicos.FuncionarioServico;


@Component
public class ProjetoMapeador {

    @Autowired
    private ClienteServico clienteServico;
    @Autowired
    private FuncionarioServico funcionarioServico;


    public Projeto converterParaEntidade(ProjetoDTO projetoDTO){
        Projeto projeto = new Projeto();

        projeto.setNome(projetoDTO.getNome());
        projeto.setDescricao(projetoDTO.getDescricao());
        projeto.setDataInicio(projetoDTO.getDataInicio());
        projeto.setDataFim(projetoDTO.getDataFim());
        projeto.setOrcamento(projetoDTO.getGastos());
        projeto.setCliente(clienteServico.buscarPorId(projetoDTO.getClienteId()));
        projeto.setLider(funcionarioServico.buscarPorId(projetoDTO.getLiderId()));
         
        return projeto;
    }
    
}

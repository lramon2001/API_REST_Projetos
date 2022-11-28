package br.com.lucasramon.lrprojetos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.lucasramon.lrprojetos.repositorios.ClienteRepositorio;
import br.com.lucasramon.lrprojetos.repositorios.FuncionarioRepositorio;
import br.com.lucasramon.lrprojetos.validadores.ClienteValidador;
import br.com.lucasramon.lrprojetos.validadores.FuncionarioValidador;
import br.com.lucasramon.lrprojetos.validadores.PessoaValidador;

@Configuration
public class ValidadorConfig {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private FuncionarioRepositorio funcionarioRepositorio;

    @Bean
    public ClienteValidador clienteValidador() {
        return new ClienteValidador(clienteRepositorio);
    }

    @Bean
    public FuncionarioValidador funcionarioValidador() {
        return new FuncionarioValidador(funcionarioRepositorio);
    }

    @Bean
    public PessoaValidador pessoaValidador() {
        return new PessoaValidador();
    }

}

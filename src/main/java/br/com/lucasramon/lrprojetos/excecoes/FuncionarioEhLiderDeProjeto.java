package br.com.lucasramon.lrprojetos.excecoes;

public class FuncionarioEhLiderDeProjeto extends RuntimeException {

    public FuncionarioEhLiderDeProjeto(Long id) {
        super(String.format("Funcionario com ID %d é lider de projeto(s)", id));
    }

}

package br.com.lucasramon.lrprojetos.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class EquipeDTO {
    @NotNull
    @NotEmpty
    List<Long> equipe;

    public List<Long> getEquipe() {
        return equipe;
    }

    public void setEquipe(List<Long> equipe) {
        this.equipe = equipe;
    }
    
}


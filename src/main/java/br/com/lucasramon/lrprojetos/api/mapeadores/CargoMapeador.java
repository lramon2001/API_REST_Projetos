package br.com.lucasramon.lrprojetos.api.mapeadores;

import org.springframework.stereotype.Component;

import br.com.lucasramon.lrprojetos.api.dto.CargoDto;
import br.com.lucasramon.lrprojetos.entidades.Cargo;

@Component
public class CargoMapeador {
    
    public Cargo converterParaEntidade(CargoDto cargoDto)
    {
        Cargo cargo = new Cargo();
        cargo.setNome(cargoDto.getNome());
        return cargo;
    }
}

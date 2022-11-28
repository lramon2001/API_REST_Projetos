package br.com.lucasramon.lrprojetos.api.hateoas;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.lucasramon.lrprojetos.api.controles.CargoControleApi;
import br.com.lucasramon.lrprojetos.api.controles.FuncionarioControleApi;
import br.com.lucasramon.lrprojetos.entidades.Funcionario;

@Component
public class FuncionarioAssembler implements SimpleRepresentationModelAssembler<Funcionario> {

    @Override
    public void addLinks(EntityModel<Funcionario> resource) {
     Long cargo_id = resource.getContent().getCargo().getId();
     Long funcionario_id = resource.getContent().getId();
     Link cargoLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CargoControleApi.class).buscaPorId(cargo_id)).withRel("cargo").withType("GET");
     Link selfLink =  WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FuncionarioControleApi.class).buscarPorId(funcionario_id)).withSelfRel().withType("GET");

     resource.add(cargoLink,selfLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Funcionario>> resources) {
        Link selfLink = WebMvcLinkBuilder.
        linkTo(WebMvcLinkBuilder.
        methodOn(FuncionarioControleApi.class).
        buscarTodos(null)).withSelfRel().withType("GET");

        resources.add(selfLink);
        
    }
    
}

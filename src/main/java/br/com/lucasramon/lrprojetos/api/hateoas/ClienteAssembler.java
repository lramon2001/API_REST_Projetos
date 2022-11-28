package br.com.lucasramon.lrprojetos.api.hateoas;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.lucasramon.lrprojetos.api.controles.ClienteControleApi;
import br.com.lucasramon.lrprojetos.entidades.Cliente;

@Component
public class ClienteAssembler implements SimpleRepresentationModelAssembler<Cliente> {

    @Override
    public void addLinks(EntityModel<Cliente> resource) {
        Long id = resource.getContent().getId();

        Link selfLink = WebMvcLinkBuilder.
        linkTo(WebMvcLinkBuilder.
        methodOn(ClienteControleApi.class).
        buscarPorId(id)).
        withSelfRel().
        withType("GET");

        Link projetosLink = WebMvcLinkBuilder.
        linkTo(WebMvcLinkBuilder.
        methodOn(ClienteControleApi.class)
        .buscarProjetos(id)).
        withRel("projetos").withType("GET");
     
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Cliente>> resources) {
      Link selfLink  =  WebMvcLinkBuilder.
      linkTo(WebMvcLinkBuilder.
      methodOn(ClienteControleApi.class).
      buscarTodos(null)).
      withSelfRel().withType("GET");

      resources.add(selfLink);
        
    }
    
}

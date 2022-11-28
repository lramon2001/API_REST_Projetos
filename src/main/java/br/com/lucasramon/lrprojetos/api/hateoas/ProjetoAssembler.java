package br.com.lucasramon.lrprojetos.api.hateoas;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.lucasramon.lrprojetos.api.controles.ClienteControleApi;
import br.com.lucasramon.lrprojetos.api.controles.FuncionarioControleApi;
import br.com.lucasramon.lrprojetos.api.controles.ProjetoControleApi;
import br.com.lucasramon.lrprojetos.entidades.Projeto;
@Component
public class ProjetoAssembler implements SimpleRepresentationModelAssembler<Projeto>{

    @Override
    public void addLinks(EntityModel<Projeto> resource) {
        Long id_cliente = resource.getContent().getCliente().getId();
        Long id_lider = resource.getContent().getLider().getId();
        Long id_projeto = resource.getContent().getId();
        Link cliente_link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClienteControleApi.class).buscarPorId(id_cliente)).withRel("lider").withType("GET");
        Link lider_link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FuncionarioControleApi.class).buscarPorId(id_lider)).withRel("lider").withType("GET");
        Link selLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProjetoControleApi.class).buscarPorId(id_projeto)).withSelfRel().withType("GET");
        Link editarLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProjetoControleApi.class).atualizar(null, id_projeto)).withRel("editar").withType("PUT");
        Link excluirLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProjetoControleApi.class).deletar(id_projeto)).withRel("excluir").withType("DELETE");
        Link equipeLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProjetoControleApi.class).buscarEquipe(id_projeto)).withRel("equipe").withType("GET");
        Link atualizarEquipeLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProjetoControleApi.class).atualizarEquipe(id_projeto, null)).withRel("atulizarEquipe").withType("PATCH");
        resource.add(cliente_link,lider_link,selLink,editarLink,excluirLink,equipeLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Projeto>> resources) {
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProjetoControleApi.class).buscarTodos(null)).withSelfRel().withType("GET");
        resources.add(selfLink);
    }
    
}

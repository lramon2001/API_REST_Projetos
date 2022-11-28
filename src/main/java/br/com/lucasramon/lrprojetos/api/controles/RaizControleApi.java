package br.com.lucasramon.lrprojetos.api.controles;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucasramon.lrprojetos.api.hateoas.RaizModel;

@RestController
@RequestMapping("/api/v1")
public class RaizControleApi {
    @GetMapping
    public RaizModel raiz()
    {
        RaizModel raizModel = new RaizModel();
        Link cargosLink = WebMvcLinkBuilder.
        linkTo(WebMvcLinkBuilder.
        methodOn(CargoControleApi.class)
        .buscarTodos(null)).
        withRel("cargos").
        withType("GET");

        raizModel.add(cargosLink);
        return raizModel;
    }
}

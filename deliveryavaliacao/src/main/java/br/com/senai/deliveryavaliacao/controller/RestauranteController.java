package br.com.senai.deliveryavaliacao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.deliveryavaliacao.model.Restaurante;
import br.com.senai.deliveryavaliacao.service.RestauranteService;
import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/restaurantes")
public class RestauranteController {
    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService){
        this.restauranteService = restauranteService;
    }
    @PostMapping 
    public ResponseEntity<Restaurante> cadastrar(@Valid @RequestBody Restaurante restaurante){
        Restaurante restauranteCadastrado = restauranteService.cadastrar(restaurante);

        return ResponseEntity.status(HttpStatus.CREATED).body(restauranteCadastrado);
    }
    @GetMapping 
    public ResponseEntity<List<Restaurante>> listar(){
        List<Restaurante> restaurantes = restauranteService.listar();
        return ResponseEntity.ok(restaurantes);  
    }
    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarPorId(
            @PathVariable Integer id) {

        Optional<Restaurante> restaurante = restauranteService.buscarPorId(id);
        if (restaurante.isPresent()) {
            return ResponseEntity.ok(restaurante.get());
        }
        return ResponseEntity.notFound().build();
    }
     @PutMapping("/{id}") 
    public ResponseEntity<Restaurante> atualizar(
            @Valid
            @PathVariable Integer id,
            @RequestBody Restaurante novosDados) {
        Optional<Restaurante> restauranteModificado = restauranteService.atualizar(id, novosDados);

        if (restauranteModificado.isPresent()) {
            return ResponseEntity.ok(restauranteModificado.get());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Integer id) {

        boolean excluido = restauranteService.excluir(id);

        if (excluido) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}

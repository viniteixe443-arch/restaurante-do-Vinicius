package br.com.senai.deliveryavaliacao.service;

import org.springframework.stereotype.Service;

import br.com.senai.deliveryavaliacao.model.Restaurante;
import br.com.senai.deliveryavaliacao.repository.RestauranteRepository;

import java.util.List;
import java.util.Optional;

@Service 
public class RestauranteService {
    
    private final RestauranteRepository restauranteRepository;

    public  RestauranteService(RestauranteRepository restauranteRepository){
        this.restauranteRepository = restauranteRepository;
    }
    public Restaurante cadastrar(Restaurante restaurante){
        return restauranteRepository.save(restaurante);
    }
    public List<Restaurante> listar(){
        return restauranteRepository.findAll();
    }
    public Optional<Restaurante> buscarPorId(Integer Id){
        return restauranteRepository.findById(Id);
    }
    public Optional<Restaurante> atualizar(Integer Id, Restaurante novosDados){
        Optional<Restaurante> restauranteExistente = restauranteRepository.findById(Id);
        if (restauranteExistente.isEmpty()){
            return Optional.empty();
        }
        Restaurante restaurante = restauranteExistente.get();
        restaurante.setNome(novosDados.getNome());
        return Optional.of(restauranteRepository.save(restaurante));
    }
    public boolean excluir(Integer Id){
     if(!restauranteRepository.existsById(Id)){
        return false;
     }
         restauranteRepository.deleteById(Id);
        return true;
    }
}

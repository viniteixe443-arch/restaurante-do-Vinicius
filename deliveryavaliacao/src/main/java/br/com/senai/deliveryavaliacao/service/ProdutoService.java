package br.com.senai.deliveryavaliacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.senai.deliveryavaliacao.dto.ProdutoRequest;
import br.com.senai.deliveryavaliacao.model.Produto;
import br.com.senai.deliveryavaliacao.model.Restaurante;
import br.com.senai.deliveryavaliacao.repository.ProdutoRepository;
import br.com.senai.deliveryavaliacao.repository.RestauranteRepository;

@Service 
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final RestauranteRepository restauranteRepository;

    public ProdutoService(ProdutoRepository produtoRepository, RestauranteRepository restauranteRepository ){
        this.restauranteRepository = restauranteRepository;
        this.produtoRepository = produtoRepository;
    }
   public Optional<Produto> cadastrar(ProdutoRequest dadosProduto) {
    Optional<Restaurante> restauranteEncontrado = restauranteRepository.findById(dadosProduto.getRestauranteId());

    if (restauranteEncontrado.isEmpty()) {
        return Optional.empty();
    }
    Produto produto = new Produto();
    produto.setNome(dadosProduto.getNome());
    produto.setPreco(dadosProduto.getPreco());

    produto.setRestaurante(restauranteEncontrado.get());
    return Optional.of(produtoRepository.save(produto));
}
    public List<Produto> listar(){
        return produtoRepository.findAll();
    }
     public Optional<Produto> buscarPorId(Integer Id){
        return produtoRepository.findById(Id);
    }
    public Optional<Produto> atualizar(Integer id, ProdutoRequest novosDados) {

    Optional<Produto> produtoExistente =
            produtoRepository.findById(id);

    if (produtoExistente.isEmpty()) {
        return Optional.empty();
    }

    Optional<Restaurante> restauranteEncontrado =
        restauranteRepository.findById(novosDados.getRestauranteId());

    if (restauranteEncontrado.isEmpty()) {
        return Optional.empty();
    }

    Produto produto = produtoExistente.get();

    produto.setNome(novosDados.getNome());
    produto.setPreco(novosDados.getPreco());
    produto.setRestaurante(restauranteEncontrado.get());

    return Optional.of(produtoRepository.save(produto));
    }
    public boolean excluir(Integer Id){
            if(!produtoRepository.existsById(Id)){
                return false;
            }
                produtoRepository.deleteById(Id);
                return true;
        }
}
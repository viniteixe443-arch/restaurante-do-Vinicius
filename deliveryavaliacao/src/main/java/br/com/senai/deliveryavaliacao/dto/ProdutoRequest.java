package br.com.senai.deliveryavaliacao.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProdutoRequest {
    @NotBlank (message = "Informe o nome do produto")
    private String nome;

    @NotNull 
    @Positive (message = "O preço deve ser positivo")
    BigDecimal preco;

    @NotBlank (message = "O id do restaurante é obrigatório")
    @NotNull  
    private Integer restauranteId;

    public String getNome() {
    return nome;
}

public void setNome(String nome) {
    this.nome = nome;
}

public BigDecimal getPreco() {
    return preco;
}

public void setPreco(BigDecimal preco) {
    this.preco = preco;
    
}
public Integer getRestauranteId() {
    return restauranteId;
}
public void setRestauranteId(Integer restauranteId) {
    this.restauranteId = restauranteId;
}
}
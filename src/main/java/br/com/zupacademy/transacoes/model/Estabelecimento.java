package br.com.zupacademy.transacoes.model;

import br.com.zupacademy.transacoes.dto.response.EstabelecimentoResponse;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String cidade;
    @NotBlank
    private String endereco;

    @Deprecated
    public Estabelecimento() {
    }

    public Estabelecimento(String nome, String cidade, String endereco) {
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public Long getId() {
        return id;
    }

    public EstabelecimentoResponse toResponse() {
        return new EstabelecimentoResponse(this.nome, this.cidade, this.endereco);
    }
}

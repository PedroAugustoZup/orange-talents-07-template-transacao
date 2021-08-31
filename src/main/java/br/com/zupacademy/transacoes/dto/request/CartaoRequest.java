package br.com.zupacademy.transacoes.dto.request;

import br.com.zupacademy.transacoes.config.validators.ExistsValue;
import br.com.zupacademy.transacoes.model.Cartao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CartaoRequest {

    @NotBlank
    @ExistsValue(field = "id", table = Cartao.class)
    private String id;
    @NotBlank
    @Email
    @ExistsValue(field = "email", table = Cartao.class)
    private String email;

    public CartaoRequest(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Cartao toModel() {
        return new Cartao(this.id, this.email);
    }
}

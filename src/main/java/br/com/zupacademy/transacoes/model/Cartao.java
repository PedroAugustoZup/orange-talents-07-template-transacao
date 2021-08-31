package br.com.zupacademy.transacoes.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cartao {

    @Id
    private String id;
    @NotBlank
    @Email
    private String email;

    @OneToMany(mappedBy = "cartao", fetch = FetchType.LAZY)
    private List<Transacao> transacoes = new ArrayList<>();

    @Deprecated
    public Cartao() {
    }

    public Cartao(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }
}

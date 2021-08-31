package br.com.zupacademy.transacoes.dto.request;

import br.com.zupacademy.transacoes.model.Cartao;
import br.com.zupacademy.transacoes.model.Estabelecimento;
import br.com.zupacademy.transacoes.model.Transacao;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoRequest {
    private String id;
    private BigDecimal valor;
    private Estabelecimento estabelecimento;
    private Cartao cartao;
    private LocalDateTime efetivadaEm;

    @Deprecated
    public TransacaoRequest() {
    }

    public TransacaoRequest(String id, BigDecimal valor, Estabelecimento estabelecimento, Cartao cartao, LocalDateTime efetivadaEm) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public Transacao toModel(EntityManager manager) {
        manager.persist(this.estabelecimento);
        return new Transacao(this.id, this.valor,
                manager.find(Estabelecimento.class, this.estabelecimento.getId()),
                manager.find(Cartao.class, this.cartao.getId()), this.efetivadaEm);
    }
}

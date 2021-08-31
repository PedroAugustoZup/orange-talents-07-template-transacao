package br.com.zupacademy.transacoes.dto.response;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoResponse {
    @NotNull
    private BigDecimal valor;
    @NotNull
    private EstabelecimentoResponse estabelecimento;
    @NotNull
    private LocalDateTime efetivadaEm;

    public TransacaoResponse(BigDecimal valor, EstabelecimentoResponse estabelecimento,
                             LocalDateTime efetivadaEm) {
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.efetivadaEm = efetivadaEm;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public EstabelecimentoResponse getEstabelecimento() {
        return estabelecimento;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }
}

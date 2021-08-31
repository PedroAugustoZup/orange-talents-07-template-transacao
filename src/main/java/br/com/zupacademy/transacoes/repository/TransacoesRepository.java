package br.com.zupacademy.transacoes.repository;

import br.com.zupacademy.transacoes.model.Cartao;
import br.com.zupacademy.transacoes.model.Transacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacoesRepository extends JpaRepository<Transacao, String> {
    Page<Transacao> findByCartao(Cartao cartao, Pageable pageable);
}

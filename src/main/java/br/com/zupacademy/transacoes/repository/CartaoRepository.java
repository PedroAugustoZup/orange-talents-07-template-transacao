package br.com.zupacademy.transacoes.repository;

import br.com.zupacademy.transacoes.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, String> {
}

package br.com.zupacademy.transacoes.config.kafka;

import br.com.zupacademy.transacoes.dto.request.TransacaoRequest;
import br.com.zupacademy.transacoes.model.Transacao;
import br.com.zupacademy.transacoes.utils.TransactionalEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class ListenerDeTransacao {

    private TransactionalEvent transactionalEvent;

    @PersistenceContext
    private EntityManager manager;

    public ListenerDeTransacao(TransactionalEvent transactionalEvent) {
        this.transactionalEvent = transactionalEvent;
    }

    @KafkaListener(topics = "${spring.kafka.topic.transactions}", containerFactory = "kafkaListenerContainerFactory")
    public void ouvir(@Payload TransacaoRequest eventoDeTransacao) {

        transactionalEvent.execute(()->{
            Transacao transacao = eventoDeTransacao.toModel(manager);
            manager.persist(transacao);
        });
    }

}

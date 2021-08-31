package br.com.zupacademy.transacoes.utils;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionalEvent {

    @Transactional
    public void execute(Runnable e){
        e.run();
    }
}

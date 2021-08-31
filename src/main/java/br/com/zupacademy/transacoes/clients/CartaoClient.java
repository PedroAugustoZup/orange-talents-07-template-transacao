package br.com.zupacademy.transacoes.clients;

import br.com.zupacademy.transacoes.model.Cartao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cartoesClient",url = "${api.cartoes.url}")
public interface CartaoClient {

    @PostMapping("/cartoes")
    void salvarCartao(@RequestBody Cartao cartao);

    @DeleteMapping("/cartoes/{id}")
    void delete(@PathVariable("id") String id);
}

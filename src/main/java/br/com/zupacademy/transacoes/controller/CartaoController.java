package br.com.zupacademy.transacoes.controller;

import br.com.zupacademy.transacoes.clients.CartaoClient;
import br.com.zupacademy.transacoes.dto.request.CartaoRequest;
import br.com.zupacademy.transacoes.model.Cartao;
import br.com.zupacademy.transacoes.repository.CartaoRepository;
import br.com.zupacademy.transacoes.utils.TransactionalEvent;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/cartao")
public class CartaoController {
    private CartaoRepository cartaoRepository;
    private TransactionalEvent transactionalEvent;
    private CartaoClient cartaoClient;

    public CartaoController(TransactionalEvent transactionalEvent, CartaoClient cartaoClient, CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
        this.transactionalEvent = transactionalEvent;
        this.cartaoClient = cartaoClient;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid CartaoRequest request,
                                    UriComponentsBuilder uriBuilder){

        Cartao cartao = request.toModel();

        cartaoRepository.save(cartao);
        cartaoClient.salvarCartao(cartao);
        URI uri = uriBuilder.path("/cartao/{id}").buildAndExpand(cartao.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) throws NotFoundException {
        cartaoRepository.findById(id).orElseThrow(()->new NotFoundException("Cartao nao encontrado"));
        cartaoClient.delete(id);

        return ResponseEntity.noContent().build();
    }
}

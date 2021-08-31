package br.com.zupacademy.transacoes.controller;

import br.com.zupacademy.transacoes.clients.CartaoClient;
import br.com.zupacademy.transacoes.dto.request.CartaoRequest;
import br.com.zupacademy.transacoes.dto.response.TransacaoResponse;
import br.com.zupacademy.transacoes.model.Cartao;
import br.com.zupacademy.transacoes.model.Transacao;
import br.com.zupacademy.transacoes.repository.CartaoRepository;
import br.com.zupacademy.transacoes.repository.TransacoesRepository;
import br.com.zupacademy.transacoes.utils.TransactionalEvent;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cartao")
public class CartaoController {
    private CartaoRepository cartaoRepository;
    private TransactionalEvent transactionalEvent;
    private CartaoClient cartaoClient;
    private TransacoesRepository transacoesRepository;

    public CartaoController(CartaoRepository cartaoRepository, TransactionalEvent transactionalEvent,
                            CartaoClient cartaoClient, TransacoesRepository transacoesRepository) {
        this.cartaoRepository = cartaoRepository;
        this.transactionalEvent = transactionalEvent;
        this.cartaoClient = cartaoClient;
        this.transacoesRepository = transacoesRepository;
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

    @GetMapping("/{id}/transacoes")
    public ResponseEntity<?> listaUltimasTransacoes(@PathVariable("id") String id,
                                                    @PageableDefault(sort = "efetivadaEm",
                                                            direction = Sort.Direction.DESC,
                                                            page = 0,
                                                            size = 10) Pageable page) {
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if(cartao.isEmpty()) return ResponseEntity.notFound().build();

        Page<Transacao> transacoes = transacoesRepository.findByCartao(cartao.get(), page);
        List<TransacaoResponse> response = transacoes.stream().map(item->
                new TransacaoResponse(item.getValor(),
                        item.getEstabelecimento().toResponse(),
                        item.getEfetivadaEm()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}

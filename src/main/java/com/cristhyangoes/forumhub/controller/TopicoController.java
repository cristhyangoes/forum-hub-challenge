package com.cristhyangoes.forumhub.controller;

import com.cristhyangoes.forumhub.dto.DadosAtualizacaoTopico;
import com.cristhyangoes.forumhub.dto.DadosDetalhamentoTopico;
import com.cristhyangoes.forumhub.dto.DadosRegistrarTopico;
import com.cristhyangoes.forumhub.dto.DadosTopicoCriado;
import com.cristhyangoes.forumhub.model.Topico;
import com.cristhyangoes.forumhub.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosRegistrarTopico dados){
        var topicoCriado = new Topico(dados);
        repository.save(topicoCriado);

        var dto = new DadosTopicoCriado(topicoCriado);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosTopicoCriado>> listar10Topicos(@PageableDefault(size = 10) Pageable paginacao){
        var page = repository.findAllTopicos(paginacao).map(DadosTopicoCriado::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTopico> listarTopicoPorId(@PathVariable Long id){
        var topicoAtual = repository.findById(id);
        if(topicoAtual.isPresent()){
            var topicoPego = topicoAtual.get();
            return ResponseEntity.ok(new DadosDetalhamentoTopico(topicoPego));
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoTopico> atualizarTopico(@RequestBody @Valid DadosAtualizacaoTopico dados, @PathVariable Long id){
        var topicoBuscado = repository.findById(id);
        if(topicoBuscado.isPresent()){
            var topicoCapturado = topicoBuscado.get();
            topicoCapturado.atualizar(dados);
            return ResponseEntity.ok(new DadosDetalhamentoTopico(topicoCapturado));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarTopico(@PathVariable Long id){
        var topico = repository.findById(id);
        if(topico.isPresent()){
            var topicoAtual = topico.get();
            repository.deleteById(topicoAtual.getId());
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}

package com.cristhyangoes.forumhub.dto;

import com.cristhyangoes.forumhub.model.Topico;

import java.time.LocalDateTime;

public record DadosTopicoCriado(Long id, String titulo, String mensagem, LocalDateTime dataCriacao) {
    public DadosTopicoCriado(Topico topicoCriado) {
        this(topicoCriado.getId(), topicoCriado.getTitulo(), topicoCriado.getMensagem(), topicoCriado.getData_criacao());
    }

}

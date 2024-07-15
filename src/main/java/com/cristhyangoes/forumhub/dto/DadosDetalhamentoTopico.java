package com.cristhyangoes.forumhub.dto;

import com.cristhyangoes.forumhub.model.Status;
import com.cristhyangoes.forumhub.model.Topico;

import java.time.LocalDateTime;
import java.util.List;

public record DadosDetalhamentoTopico(
        String titulo,
        String mensagem,
        LocalDateTime data,
        Status status,
        String autor,
        String curso,
        List<String> resposta) {
    public DadosDetalhamentoTopico(Topico topicoPego) {
        this(topicoPego.getTitulo(), topicoPego.getMensagem(), topicoPego.getData_criacao(), topicoPego.getStatus(), topicoPego.getAutor(), topicoPego.getCurso(), topicoPego.getRespostas());
    }
}

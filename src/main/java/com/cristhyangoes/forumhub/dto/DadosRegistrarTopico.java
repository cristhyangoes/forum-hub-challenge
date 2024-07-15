package com.cristhyangoes.forumhub.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosRegistrarTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotBlank
        String nomeCurso,
        String autor) {
}

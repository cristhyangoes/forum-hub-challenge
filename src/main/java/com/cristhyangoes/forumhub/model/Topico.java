package com.cristhyangoes.forumhub.model;

import com.cristhyangoes.forumhub.dto.DadosAtualizacaoTopico;
import com.cristhyangoes.forumhub.dto.DadosRegistrarTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensagem;
    private LocalDateTime data_criacao;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String autor;
    private String curso;

    //Como o foco do challenge é somente nos tópicos, não irei trabalhar com as respostas dos tópicos.
    @Transient
    private List<String> respostas = new ArrayList<String>();

    public Topico(DadosRegistrarTopico dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.curso = dados.nomeCurso();
        this.data_criacao = LocalDateTime.now();
        if(dados.autor() != null){
            this.autor = dados.autor();
        }else{
            this.autor = "aluno";
        }
        if(this.respostas.isEmpty() && this.status == null) {
            this.status = Status.NAO_RESPONDIDO;
        }
    }

    public void atualizar(DadosAtualizacaoTopico dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.status = Status.RESPONDIDO;
    }


}

package com.cristhyangoes.forumhub.repository;

import com.cristhyangoes.forumhub.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @Query("SELECT t FROM Topico t WHERE t.id IS NOT NULL")
    Page<Topico> findAllTopicos(Pageable paginacao);
}

package com.francilio.alencar.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.francilio.alencar.teste.model.SessaoVotacao;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {

    boolean existsByIdAndPautaId(Long sessaoId, Long pautaId);
    
}

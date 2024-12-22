package com.francilio.alencar.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.francilio.alencar.teste.model.Votacao;

public interface VotacaoRepository extends JpaRepository<Votacao, Long> {

    int countByPautaIdAndSessaoVotacaoIdAndAssociadoId(Long sessaoId, Long pautaId, Long associadoId);
    
}

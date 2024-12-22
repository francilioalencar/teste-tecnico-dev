package com.francilio.alencar.teste.dto;

import java.time.LocalDateTime;

import com.francilio.alencar.teste.model.Pauta;
import com.francilio.alencar.teste.model.SessaoVotacao;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record VotacaoDto(

    Long id,

    Long sessaoVotacaoId,
    
    Long pautaId,
    
    Long associadoId,

    String voto

) {
    
}

package com.francilio.alencar.teste.dto;

import java.time.LocalDateTime;

import com.francilio.alencar.teste.model.Pauta;
import com.francilio.alencar.teste.model.SessaoVotacao;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record VotacaoDtoEntidade(

    Long id,

    SessaoVotacao sessaoVotacao,
    
    Pauta pauta,
    
    Long associadoId,

    String voto

) {
    
}

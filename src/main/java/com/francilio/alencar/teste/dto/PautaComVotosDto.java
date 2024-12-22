package com.francilio.alencar.teste.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.extern.java.Log;

@Builder

public record PautaComVotosDto(
    Long id,
    String titulo,
    String descricao,
    String status,
    LocalDate data,
    Long totalVotos,
    Long votosSim,
    Long VotosNao


) {
    
}

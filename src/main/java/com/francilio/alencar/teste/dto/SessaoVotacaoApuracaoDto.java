package com.francilio.alencar.teste.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;

@Builder

public record SessaoVotacaoApuracaoDto(

    Long id,
    LocalDate data,
    LocalDateTime inicioSessao,
    LocalDateTime fimSessao,
    LocalDateTime dataApuracao,
    Long totalVotos,
    Long votosSim,
    Long votosNao,
    PautaDto pauta

){

}

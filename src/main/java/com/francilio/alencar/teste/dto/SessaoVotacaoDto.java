package com.francilio.alencar.teste.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;


@Builder
public record SessaoVotacaoDto(
        Long id,
        LocalDate data,
        LocalDateTime inicioSessao,
        LocalDateTime fimSessao,
        Long  pautaId
){

}

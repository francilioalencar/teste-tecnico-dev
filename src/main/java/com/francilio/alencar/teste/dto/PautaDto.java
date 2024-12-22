package com.francilio.alencar.teste.dto;

import java.time.LocalDate;

import lombok.Builder;

@Builder

public record PautaDto(
    Long id,
    String titulo,
    String descricao,
    String status,
    LocalDate data


) {
    
}

package com.francilio.alencar.teste.dto;

import lombok.Builder;

@Builder

public record AssociadoDto(
        Long id,
        String nome,
        String cpf
) {
}

package com.francilio.alencar.teste.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder

public record AssociadoDto(
        Long id,
        String nome,
        @Size(min = 11, max = 11, message = "Um CPF possui 11 caracteres")
        String cpf
) {
}

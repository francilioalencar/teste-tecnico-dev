package com.francilio.alencar.teste.dto;

import java.time.LocalDate;

import lombok.Builder;

@Builder

public record PautaSelecao(
        String texto,
        String url

) {
}

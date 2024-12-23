package com.francilio.alencar.teste.dto;

import lombok.Builder;

@Builder

public record PautaSelecao(
        String texto,
        String url

){
}

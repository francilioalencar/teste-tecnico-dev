package com.francilio.alencar.teste.service;

import com.francilio.alencar.teste.dto.PautaComVotosDto;
import com.francilio.alencar.teste.dto.PautaDto;

public interface PautaInterfaceService {
    

    PautaDto buscaPautaPorId(Long id);
    PautaDto gravaPauta(PautaDto pautaDto);
    PautaComVotosDto buscaPautaComVotos(Long id);




}

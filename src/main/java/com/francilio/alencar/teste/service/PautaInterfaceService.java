package com.francilio.alencar.teste.service;

import java.util.List;

import com.francilio.alencar.teste.dto.PautaComVotosDto;
import com.francilio.alencar.teste.dto.PautaDto;
import com.francilio.alencar.teste.dto.PautaSelecao;

public interface PautaInterfaceService {
    


    List<PautaSelecao> listaTodasAsPautas();
    PautaDto buscaPautaPorId(Long id);
    PautaDto gravaPauta(PautaDto pautaDto);
    PautaComVotosDto buscaPautaComVotos(Long id);




}

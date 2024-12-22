package com.francilio.alencar.teste.service.implementa;

import org.springframework.stereotype.Service;

import com.francilio.alencar.teste.dto.VotacaoDto;
import com.francilio.alencar.teste.repository.AssociadoRepository;
import com.francilio.alencar.teste.service.VotacaoInterfaceServiceValidacao;

@Service

public class VotacaoServiceValidacaoAssociado implements VotacaoInterfaceServiceValidacao{

    private final AssociadoRepository associadoRepository;

    public VotacaoServiceValidacaoAssociado(AssociadoRepository associadoRepository){
        this.associadoRepository = associadoRepository;
    }

    @Override
    public void validaVotacao(VotacaoDto votacaoDto) {
        validaExistenciaAssociado(votacaoDto.associadoId());
    }


    void validaExistenciaAssociado(Long associadoId){

        if(!this.associadoRepository.existsById(associadoId)){
            throw new IllegalArgumentException("Associado não foi localizado");
        }

    }



    
}

package com.francilio.alencar.teste.service.implementa;

import org.springframework.stereotype.Service;

import com.francilio.alencar.teste.dto.VotacaoDto;
import com.francilio.alencar.teste.repository.VotacaoRepository;
import com.francilio.alencar.teste.service.VotacaoInterfaceServiceValidacao;

@Service

public class VotacaoServiceValidacaoVotacao implements VotacaoInterfaceServiceValidacao{

    private final VotacaoRepository votacaoRepository;

    public VotacaoServiceValidacaoVotacao(VotacaoRepository votacaoRepository){
        this.votacaoRepository = votacaoRepository;
    }

    @Override
    public void validaVotacao(VotacaoDto votacaoDto) {
        validaInexistenciaDeVotacao(votacaoDto.sessaoVotacaoId(), votacaoDto.pautaId(), votacaoDto.associadoId());
    }


    void validaInexistenciaDeVotacao(Long sessaoId, Long pautaId, Long associadoId){

        if(this.votacaoRepository.countByPautaIdAndSessaoVotacaoIdAndAssociadoId(sessaoId, pautaId, associadoId) > 0){
            throw new IllegalArgumentException("Ja existe registro de votacao para o Assoaciado nesssa pauta e sessão");
        }

    }



    
}

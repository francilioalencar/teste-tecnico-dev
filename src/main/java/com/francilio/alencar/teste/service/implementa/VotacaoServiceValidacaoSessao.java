package com.francilio.alencar.teste.service.implementa;

import org.springframework.stereotype.Service;

import com.francilio.alencar.teste.dto.VotacaoDto;
import com.francilio.alencar.teste.repository.SessaoVotacaoRepository;
import com.francilio.alencar.teste.repository.VotacaoRepository;
import com.francilio.alencar.teste.service.VotacaoInterfaceServiceValidacao;

@Service

public class VotacaoServiceValidacaoSessao implements VotacaoInterfaceServiceValidacao{

    private final SessaoVotacaoRepository sessaoVotacaoRepository;

    public VotacaoServiceValidacaoSessao(SessaoVotacaoRepository sessaoVotacaoRepository){
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
    }

    @Override
    public void validaVotacao(VotacaoDto votacaoDto) {
        validaSessao(votacaoDto.sessaoVotacaoId(), votacaoDto.pautaId());
    }


    void validaSessao(Long sessaoId, Long pautaId){
        if(!this.sessaoVotacaoRepository.existsByIdAndPautaId(sessaoId, pautaId)){
            throw new IllegalArgumentException("Não foi localizado a Sessão para essa pauta");
        }

    }



    
}

package com.francilio.alencar.teste.service.implementa;

import java.util.List;

import org.springframework.stereotype.Service;

import com.francilio.alencar.teste.dto.VotacaoDto;
import com.francilio.alencar.teste.model.Pauta;
import com.francilio.alencar.teste.model.SessaoVotacao;
import com.francilio.alencar.teste.model.Votacao;
import com.francilio.alencar.teste.repository.PautaRepository;
import com.francilio.alencar.teste.repository.SessaoVotacaoRepository;
import com.francilio.alencar.teste.repository.VotacaoRepository;
import com.francilio.alencar.teste.service.VotacaoInterfaceService;
import com.francilio.alencar.teste.service.VotacaoInterfaceServiceValidacao;

import io.swagger.v3.oas.annotations.servers.Server;

@Service
public class VotacaoService implements VotacaoInterfaceService {

    private final PautaRepository pautaRepository;
    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final VotacaoRepository votacaoRepository;

    private final List<VotacaoInterfaceServiceValidacao> validacoes;


    public VotacaoService( PautaRepository pautaRepository, SessaoVotacaoRepository sessaoVotacaoRepository, VotacaoRepository votacaoRepository , List<VotacaoInterfaceServiceValidacao> validacoes){
        this.pautaRepository    =   pautaRepository;
        this.sessaoVotacaoRepository    =   sessaoVotacaoRepository;
        this.votacaoRepository  =   votacaoRepository;
        this.validacoes = validacoes;

    }

    @Override
    public boolean gravarVotacao(VotacaoDto votacaoDto) {


        this.validacoes.forEach(valida->{
            valida.validaVotacao(votacaoDto);
        });

        Pauta pauta = this.pautaRepository.findById(votacaoDto.pautaId()).get();
        SessaoVotacao sessaoVotacao = this.sessaoVotacaoRepository.findById(votacaoDto.sessaoVotacaoId()).get();



        Votacao votacao = Votacao.builder()
                .sessaoVotacao(sessaoVotacao)
                .pauta(pauta)
                .associadoId(votacaoDto.associadoId())
                .voto(votacaoDto.voto())
                .build();

        this.votacaoRepository.save(votacao);

        return true;

    }

}

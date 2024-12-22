package com.francilio.alencar.teste.service.implementa;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.francilio.alencar.teste.dto.SessaoVotacaoDto;
import com.francilio.alencar.teste.model.Pauta;
import com.francilio.alencar.teste.model.SessaoVotacao;
import com.francilio.alencar.teste.repository.PautaRepository;
import com.francilio.alencar.teste.repository.SessaoVotacaoRepository;
import com.francilio.alencar.teste.service.SessaoVotacaoInterfaceService;

@Service
public class SessaoVotacaoService implements SessaoVotacaoInterfaceService {

    private final SessaoVotacaoRepository sessaoVotacaoRepository;

    private final PautaRepository   pautaRepository;

    @Value("${requisito.tempo.sessao.votacao}")
    private String tempoSessaoVotacao;


    public SessaoVotacaoService(SessaoVotacaoRepository sessaoVotacaoRepository, PautaRepository   pautaRepository) {
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
        this.pautaRepository    =   pautaRepository;

    }

    @Override
    public SessaoVotacaoDto gravarSessaoVotacao(SessaoVotacaoDto sessaoDto) {

        consisteSeHaSessaoAberta(sessaoDto.pautaId());


        Pauta pauta = this.pautaRepository.findById(sessaoDto.pautaId()).get();

        SessaoVotacao sessaoVotacao = SessaoVotacao.builder()
            .data(sessaoDto.data())
            .inicioSessao(LocalDateTime.now())
            .fimSessao( LocalDateTime.now().plusMinutes(Integer.parseInt(tempoSessaoVotacao)) )
            .pauta(pauta)
            .build();

        this.sessaoVotacaoRepository.save(sessaoVotacao);

        SessaoVotacaoDto sessaoVotacaoDto2 = SessaoVotacaoDto.builder()
        .id(sessaoVotacao.getId())
        .data(sessaoVotacao.getData())
        .inicioSessao( sessaoVotacao.getInicioSessao() )
        .fimSessao( sessaoVotacao.getFimSessao() )
        .pautaId(pauta.getId())
        .build();


        return sessaoVotacaoDto2;


        
    }

    void consisteSeHaSessaoAberta(Long pautaId){

        if(this.sessaoVotacaoRepository.existsById(pautaId)){
            throw new IllegalArgumentException("Já existe uma sessao aberta para pauta informada!");
        }


    }
}

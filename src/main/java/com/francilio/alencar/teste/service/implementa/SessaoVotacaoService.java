package com.francilio.alencar.teste.service.implementa;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.francilio.alencar.teste.dto.PautaDto;
import com.francilio.alencar.teste.dto.SessaoVotacaoApuracaoDto;
import com.francilio.alencar.teste.dto.SessaoVotacaoDto;
import com.francilio.alencar.teste.model.Pauta;
import com.francilio.alencar.teste.model.SessaoVotacao;
import com.francilio.alencar.teste.repository.PautaRepository;
import com.francilio.alencar.teste.repository.SessaoVotacaoRepository;
import com.francilio.alencar.teste.repository.VotacaoRepository;
import com.francilio.alencar.teste.service.SessaoVotacaoInterfaceService;

@Service
public class SessaoVotacaoService implements SessaoVotacaoInterfaceService {

    private final SessaoVotacaoRepository sessaoVotacaoRepository;

    private final PautaRepository   pautaRepository;

    private final VotacaoRepository votacaoRepository;

    @Value("${requisito.tempo.sessao.votacao}")
    private String tempoSessaoVotacao;


    public SessaoVotacaoService(SessaoVotacaoRepository sessaoVotacaoRepository, PautaRepository   pautaRepository, VotacaoRepository votacaoRepository) {
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
        this.pautaRepository    =   pautaRepository;
        this.votacaoRepository  =   votacaoRepository;

    }

    @Override
    public SessaoVotacaoDto gravarSessaoVotacao(SessaoVotacaoDto sessaoDto) {

        consisteSeHaSessaoAberta(sessaoDto.pautaId());


        Pauta pauta = this.pautaRepository.findById(sessaoDto.pautaId()).orElseThrow( NoSuchElementException::new );

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

    @Override
    public SessaoVotacaoApuracaoDto obterApuracaoSesaoVotacao(Long sessaoId) {

        SessaoVotacao sessaoVotacao = this.sessaoVotacaoRepository.findById(sessaoId).orElseThrow( NoSuchElementException::new );
        

        PautaDto pautaDto = PautaDto.builder()
            .id(sessaoVotacao.getPauta().getId())
            .titulo(sessaoVotacao.getPauta().getTitulo())
            .descricao(sessaoVotacao.getPauta().getDescricao())
            .data(sessaoVotacao.getPauta().getData())
            .build();



        List<Object[]> votacao = this.votacaoRepository.obterTotalVotacao(sessaoId);

        sessaoVotacao.setDataApuracao(LocalDateTime.now());
        sessaoVotacao.setTotalVotos( ((Number) votacao.get(0)[0]).longValue() );
        sessaoVotacao.setVotosSim( ((Number) votacao.get(0)[1]).longValue() );
        sessaoVotacao.setVotosNao( ((Number) votacao.get(0)[2]).longValue() );


        this.sessaoVotacaoRepository.save(sessaoVotacao);

        SessaoVotacaoApuracaoDto sessaoVotacaoApuracaoDto = SessaoVotacaoApuracaoDto.builder()
            .id(sessaoVotacao.getId())
            .data(sessaoVotacao.getData())
            .inicioSessao(sessaoVotacao.getInicioSessao())
            .fimSessao(sessaoVotacao.getFimSessao())
            .totalVotos(sessaoVotacao.getTotalVotos())
            .votosSim(sessaoVotacao.getVotosSim())
            .votosNao(sessaoVotacao.getVotosNao())
            .dataApuracao(sessaoVotacao.getDataApuracao())
            .pauta(pautaDto)
        .build();

        return sessaoVotacaoApuracaoDto;
    }
}

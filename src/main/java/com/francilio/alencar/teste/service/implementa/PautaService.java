package com.francilio.alencar.teste.service.implementa;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.francilio.alencar.teste.dto.PautaComVotosDto;
import com.francilio.alencar.teste.dto.PautaDto;
import com.francilio.alencar.teste.dto.PautaSelecao;
import com.francilio.alencar.teste.model.Pauta;
import com.francilio.alencar.teste.repository.PautaRepository;
import com.francilio.alencar.teste.service.PautaInterfaceService;

@Service

public class PautaService implements PautaInterfaceService {


    @Value("${app_regra_dominio_callback}")
    private String urlCallBack;

    private final PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }


    @Override
    public PautaDto buscaPautaPorId(Long id) {

        Pauta pauta = this.pautaRepository.findById(id).orElseThrow(NoSuchElementException::new);

        PautaDto pautaDto = PautaDto.builder()
                .id(pauta.getId())
                .titulo(pauta.getTitulo())
                .descricao(pauta.getDescricao())
                .status(pauta.getStatus())
                .data(pauta.getData())
                .build();

        return pautaDto;

    }

    @Override
    public PautaDto gravaPauta(PautaDto dto) {

        Pauta pauta = Pauta.builder()
                .id(dto.id())
                .titulo(dto.titulo())
                .descricao(dto.descricao())
                .status(dto.status())
                .data(dto.data())
                .build();

        this.pautaRepository.save(pauta);

        PautaDto pautaDto = PautaDto.builder()
                .id(pauta.getId())
                .titulo(pauta.getTitulo())
                .descricao(pauta.getDescricao())
                .status(pauta.getStatus())
                .data(pauta.getData())
                .build();

        return pautaDto;

    }

    @Override
    public PautaComVotosDto buscaPautaComVotos(Long id) {

        Pauta pauta = this.pautaRepository.findById(id).orElseThrow(NoSuchElementException::new);

        List<Object[]> votos = this.pautaRepository.obterPautaComVotos(pauta.getId());

        PautaComVotosDto pautaComVotosDto = PautaComVotosDto.builder()
                .id(pauta.getId())
                .titulo(pauta.getTitulo())
                .descricao(pauta.getDescricao())
                .data(pauta.getData())
                .totalVotos(((Number) votos.get(0)[0]).longValue())
                .votosSim(((Number) votos.get(0)[1]).longValue())
                .VotosNao(((Number) votos.get(0)[2]).longValue())
                .build();

        return pautaComVotosDto;

    }

    @Override
    public List<PautaSelecao> listaTodasAsPautas() {


        List<PautaSelecao> pautaSelecao = new ArrayList<PautaSelecao>();
        List<Pauta> pauta =  this.pautaRepository.findAll();//.stream().map(PautaSelecao:: new).toList();
        
        pauta.forEach(item->{
            pautaSelecao.add(
                PautaSelecao.builder()
                    .texto(item.getTitulo())
                    .url( new StringBuilder(urlCallBack).append("/votacao").toString())
                    .build());
        });

        return pautaSelecao;
    }

}

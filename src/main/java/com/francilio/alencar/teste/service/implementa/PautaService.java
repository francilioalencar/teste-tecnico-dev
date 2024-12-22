package com.francilio.alencar.teste.service.implementa;

import java.util.IllegalFormatException;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.francilio.alencar.teste.dto.PautaDto;
import com.francilio.alencar.teste.model.Pauta;
import com.francilio.alencar.teste.repository.PautaRepository;
import com.francilio.alencar.teste.service.PautaInterfaceService;

@Service

public class PautaService implements PautaInterfaceService {

    private final PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository){
        this.pautaRepository = pautaRepository;
    }

    @Override
    public PautaDto buscaPautaPorId(Long id) {
        
        Pauta pauta =  this.pautaRepository.findById(id).orElseThrow(NoSuchElementException::new); 

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
    
}

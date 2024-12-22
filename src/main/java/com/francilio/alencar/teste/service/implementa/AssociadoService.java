package com.francilio.alencar.teste.service.implementa;


import org.springframework.stereotype.Service;

import com.francilio.alencar.teste.dto.AssociadoDto;
import com.francilio.alencar.teste.model.Associado;
import com.francilio.alencar.teste.repository.AssociadoRepository;
import com.francilio.alencar.teste.service.AssociadoInterfaceService;

import java.util.NoSuchElementException;

@Service
public class AssociadoService implements AssociadoInterfaceService {



    private final AssociadoRepository associadoRepository;

    public  AssociadoService(AssociadoRepository associadoRepository){
        this.associadoRepository = associadoRepository;

    }

    @Override
    public AssociadoDto retornaAssociadoPorId(Long id) {
        
        Associado associado = this.associadoRepository.findById(id).orElseThrow(NoSuchElementException::new); 

        AssociadoDto associadoDto = AssociadoDto.builder()
            .id(associado.getId())
            .nome(associado.getNome())
            .cpf(associado.getCpf())
        .build();
        return associadoDto  ;
    }

    @Override
    public AssociadoDto cadastraAssociado(AssociadoDto associadoDtoP) {
        if(this.associadoRepository.existsByCpf(associadoDtoP.cpf())){
            throw new RuntimeException("Registro de associado encontrado com o CPF informado");
        }

        
        Associado associado = Associado.builder()
            .id(associadoDtoP.id())
            .nome(associadoDtoP.nome())
            .cpf(associadoDtoP.cpf())
        .build();

        this.associadoRepository.save(associado);


        AssociadoDto associadoDto = AssociadoDto.builder()
            .id(associado.getId())
            .nome(associado.getNome())
            .cpf(associado.getCpf())
        .build();
        
        return associadoDto;
    }
}

package com.francilio.alencar.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.shortThat;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.francilio.alencar.teste.dto.AssociadoDto;
import com.francilio.alencar.teste.model.Associado;
import com.francilio.alencar.teste.repository.AssociadoRepository;
import com.francilio.alencar.teste.service.implementa.AssociadoService;

@ExtendWith(MockitoExtension.class)
public class AssociadoServiceTeste {

    @InjectMocks
    private AssociadoService associadoService;

    @Mock
    private AssociadoRepository associadoRepository;

    @Mock
    private AssociadoDto associadoDto;

    @Captor
    private ArgumentCaptor<Associado> associado;

    @Test
    void deveriaSalvarAssociado() {

        //Arrang
        Associado associadoMock = Associado.builder()
                .id(1L)
                .cpf("99999999999")
                .nome("Teste")
                .build();

        when(associadoRepository.save(this.associado.capture())).thenReturn(associadoMock);

        
        //Act
        Associado associadoSave = associadoRepository.save(associadoMock);
        
       
        //Assert
        
         verify(associadoRepository, times(1) ).save(this.associado.capture());

         assertEquals("Teste", associadoSave.getNome());
    }
    @Test
    void deveriaBuscarPorId() {

        //Arrang
        Associado associadoMock = Associado.builder()
                .id(1L)
                .cpf("99999999999")
                .nome("Teste")
                .build();

        when(associadoRepository.findById(any(Long.class)) ).thenReturn( Optional.of( associadoMock ));

        
        //Act
        Associado associadoListado = associadoRepository.findById(1L).get();
        
       
        //Assert
        
         verify(associadoRepository, times(1) ).findById(any(Long.class));

         assertEquals("Teste", associadoListado.getNome());
    }

}

package com.francilio.alencar.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.shortThat;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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
import com.francilio.alencar.teste.model.Pauta;
import com.francilio.alencar.teste.repository.AssociadoRepository;
import com.francilio.alencar.teste.repository.PautaRepository;
import com.francilio.alencar.teste.service.implementa.AssociadoService;
import com.francilio.alencar.teste.service.implementa.PautaService;

@ExtendWith(MockitoExtension.class)
public class PautaServiceTeste {

    @InjectMocks
    private PautaService pautaService;

    @Mock
    private PautaRepository pautaRepository;

    
    @Captor
    private ArgumentCaptor<Pauta> pautaCaptor;

    @Test
    void deveriaSalvarPauta() {

        //Arrang
        Pauta pautaMock = Pauta.builder()
                .id(1L)
                .titulo("Teste pauta 1")
                .descricao("Teste")
                .data(LocalDate.now())
                .build();

        when(pautaRepository.save(this.pautaCaptor.capture())).thenReturn(pautaMock);

        
        //Act
        Pauta pautaSalva = pautaRepository.save(pautaMock);
        
       
        //Assert
        
         verify(pautaRepository, times(1) ).save(this.pautaCaptor.capture());

         assertEquals("Teste pauta 1", pautaSalva.getTitulo());
    }
    @Test
    void deveriaBuscarPautaPorId() {

        //Arrang
        Pauta pautaMock = Pauta.builder()
            .id(1L)
            .titulo("Teste pauta 1")
            .descricao("Teste")
            .data(LocalDate.now())
            .build();


        when(pautaRepository.findById(any(Long.class)) ).thenReturn( Optional.of( pautaMock ));

        
        //Act
        Pauta pautaListado = pautaRepository.findById(1L).get();
        
       
        //Assert
        
         verify(pautaRepository, times(1) ).findById(any(Long.class));

         assertEquals("Teste pauta 1", pautaListado.getTitulo());
    }

}

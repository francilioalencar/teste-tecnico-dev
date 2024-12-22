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
import java.time.LocalDateTime;
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
import com.francilio.alencar.teste.model.SessaoVotacao;
import com.francilio.alencar.teste.repository.AssociadoRepository;
import com.francilio.alencar.teste.repository.PautaRepository;
import com.francilio.alencar.teste.repository.SessaoVotacaoRepository;
import com.francilio.alencar.teste.service.implementa.AssociadoService;
import com.francilio.alencar.teste.service.implementa.PautaService;
import com.francilio.alencar.teste.service.implementa.SessaoVotacaoService;

@ExtendWith(MockitoExtension.class)
public class SessaoVotacaoServiceTeste {

    @InjectMocks
    private SessaoVotacaoService sessaoVotacaoService;

    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    
    @Captor
    private ArgumentCaptor<SessaoVotacao> sessaoVotacaoCaptor;

    @Test
    void deveriaSalvarSessaoVotacao() {

        //Arrang

        Pauta pautaMock = Pauta.builder()
                .id(1L)
                .titulo("Teste pauta 1")
                .descricao("Teste")
                .data(LocalDate.now())
                .build();

        SessaoVotacao sessaoVotacaoMock = SessaoVotacao.builder()
                .id(1L)
                .data(LocalDate.now())
                .inicioSessao(LocalDateTime.now())
                .fimSessao(LocalDateTime.now().plusMinutes(1))
                .pauta(pautaMock)
                .build();

        when(sessaoVotacaoRepository.save(this.sessaoVotacaoCaptor.capture())).thenReturn(sessaoVotacaoMock);

        
        //Act
        SessaoVotacao sessaoVotacaoSalva = sessaoVotacaoRepository.save(sessaoVotacaoMock);
        
       
        //Assert
        
         verify(sessaoVotacaoRepository, times(1) ).save(this.sessaoVotacaoCaptor.capture());

         assertEquals(1L, sessaoVotacaoSalva.getId());
    }
    
}

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
import com.francilio.alencar.teste.model.Votacao;
import com.francilio.alencar.teste.repository.AssociadoRepository;
import com.francilio.alencar.teste.repository.PautaRepository;
import com.francilio.alencar.teste.repository.SessaoVotacaoRepository;
import com.francilio.alencar.teste.repository.VotacaoRepository;
import com.francilio.alencar.teste.service.implementa.AssociadoService;
import com.francilio.alencar.teste.service.implementa.PautaService;
import com.francilio.alencar.teste.service.implementa.SessaoVotacaoService;
import com.francilio.alencar.teste.service.implementa.VotacaoService;

@ExtendWith(MockitoExtension.class)
public class VotacaoServiceTeste {

    @InjectMocks
    private VotacaoService votacaoService;

    @Mock
    private VotacaoRepository votacaoRepository;

    
    @Captor
    private ArgumentCaptor<Votacao> votacaoCaptor;

    @Test
    void deveriaSalvarVotacao() {

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



        Votacao votacaoMock = Votacao.builder()
                .id(1L)
                .associadoId(1L)
                .dataVoto(LocalDateTime.now())
                .pauta(null)
                .sessaoVotacao(sessaoVotacaoMock)
                .voto("SIM")
                .build();

        
        when(votacaoRepository.save(this.votacaoCaptor.capture())).thenReturn(votacaoMock);

        
        //Act
        Votacao votacaoSalva = votacaoRepository.save(votacaoMock);
        
       
        //Assert
        
         verify(votacaoRepository, times(1) ).save(this.votacaoCaptor.capture());

         assertEquals(1L, votacaoSalva.getId());
    }
    
}

package com.francilio.alencar.teste.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.francilio.alencar.teste.dto.PautaComVotosDto;
import com.francilio.alencar.teste.dto.PautaDto;
import com.francilio.alencar.teste.dto.PautaSelecao;
import com.francilio.alencar.teste.service.PautaInterfaceService;

@RestController
@RequestMapping("pauta")
public class PautaController {
    

    private final PautaInterfaceService pautaInterfaceService;

    public PautaController(PautaInterfaceService pautaInterfaceService){

        this.pautaInterfaceService  =   pautaInterfaceService;
    }


    @GetMapping
    ResponseEntity<List<PautaSelecao>> listaTodasAsPauta(){
        return ResponseEntity.ok( this.pautaInterfaceService.listaTodasAsPautas() );
    }

    @GetMapping("/{id}")
    ResponseEntity<PautaDto> buscaPautaPorId(@PathVariable Long id){
        return ResponseEntity.ok( this.pautaInterfaceService.buscaPautaPorId(id) );
    }

    @GetMapping("/votos/{id}")
    ResponseEntity<PautaComVotosDto> buscaPautaComVotosPorId(@PathVariable Long id){
        return ResponseEntity.ok( this.pautaInterfaceService.buscaPautaComVotos(id) );
    }

    @PostMapping
    ResponseEntity<PautaDto> gravaPauta(@RequestBody PautaDto pautaDto){
        return ResponseEntity.ok( this.pautaInterfaceService.gravaPauta(pautaDto) );
    }

}

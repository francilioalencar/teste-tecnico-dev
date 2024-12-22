package com.francilio.alencar.teste.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.francilio.alencar.teste.dto.PautaDto;
import com.francilio.alencar.teste.service.PautaInterfaceService;

@RestController
@RequestMapping("pauta")
public class PautaController {
    

    private final PautaInterfaceService pautaInterfaceService;

    public PautaController(PautaInterfaceService pautaInterfaceService){

        this.pautaInterfaceService  =   pautaInterfaceService;
    }


    @GetMapping("/{id}")
    ResponseEntity<PautaDto> buscaPautaPorId(@PathVariable Long id){
        return ResponseEntity.ok( this.pautaInterfaceService.buscaPautaPorId(id) );
    }

    @PostMapping
    ResponseEntity<PautaDto> gravaPauta(@RequestBody PautaDto pautaDto){
        return ResponseEntity.ok( this.pautaInterfaceService.gravaPauta(pautaDto) );
    }

}

package com.francilio.alencar.teste.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.francilio.alencar.teste.dto.AssociadoDto;
import com.francilio.alencar.teste.service.AssociadoInterfaceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/associado")

public class AssociadoController {


    private final AssociadoInterfaceService associadoInterfaceService;


    public AssociadoController(AssociadoInterfaceService associadoInterfaceService){
        this.associadoInterfaceService = associadoInterfaceService;
    }



    @GetMapping("{id}")
    ResponseEntity retornaAssociadoPorId(@PathVariable Long id){


        return ResponseEntity.ok().body(associadoInterfaceService.retornaAssociadoPorId(id));


    }


    @PostMapping
    ResponseEntity cadastraAssociado(@Valid @RequestBody AssociadoDto associadoDto){

        return ResponseEntity.ok().body( this.associadoInterfaceService.cadastraAssociado(associadoDto) );

    }



}

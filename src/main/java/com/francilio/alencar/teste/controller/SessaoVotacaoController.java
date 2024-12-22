package com.francilio.alencar.teste.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.francilio.alencar.teste.dto.SessaoVotacaoDto;
import com.francilio.alencar.teste.service.SessaoVotacaoInterfaceService;

@RestController
@RequestMapping("sessao")
public class SessaoVotacaoController {
    
    private final SessaoVotacaoInterfaceService sessaoVotacaoInterfaceService;

    public SessaoVotacaoController(SessaoVotacaoInterfaceService sessaoVotacaoInterfaceService){
        this.sessaoVotacaoInterfaceService = sessaoVotacaoInterfaceService;

    }


    @PostMapping
    ResponseEntity<SessaoVotacaoDto> cadastraSessaoVotacao(@RequestBody SessaoVotacaoDto sessaoVotacaoDto){

        return ResponseEntity.ok( this.sessaoVotacaoInterfaceService.gravarSessaoVotacao(sessaoVotacaoDto) );

    }



}

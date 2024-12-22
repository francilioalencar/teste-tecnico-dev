package com.francilio.alencar.teste.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.francilio.alencar.teste.dto.VotacaoDto;
import com.francilio.alencar.teste.service.VotacaoInterfaceService;

@RestController
@RequestMapping("votacao")
public class VotacaoController {

    private final VotacaoInterfaceService votacaoInterfaceService;

    public VotacaoController(VotacaoInterfaceService votacaoInterfaceService) {
        this.votacaoInterfaceService = votacaoInterfaceService;

    }

    @PostMapping

    ResponseEntity<String> gravarVotacao(@RequestBody VotacaoDto votacaoDto) {
        this.votacaoInterfaceService.gravarVotacao(votacaoDto);
        return ResponseEntity.ok("Votacao realizada com sucesso!");
    }

}

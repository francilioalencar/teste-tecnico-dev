package com.francilio.alencar.teste.service;

import com.francilio.alencar.teste.dto.SessaoVotacaoApuracaoDto;
import com.francilio.alencar.teste.dto.SessaoVotacaoApuracaoDtoPatch;
import com.francilio.alencar.teste.dto.SessaoVotacaoDto;

public interface SessaoVotacaoInterfaceService {

    SessaoVotacaoDto gravarSessaoVotacao(SessaoVotacaoDto sessaoVotacaoDto);

    SessaoVotacaoApuracaoDto obterApuracaoSesaoVotacao(Long sessaoId);

}

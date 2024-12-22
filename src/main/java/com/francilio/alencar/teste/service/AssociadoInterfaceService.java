package com.francilio.alencar.teste.service;

import com.francilio.alencar.teste.dto.AssociadoDto;

public interface AssociadoInterfaceService {

    AssociadoDto retornaAssociadoPorId(Long id);
    AssociadoDto cadastraAssociado(AssociadoDto associado);


}

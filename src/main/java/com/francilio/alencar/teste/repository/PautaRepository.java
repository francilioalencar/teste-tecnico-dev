package com.francilio.alencar.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.francilio.alencar.teste.model.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
    
}

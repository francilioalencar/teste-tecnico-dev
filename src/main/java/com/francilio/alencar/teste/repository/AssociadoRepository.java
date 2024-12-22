package com.francilio.alencar.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.francilio.alencar.teste.model.Associado;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {
    boolean existsByCpf(String cpf);
}

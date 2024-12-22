package com.francilio.alencar.teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.francilio.alencar.teste.model.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long> {

    Pauta findBySessaoVotacaoId(Long sessaoId);

    @Query(value = """          
    select ifnull(count(1),0) total,
    ifnull((select sum(1)  from votacao where pauta_id = :id and upper(voto)='SIM'), 0) total_sim,
    ifnull((select sum(1)  from votacao where pauta_id = :id and upper(voto)='NAO'), 0) total_nao
    from votacao where pauta_id = :id
            """, nativeQuery = true)
    List<Object[]> obterPautaComVotos(@Param("id") Long id);
    
}

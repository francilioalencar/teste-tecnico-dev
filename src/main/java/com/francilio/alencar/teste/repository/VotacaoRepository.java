package com.francilio.alencar.teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.francilio.alencar.teste.model.Votacao;

public interface VotacaoRepository extends JpaRepository<Votacao, Long> {

    int countByPautaIdAndSessaoVotacaoIdAndAssociadoId(Long sessaoId, Long pautaId, Long associadoId);
 
    
    @Query(value = """
            select 
                ifnull(count(1),0) total,
                ifnull((select sum(1)  from votacao where sessao_votacao_id = :idSessao and upper(voto)='SIM'), 0) total_sim,
                ifnull((select sum(1)  from votacao where sessao_votacao_id = :idSessao and upper(voto)='NAO'), 0) total_nao
                from votacao where sessao_votacao_id = :idSessao
            """, nativeQuery = true)
    List<Object[]> obterTotalVotacao(@Param("idSessao") Long idSessao);


}

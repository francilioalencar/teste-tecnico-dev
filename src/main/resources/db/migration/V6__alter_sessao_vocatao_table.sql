ALTER TABLE sessao_votacao 
ADD COLUMN data_apuracao DATETIME NULL AFTER pauta_id,
ADD COLUMN total_votos INT NULL AFTER data_apuracao,
ADD COLUMN votos_sim INT NULL AFTER total_votos,
ADD COLUMN votos_nao INT NULL AFTER votos_sim;

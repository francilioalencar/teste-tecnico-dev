CREATE TABLE sessao_votacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data DATE NULL,
    inicio_sessao DATETIME NULL,
    fim_sessao DATETIME NULL,
    pauta_id INT NOT NULL

);


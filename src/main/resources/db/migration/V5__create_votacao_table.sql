CREATE TABLE votacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sessao_votacao_id INT not null,
    pauta_id int not null,
    associado_id int not null,
    voto VARCHAR(3),
    data_voto DATETIME

);



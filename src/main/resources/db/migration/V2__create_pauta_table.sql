CREATE TABLE pauta (
    codigo INT(12) AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(250) NULL,
    descricao LONGTEXT NULL,
    status CHAR(1) NULL DEFAULT 'A',
    data DATE NULL 

);
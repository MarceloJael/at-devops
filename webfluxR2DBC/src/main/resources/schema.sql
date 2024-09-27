CREATE TABLE IF NOT EXISTS veiculo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    cor VARCHAR(50) NOT NULL,
    ano INT NOT NULL
);

INSERT INTO veiculo (marca, modelo, cor, ano) VALUES ('Volkswagen', 'Gol', 'Verde', 2000);
INSERT INTO veiculo (marca, modelo, cor, ano) VALUES ('Volkswagen', 'Fusca', 'Branco', 1985);
INSERT INTO veiculo (marca, modelo, cor, ano) VALUES ('Peugeot', '306', 'Prata', 2012);
INSERT INTO veiculo (marca, modelo, cor, ano) VALUES ('Fiat', 'Palio', 'Prata', 1999);
INSERT INTO veiculo (marca, modelo, cor, ano) VALUES ('Chevrolet', 'Onix Premier', 'Prata', 2022);
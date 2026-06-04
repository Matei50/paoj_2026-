DROP TABLE IF EXISTS audit_log;
DROP TABLE IF EXISTS Comenzi_Produse;
DROP TABLE IF EXISTS Comenzi;
DROP TABLE IF EXISTS Produse;
DROP TABLE IF EXISTS Restaurante;
DROP TABLE IF EXISTS Clienti;

CREATE TABLE Clienti (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nume VARCHAR(255) NOT NULL UNIQUE,
    parola VARCHAR(255) NOT NULL,
    adresa_oras VARCHAR(255),
    adresa_strada VARCHAR(255),
    adresa_numar INT
);

CREATE TABLE Restaurante (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nume VARCHAR(255) NOT NULL UNIQUE,
    program VARCHAR(100),
    categorie VARCHAR(50),
    rating DOUBLE
);

CREATE TABLE Produse (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nume VARCHAR(255) NOT NULL,
    categorie VARCHAR(100),
    pret DOUBLE NOT NULL,
    id_restaurant INT NOT NULL,
    FOREIGN KEY (id_restaurant) REFERENCES Restaurante(id) ON DELETE CASCADE
);

CREATE TABLE Comenzi (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_client INT NOT NULL,
    status VARCHAR(50),
    total DOUBLE,
    data_comanda TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_client) REFERENCES Clienti(id) ON DELETE CASCADE
);

CREATE TABLE Comenzi_Produse (
    id_comanda INT NOT NULL,
    id_produs INT NOT NULL,
    cantitate INT DEFAULT 1,
    PRIMARY KEY (id_comanda, id_produs),
    FOREIGN KEY (id_comanda) REFERENCES Comenzi(id) ON DELETE CASCADE,
    FOREIGN KEY (id_produs) REFERENCES Produse(id) ON DELETE CASCADE
);

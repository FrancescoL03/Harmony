--
-- DATABASE: *nome database*
--

DROP DATABASE PROGETTO;

CREATE DATABASE PROGETTO;

USE PROGETTO;

/*------ TABELLA UTENTE ------*/

create table PROGETTO.Utente(
	id INT UNSIGNED AUTO_INCREMENT,
    nome VARCHAR(30) NOT NULL,
    cognome VARCHAR(30) NOT NULL,
	email VARCHAR(50) NOT NULL,
    password VARCHAR(30) NOT NULL,
    dataDiNascita DATE,
    sesso char(1) COMMENT 'F->Femmina, M->Maschio, A->Altro',
    amministratore TINYINT(1) DEFAULT 0 COMMENT '1->Admin, 0 Altrimenti',
    
    PRIMARY KEY (id),
    UNIQUE KEY(email)
);

/*------ TABELLA CATEGORIA ------*/

create table PROGETTO.Categoria(
	id INT UNSIGNED AUTO_INCREMENT,
    nome VARCHAR(30) NOT NULL DEFAULT 'Altro',
    
    PRIMARY KEY (id)
);

/*------ TABELLA PRODOTTO ------*/

create table PROGETTO.Prodotto(
	id INT UNSIGNED AUTO_INCREMENT,
	nome VARCHAR(128) NOT NULL,
    prezzo BIGINT(20) NOT NULL,
	categoria INT UNSIGNED NOT NULL DEFAULT 0,
    descrizione TEXT,
    FULLTEXT KEY (`nome`,`descrizione`),
    
    CONSTRAINT FOREIGN KEY (categoria) REFERENCES Categoria(id) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (id)
);

/*------ TABELLA ORDINE------*/

create table PROGETTO.Ordine(
	id BIGINT UNSIGNED AUTO_INCREMENT,
    utente INT UNSIGNED,
    data DATE NOT NULL,
    prezzo_totale BIGINT(20) NOT NULL,
    indirizzo VARCHAR(100) NOT NULL,
    metodo_di_pagamento varchar(20) NOT NULL,
    numero_di_carta CHAR(16) NOT NULL,
    costo_spedizione BIGINT(20) NOT NULL,
    numero_di_telefono char(10) NOT NULL,
    
    FOREIGN KEY (utente) REFERENCES Utente(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    PRIMARY KEY (id)
);

/*------ TABELLA COMPOSIZIONE ------*/

create table PROGETTO.Composizione(
  prodotto INT UNSIGNED,
  ordine BIGINT UNSIGNED,
  quantita INT UNSIGNED NOT NULL,
  
  FOREIGN KEY (prodotto) REFERENCES Prodotto(id) ON DELETE NO ACTION ON UPDATE CASCADE,
  FOREIGN KEY (ordine) REFERENCES Ordine(id) ON DELETE CASCADE ON UPDATE CASCADE
);
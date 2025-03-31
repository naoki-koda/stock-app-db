-- MySQL dump 10.13  Distrib 5.1.51, for pc-linux-gnu (i686)
--
-- Host: 127.0.0.1    Database: world
-- ------------------------------------------------------
-- Server version       5.1.51-debug-log

Create Database stock;

Create Table stock.company(
    code  VARCHAR(10)  NOT NULL,
    name  VARCHAR(100)  NOT NULL,
    marketid     int,
	date  	    DATE   NOT NULL,
    PRIMARY KEY(code),
    UNIQUE KEY (code)
) Comment '東証上場企業テーブル';
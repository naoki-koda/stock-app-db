-- MySQL dump 10.13  Distrib 5.1.51, for pc-linux-gnu (i686)
--
-- Host: 127.0.0.1    Database: world
-- ------------------------------------------------------
-- Server version       5.1.51-debug-log

Create Database stock;

Create Table stock.company(
    code  VARCHAR(10)  NOT NULL,
    name  VARCHAR(10)  NOT NULL,
    marketid     int,
	date  	    DATE   NOT NULL,
    PRIMARY KEY(code),
    UNIQUE KEY (code)
) Comment '東証上場企業テーブル';

Create Table stock.daily_quotes(
    code  VARCHAR(10)  NOT NULL,
    date  DATE NOT NULL,
    open  DECIMAL(10.4)  NOT NULL,
    high  DECIMAL(10.4)  NOT NULL,
    low  DECIMAL(10.4)  NOT NULL,
    close  DECIMAL(10.4)  NOT NULL,
    upper_limit  VARCHAR(10)  NOT NULL,
    lower_limit  VARCHAR(10)  NOT NULL,
    volume DECIMAL(10.4)  NOT NULL,
    turnover_value DECIMAL(15.4)  NOT NULL,
    adjustment_factor DECIMAL(10.4)  NOT NULL,
    adjustment_Open DECIMAL(10.4)  NOT NULL,
    adjustment_High DECIMAL(10.4)  NOT NULL,
    adjustment_low DECIMAL(10.4)  NOT NULL,
    adjustment_Close DECIMAL(10.4)  NOT NULL,
    adjustment_volume DECIMAL(10.4)  NOT NULL
) Comment '株価データ(日次)';
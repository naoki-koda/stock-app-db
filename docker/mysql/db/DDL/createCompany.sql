Create Table stock.company(
    code      nVARCHAR(10)  NOT NULL,
    name      VARCHAR(100)  NOT NULL,
    marketid  int,
	date  	  DATE   NOT NULL,
    PRIMARY KEY(code),
    UNIQUE KEY (code)
) Comment 'ãêŠé‹Æƒe[ƒuƒ‹';

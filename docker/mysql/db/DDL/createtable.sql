Create Table stock.company(
    code  VARCHAR(10)  NOT NULL,
    name  VARCHAR(10)  NOT NULL,
    marketid     int,
	date  	    DATE   NOT NULL,
    PRIMARY KEY(code),
    UNIQUE KEY (code)
) Comment '株一覧情報';

CREATE TABLE company (
    code      VARCHAR(10) NOT NULL,
    name      VARCHAR(100) NOT NULL,
    marketid  INT,
    date      DATE NOT NULL,
    PRIMARY KEY(code),
    UNIQUE (code),
    CONSTRAINT fk_market
        FOREIGN KEY (marketid)
        REFERENCES market(id)
        ON DELETE SET NULL
);

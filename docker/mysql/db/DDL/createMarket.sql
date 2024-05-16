Create Table stock.marget(
    id  int  NOT NULL,
    name  VARCHAR(10)  NOT NULL,
    created_at_db DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    updated_at_db DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    PRIMARY KEY(id),
    UNIQUE KEY (id)
) Comment 'マーケット区分テーブル';


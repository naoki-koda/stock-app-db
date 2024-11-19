Create Table stock.market(
    id  int  NOT NULL,
    name  VARCHAR(64)  NOT NULL,
    created_at_db DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    updated_at_db DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    PRIMARY KEY(id),
    UNIQUE KEY (id)
) Comment 'マーケット区分管理テーブル';


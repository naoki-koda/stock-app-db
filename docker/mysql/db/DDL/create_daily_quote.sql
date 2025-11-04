CREATE TABLE stock.daily_quotes (
    date DATE NOT NULL,
    code VARCHAR(10) NOT NULL,
    open_price DECIMAL(10,2) NOT NULL,
    high_price DECIMAL(10,2) NOT NULL,
    low_price DECIMAL(10,2) NOT NULL,
    close_price DECIMAL(10,2) NOT NULL,
    upper_limit VARCHAR(10) NOT NULL,
    lower_limit VARCHAR(10) NOT NULL,
    volume DECIMAL(15,2) NOT NULL,
    turnover_value DECIMAL(20,2) NOT NULL,
    adjustment_factor DECIMAL(10,2) NOT NULL,
    adjustment_open DECIMAL(10,2) NOT NULL,
    adjustment_high DECIMAL(10,2) NOT NULL,
    adjustment_low DECIMAL(10,2) NOT NULL,
    adjustment_close DECIMAL(10,2) NOT NULL,
    adjustment_volume DECIMAL(15,2) NOT NULL,
    UNIQUE KEY uq_date_code (date, code)
)Comment '日足管理テーブル';
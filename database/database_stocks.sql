create database stocks_overflow;

use stocks_overflow;

create table stocks(
	symbol_id int primary key,
	symbol varchar(30),
	company_name varchar(100)
);

create table watchlists(
	watchlist_id int primary key,
	name varchar(100)
);

create table watchlist_stocks(
	watchlist_id int,
	symbol_id int,
	foreign key (watchlist_id) references watchlists(watchlist_id),
	foreign key (symbol_id) references stocks(symbol_id),
	primary key(watchlist_id, symbol_id)
);

create table portfolio(
	symbol_id int primary key,
	stock_quantity int,
	money_invested decimal(10,4),
	foreign key (symbol_id) references stocks(symbol_id)
);

create table stock_history(
	id int primary key,
	symbol_id int,
	period_number int,
	period_start_time time(2),
	period_end_time time(2),
	opening_price decimal(10,4),
	closing_price decimal(10,4),
	min_price decimal(10,4),
	max_price decimal(10,4),
	foreign key (symbol_id) references stocks(symbol_id)
);

create table order_history(
	order_id int primary key,
	time_ordered timestamp,
	time_completed timestamp,
	symbol_id int,
	order_type enum('LIMIT', 'MARKET', 'STOP'),
	stock_quantity int,
	transaction_amount decimal(10,4),
	is_buy bool,
	order_status enum('PENDING', 'EXECUTED', 'FAILED'),
	foreign key (symbol_id) references stocks(symbol_id)
);

CREATE TABLE user_account (     id INT PRIMARY KEY AUTO_INCREMENT,     full_name VARCHAR(100) NOT NULL,     email VARCHAR(100) UNIQUE NOT NULL,     phone_number VARCHAR(15) NOT NULL,     date_of_birth DATE NOT NULL,     address TEXT NOT NULL,     zip_code VARCHAR(10) NOT NULL,     state VARCHAR(50) NOT NULL,     demat_number VARCHAR(50) UNIQUE NOT NULL,     client_number VARCHAR(50) UNIQUE NOT NULL,     bank_account_number VARCHAR(30) NOT NULL,     ifsc_code VARCHAR(15) NOT NULL,     trading_account_money DECIMAL(15,2) DEFAULT 0.00,     stock_investments_money DECIMAL(15,2) DEFAULT 0.00 );

INSERT INTO user_account (     full_name,     email,     phone_number,     date_of_birth,     address,     zip_code,     state,     demat_number,     client_number,     bank_account_number,     ifsc_code,     trading_account_money,     stock_investments_money ) VALUES (     'Santhosh Kumar',     'santhosh.kumar@gmail.com',     '+91-9876543210',     '1985-07-15',     '12/3, Nehru Street, Muthialpet',     '605003',     'Pondicherry',     'DM123456789012',     'CL987654321098',     '1234567890123456',     'HDFC0001234',     0,     0 );

ALTER TABLE order_history 
MODIFY order_id INT NOT NULL AUTO_INCREMENT;

SHOW CREATE TABLE order_history;
ALTER TABLE order_history DROP FOREIGN KEY order_history_ibfk_1;

SELECT * FROM order_history;

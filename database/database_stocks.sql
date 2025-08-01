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
ALTER TABLE order_history 
MODIFY order_id INT NOT NULL AUTO_INCREMENT;

SHOW CREATE TABLE order_history;
ALTER TABLE order_history DROP FOREIGN KEY order_history_ibfk_1;

SELECT * FROM order_history;

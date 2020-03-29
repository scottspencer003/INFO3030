CREATE TABLE person 
(
ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
first_name VARCHAR(256) NOT NULL,
last_name VARCHAR(256) NOT NULL
);

create table quotes(
id INT NOT NULL AUTO_INCREMENT, 
symbol VARCHAR(4) NOT NULL, 
time DATETIME NOT NULL,
price DECIMAL NOT NULL, 
PRIMARY KEY ( id )
 );

CREATE TABLE UserStocks (
ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
person_id INT NOT NULL,
quotes_id INT NOT NULL,
FOREIGN KEY (person_id) REFERENCES person (ID), 
FOREIGN KEY (quotes_id) REFERENCES quotes (ID)
);


INSERT INTO stocks.quotes (symbol,time,price) VALUES ('TSLA','2004-08-19 00:00:01','314.00');
INSERT INTO quotes (symbol,time,price) VALUES ('GOOG','2004-08-19 00:00:01','85.00'); 
INSERT INTO quotes (symbol,time,price) VALUES ('GOOG','2015-02-03 00:00:01','527.35'); 
INSERT INTO quotes (symbol,time,price) VALUES ('APPL','2000-01-01 00:00:01','118.27'); 
INSERT INTO quotes (symbol,time,price) VALUES ('AMZN','2015-02-03 00:00:01','363.21');


INSERT INTO stocks.person (first_name,last_name) VALUES ('Kennedy', 'Spencer');


INSERT INTO stocks.UserStocks (ID, person_id, quotes_id) VALUES (1, 1, 2);



# library-management-system
帮学弟写的作业——但愿不会被他老师看见

## Change MYSQL password

ALTER USER 'root'@'localhost' IDENTIFIED BY 'yhq';

## Database initialization
CREATE DATABASE IF NOT EXISTS library;

CREATE TABLE IF NOT EXISTS user (
    username VARCHAR(20),
    password VARCHAR(20) NOT NULL,
    PRIMARY KEY(username)
    );

INSERT INTO user (username, password) SELECT "yanghongquan", "yhq"
WHERE NOT EXISTS (SELECT * FROM user WHERE username="yanghongquan");

CREATE TABLE IF NOT EXISTS book (
    id INTEGER,
    name VARCHAR(22),
    author VARCHAR(14),
    publisher VARCHAR(18),
    date DATE,
    price FLOAT,
    category VARCHAR(6),
    PRIMARY KEY(id)
);

INSERT INTO book VALUES(1, "Book1", "Alice", "A", now(), 18.27, "Roman");
INSERT INTO book VALUES(2, "Book2", "Bob", "B", now(), 25.49, "Roman");
INSERT INTO book VALUES(3, "Book3", "Charlie", "C", now(), 25.36, "Roman");
INSERT INTO book VALUES(4, "Book4", "David", "D", now(), 15.2, "Roman");
INSERT INTO book VALUES(5, "Book5", "Frank", "E", now(), 5.49, "Roman");

CREATE TABLE IF NOT EXISTS reader (
    id INTEGER,
    name VARCHAR(8),
    gender VARCHAR(2),
    student_number VARCHAR(6),
    PRIMARY KEY (id)
);

INSERT INTO reader VALUES(1, "Alice", "W", "611731");
INSERT INTO reader VALUES(2, "Bob", "M", "152133");

CREATE TABLE IF NOT EXISTS borrow (
    book_id INTEGER,
    reader_id INTEGER,
    borrow_date Date NOT NULL,
    PRIMARY KEY (reader_id, book_id),
    FOREIGN KEY (book_id) REFERENCES book (id),
    FOREIGN KEY (reader_id) REFERENCES reader (id)
);

INSERT INTO borrow VALUES(1, 1, now());
INSERT INTO borrow VALUES(2, 2, DATE("2016-6-12"));

WITH b_date AS (SELECT borrow_date FROM borrow WHERE book_id = 2 AND reader_id = 2)
SELECT DATE(now()) - SELECT * FROM b_date;

SELECT TIMESTAMPDIFF(DAY, '2009-07-29', DATE(now()));
SELECT TIMESTAMPDIFF(DAY, (SELECT borrow_date FROM borrow WHERE book_id = 2 AND reader_id = 2), DATE(now())) AS DIFF;

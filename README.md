# library-management-system

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

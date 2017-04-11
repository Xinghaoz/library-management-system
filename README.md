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

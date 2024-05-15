SELECT 1 + 65;

CREATE TABLE users (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(100),
                       email VARCHAR(150) NOT NULL UNIQUE,
                       password VARCHAR(255),
                       role VARCHAR(20)
);


INSERT INTO users(name, email, password, role) values ( 'test1', 'test1@gmail.com', 'test1', 'ADMIN');


CREATE TABLE projects(
                         id INTEGER PRIMARY KEY,
                         name VARCHAR(100),
                         description VARCHAR(500),
                         status VARCHAR(20),
                         startDate DATE,
                         endDate DATE,
                         userId INTEGER,
                         FOREIGN KEY (userId) REFERENCES users(id)
);
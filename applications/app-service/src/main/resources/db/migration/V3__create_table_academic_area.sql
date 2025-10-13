CREATE TABLE academic_area (
                               id BIGINT PRIMARY KEY AUTO_INCREMENT,
                               name VARCHAR(100) NOT NULL UNIQUE,
                               description VARCHAR(255),
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE academic_program (
                                  id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                  name VARCHAR(100) NOT NULL UNIQUE,
                                  description VARCHAR(255),
                                  faculty_id BIGINT NOT NULL,
                                  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  deleted BOOLEAN NOT NULL DEFAULT FALSE,
                                  CONSTRAINT fk_academic_program_faculty
                                      FOREIGN KEY (faculty_id) REFERENCES faculty(id)
);

CREATE TABLE members (
     id         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
     email      VARCHAR(255)    NOT NULL,
     password   VARCHAR(255)    NOT NULL,
     nickname   VARCHAR(255)    NOT NULL,
     PRIMARY KEY (id),
     UNIQUE KEY uk_members_email (email),
     UNIQUE KEY uk_members_nickname (nickname)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
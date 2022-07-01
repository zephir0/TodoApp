CREATE TABLE todoapp.application_user
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(50) NOT NULL,
    password   VARCHAR(200) NOT NULL
);

CREATE TABLE todoapp.user_role
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        varchar(30)  NOT NULL,
    description varchar(200) NOT NULL
);

CREATE TABLE todoapp.user_roles
(
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES application_user(id),
    FOREIGN KEY (role_id) REFERENCES user_role(id)
);
INSERT INTO todoapp.application_user (login, password)
VALUES
    -- superadmin@example.com / hard
    ('Jan', '{bcrypt}$2a$10$Ruu5GtmSVkfLeuGfz/wHUuzflCcMbwJHSBo/.Wui0EM0KIM52Gs2S'),
    -- john@example.com / dog.8
    ('John', '{MD5}{AlZCLSQMMNLBS5mEO0kSem9V3mxplC6cTjWy9Kj/Gxs=}d9007147eb3a5f727b2665d647d36e35'),
    -- java_lover@example.com / javaiscool
    ('Marek',
     '{argon2}$argon2id$v=19$m=4096,t=3,p=1$YBBBwx+kfrNgczYDcLlWYA$LEPgdtfskoobyFtUWTMejaE5SBRyieHYbiE5ZmFKE7I');

INSERT INTO todoapp.user_role (name, description)
VALUES ('ADMIN', 'Ma dostęp do wszystkiego'),
       ('USER', 'Dostęp tylko do odczytu');

INSERT INTO todoapp.user_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 2);
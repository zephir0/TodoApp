INSERT INTO todoapp.application_user (login, password)
VALUES
    -- superadmin@example.com / hard
    ('Jan', '{bcrypt}$10$Ruu5GtmSVkfLeuGfz/wHUuzflCcMbwJHSBo/.Wui0EM0KIM52Gs2S'),
    -- john@example.com / dog.8
    ('4344', 'f5afa'),
    -- java_lover@example.com / javaiscool
    ('M4arek',
     'tr54ata');

INSERT INTO todoapp.user_role (name, description)
VALUES ('ADMIN', 'Ma dostęp do wszystkiego'),
       ('USER', 'Dostęp tylko do odczytu');

INSERT INTO todoapp.user_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 2);
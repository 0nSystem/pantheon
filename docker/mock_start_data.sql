--Set schema

-- Insertar datos en la tabla "user"
INSERT INTO users."user" (name, surname, email, login, password, high_date)
VALUES
    ('John', 'Doe', 'john.doe@example.com', 'john_doe', 'hashed_password', CURRENT_TIMESTAMP),
    ('Jane', 'Smith', 'jane.smith@example.com', 'jane_smith', 'hashed_password', CURRENT_TIMESTAMP);



-- Datos para la tabla "application"
INSERT INTO applications.application (name, description, high_date, high_id_user)
VALUES
    ('App1', 'First application', '2024-02-05 14:00:00', 1),
    ('App2', 'Second application', '2024-02-05 15:30:00', 2),
    ('App3', 'Third application', '2024-02-05 16:45:00', 1);


-- Datos para la tabla "role"
-- Insertar el rol "authorized" para cada aplicación
INSERT INTO applications.role (id_application, name, description)
SELECT id_application, 'authorized', 'Authorized role for all applications'
FROM applications.application;

INSERT INTO applications.role (id_application, name, description)
VALUES
    (1, 'Admin', 'Administrator role for App1'),
    (2, 'User', 'Regular user role for App2'),
    (1, 'Editor', 'Content editor role for App1'),
    (3, 'Guest', 'Guest role for App3');



-- Datos para la tabla "permission"
INSERT INTO applications.permission (id_application, name, description)
VALUES
    (1, 'Read', 'Read access permission for App1'),
    (2, 'Write', 'Write access permission for App2'),
    (1, 'Delete', 'Delete access permission for App1'),
    (3, 'Execute', 'Execute access permission for App3');




-- Datos para la tabla "attribute"
INSERT INTO applications.attribute (id_application, name, description)
VALUES
    (1, 'Color', 'Color attribute for App1'),
    (2, 'Size', 'Size attribute for App2'),
    (1, 'Category', 'Category attribute for App1'),
    (3, 'Type', 'Type attribute for App3');



-- Datos para la tabla "user_attribute"
INSERT INTO users.user_attribute (id_user, id_attribute, attribute_value)
VALUES
    (1, 1, 'attribute1'),
    (1, 2, 'attribute2'),
    (2, 1, 'User'),
    (2, 2, 'Female');

-- Datos para la tabla "user_permission"
INSERT INTO users.user_permission (id_user, id_permission)
VALUES
    (1, 1),
    (1, 2),
    (2, 1);

-- Datos para la tabla "user_role"
INSERT INTO users.user_role (id_user, id_role)
VALUES
    (1, 1),
    (2, 2);

-- Obtener el identificador del rol "authorized"
WITH AuthorizedRole AS (
    SELECT id_role
    FROM applications.role
    WHERE name = 'authorized'
)
-- Insertar el rol "authorized" para los usuarios que no tienen ese rol
INSERT INTO users.user_role (id_user, id_role)
SELECT DISTINCT u.id_user, ar.id_role
FROM users."user" u
         JOIN users.user_role ur ON u.id_user = ur.id_user
         JOIN AuthorizedRole ar ON ur.id_role = ar.id_role
WHERE NOT EXISTS (
    SELECT 1
    FROM users.user_role ur2
    WHERE u.id_user = ur2.id_user
      AND ar.id_role = ur2.id_role
);


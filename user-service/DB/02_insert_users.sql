--QUERY PARA INSERTAR USUARIOS: 2 ADMINISTRADORES, 18 CLIENTES.

INSERT INTO ecommerce.usuarios (nombre, email, contrasenia, rol, autenticado, activo, fecha_creacion) VALUES
-- Administradores (2)
('Carlos Mendoza Ríos',      'carlos.mendoza@ecommerce.com',   '$2b$10$fzU5PQlwqr/EzqG097uJI.4s2miLiRKelyznyH3j8EPYcfDqxqB0K', 'ADMINISTRADOR', 1, 1, NOW()),
('Laura Villalobos Pérez',   'laura.villalobos@ecommerce.com', '$2b$10$fzU5PQlwqr/EzqG097uJI.4s2miLiRKelyznyH3j8EPYcfDqxqB0K', 'ADMINISTRADOR', 1, 1, NOW()),
-- Clientes (18)
('Daniela Hernández Cruz',   'daniela.hernandez@gmail.com',   '$2b$10$fzU5PQlwqr/EzqG097uJI.4s2miLiRKelyznyH3j8EPYcfDqxqB0K', 'CLIENTE', 1, 1, NOW()),
('Miguel Ángel Torres',      'miguel.torres@gmail.com',       '$2b$10$fzU5PQlwqr/EzqG097uJI.4s2miLiRKelyznyH3j8EPYcfDqxqB0K', 'CLIENTE', 1, 1, NOW()),
('Sofía Ramírez Lugo',       'sofia.ramirez@gmail.com',       '$2b$10$fzU5PQlwqr/EzqG097uJI.4s2miLiRKelyznyH3j8EPYcfDqxqB0K', 'CLIENTE', 1, 1, NOW()),
('Jorge Luis Castillo',      'jorge.castillo@gmail.com',      '$2b$10$fzU5PQlwqr/EzqG097uJI.4s2miLiRKelyznyH3j8EPYcfDqxqB0K', 'CLIENTE', 1, 1, NOW()),
('Valeria Gómez Salinas',    'valeria.gomez@gmail.com',       '$2b$10$fzU5PQlwqr/EzqG097uJI.4s2miLiRKelyznyH3j8EPYcfDqxqB0K', 'CLIENTE', 1, 1, NOW()),
('Andrés Flores Núñez',      'andres.flores@gmail.com',       '$2b$10$fzU5PQlwqr/EzqG097uJI.4s2miLiRKelyznyH3j8EPYcfDqxqB0K', 'CLIENTE', 1, 1, NOW()),
('Mariana López Ortega',     'mariana.lopez@gmail.com',       '$2b$10$fzU5PQlwqr/EzqG097uJI.4s2miLiRKelyznyH3j8EPYcfDqxqB0K', 'CLIENTE', 1, 1, NOW()),
('Ricardo Sánchez Vega',     'ricardo.sanchez@gmail.com',     '$2b$10$fzU5PQlwqr/EzqG097uJI.4s2miLiRKelyznyH3j8EPYcfDqxqB0K', 'CLIENTE', 1, 1, NOW()),
('Fernanda Aguilar Mora',    'fernanda.aguilar@gmail.com',    '$2b$10$fzU5PQlwqr/EzqG097uJI.4s2miLiRKelyznyH3j8EPYcfDqxqB0K', 'CLIENTE', 1, 1, NOW()),
('Emilio Navarro Díaz',      'emilio.navarro@gmail.com',      '$2b$10$fzU5PQlwqr/EzqG097uJI.4s2miLiRKelyznyH3j8EPYcfDqxqB0K', 'CLIENTE', 1, 1, NOW()),
('Paola Jiménez Reyes',      'paola.jimenez@gmail.com',       '$2b$10$fzU5PQlwqr/EzqG097uJI.4s2miLiRKelyznyH3j8EPYcfDqxqB0K', 'CLIENTE', 1, 1, NOW()),
('Héctor Morales Ibarra',    'hector.morales@gmail.com',      '$2b$10$fzU5PQlwqr/EzqG097uJI.4s2miLiRKelyznyH3j8EPYcfDqxqB0K', 'CLIENTE', 1, 1, NOW()),
('Camila Rojas Espinoza',    'camila.rojas@gmail.com',        '$2b$10$fzU5PQlwqr/EzqG097uJI.4s2miLiRKelyznyH3j8EPYcfDqxqB0K', 'CLIENTE', 1, 1, NOW()),
('Diego Vázquez Herrera',    'diego.vazquez@gmail.com',       '$2b$10$fzU5PQlwqr/EzqG097uJI.4s2miLiRKelyznyH3j8EPYcfDqxqB0K', 'CLIENTE', 1, 1, NOW()),
('Ximena Cabrera Solís',     'ximena.cabrera@gmail.com',      '$2b$10$fzU5PQlwqr/EzqG097uJI.4s2miLiRKelyznyH3j8EPYcfDqxqB0K', 'CLIENTE', 1, 1, NOW()),
('Luis Fernando Paredes',    'luis.paredes@gmail.com',        '$2b$10$fzU5PQlwqr/EzqG097uJI.4s2miLiRKelyznyH3j8EPYcfDqxqB0K', 'CLIENTE', 1, 1, NOW()),
('Renata Delgado Campos',    'renata.delgado@gmail.com',      '$2b$10$fzU5PQlwqr/EzqG097uJI.4s2miLiRKelyznyH3j8EPYcfDqxqB0K', 'CLIENTE', 1, 1, NOW()),
('Iván Maldonado Fuentes',   'ivan.maldonado@gmail.com',      '$2b$10$fzU5PQlwqr/EzqG097uJI.4s2miLiRKelyznyH3j8EPYcfDqxqB0K', 'CLIENTE', 1, 1, NOW());
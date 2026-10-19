INSERT INTO ecommerce.users
    (name, paternal_lastname, maternal_lastname, email, password, role, is_active, created_at)
VALUES
-- Administradores (2)
('Carlos', 'Mendoza', 'Ríos', 'carlos.mendoza@ecommerce.com', '$2y$10$vYdhEN.jDoiejGCxWDqxKuXmF3wE9xwQ8G72pgVQSGk9sYZrNdZ.q', 'ADMINISTRADOR', 1, NOW()),
('Laura', 'Villalobos', 'Pérez', 'laura.villalobos@ecommerce.com', '$2y$10$vYdhEN.jDoiejGCxWDqxKuXmF3wE9xwQ8G72pgVQSGk9sYZrNdZ.q', 'ADMINISTRADOR', 1, NOW()),

-- Clientes (18)
('Daniela', 'Hernández', 'Cruz', 'daniela.hernandez@gmail.com', '$2y$10$vYdhEN.jDoiejGCxWDqxKuXmF3wE9xwQ8G72pgVQSGk9sYZrNdZ.q', 'CLIENTE', 1, NOW()),
('Miguel Ángel', 'Torres', 'Reyes', 'miguel.torres@gmail.com', '$2y$10$vYdhEN.jDoiejGCxWDqxKuXmF3wE9xwQ8G72pgVQSGk9sYZrNdZ.q', 'CLIENTE', 1, NOW()),
('Sofía', 'Ramírez', 'Lugo', 'sofia.ramirez@gmail.com', '$2y$10$vYdhEN.jDoiejGCxWDqxKuXmF3wE9xwQ8G72pgVQSGk9sYZrNdZ.q', 'CLIENTE', 1, NOW()),
('Jorge Luis', 'Castillo', 'Zuñiga', 'jorge.castillo@gmail.com', '$2y$10$vYdhEN.jDoiejGCxWDqxKuXmF3wE9xwQ8G72pgVQSGk9sYZrNdZ.q', 'CLIENTE', 1, NOW()),
('Valeria', 'Gómez', 'Salinas', 'valeria.gomez@gmail.com', '$2y$10$vYdhEN.jDoiejGCxWDqxKuXmF3wE9xwQ8G72pgVQSGk9sYZrNdZ.q', 'CLIENTE', 1, NOW()),
('Andrés', 'Flores', 'Núñez', 'andres.flores@gmail.com', '$2y$10$vYdhEN.jDoiejGCxWDqxKuXmF3wE9xwQ8G72pgVQSGk9sYZrNdZ.q', 'CLIENTE', 1, NOW()),
('Mariana', 'López', 'Ortega', 'mariana.lopez@gmail.com', '$2y$10$vYdhEN.jDoiejGCxWDqxKuXmF3wE9xwQ8G72pgVQSGk9sYZrNdZ.q', 'CLIENTE', 1, NOW()),
('Ricardo', 'Sánchez', 'Vega', 'ricardo.sanchez@gmail.com', '$2y$10$vYdhEN.jDoiejGCxWDqxKuXmF3wE9xwQ8G72pgVQSGk9sYZrNdZ.q', 'CLIENTE', 1, NOW()),
('Fernanda', 'Aguilar', 'Mora', 'fernanda.aguilar@gmail.com', '$2y$10$vYdhEN.jDoiejGCxWDqxKuXmF3wE9xwQ8G72pgVQSGk9sYZrNdZ.q', 'CLIENTE', 1, NOW()),
('Emilio', 'Navarro', 'Díaz', 'emilio.navarro@gmail.com', '$2y$10$vYdhEN.jDoiejGCxWDqxKuXmF3wE9xwQ8G72pgVQSGk9sYZrNdZ.q', 'CLIENTE', 1, NOW()),
('Paola', 'Jiménez', 'Reyes', 'paola.jimenez@gmail.com', '$2y$10$vYdhEN.jDoiejGCxWDqxKuXmF3wE9xwQ8G72pgVQSGk9sYZrNdZ.q', 'CLIENTE', 1, NOW()),
('Héctor', 'Morales', 'Ibarra', 'hector.morales@gmail.com', '$2y$10$vYdhEN.jDoiejGCxWDqxKuXmF3wE9xwQ8G72pgVQSGk9sYZrNdZ.q', 'CLIENTE', 1, NOW()),
('Camila', 'Rojas', 'Espinoza', 'camila.rojas@gmail.com', '$2y$10$vYdhEN.jDoiejGCxWDqxKuXmF3wE9xwQ8G72pgVQSGk9sYZrNdZ.q', 'CLIENTE', 1, NOW()),
('Diego', 'Vázquez', 'Herrera', 'diego.vazquez@gmail.com', '$2y$10$vYdhEN.jDoiejGCxWDqxKuXmF3wE9xwQ8G72pgVQSGk9sYZrNdZ.q', 'CLIENTE', 1, NOW()),
('Ximena', 'Cabrera', 'Solís', 'ximena.cabrera@gmail.com', '$2y$10$vYdhEN.jDoiejGCxWDqxKuXmF3wE9xwQ8G72pgVQSGk9sYZrNdZ.q', 'CLIENTE', 1, NOW()),
('Luis Fernando', 'Paredes', 'Pavon', 'luis.paredes@gmail.com', '$2y$10$vYdhEN.jDoiejGCxWDqxKuXmF3wE9xwQ8G72pgVQSGk9sYZrNdZ.q', 'CLIENTE', 1, NOW()),
('Renata', 'Delgado', 'Campos', 'renata.delgado@gmail.com', '$2y$10$vYdhEN.jDoiejGCxWDqxKuXmF3wE9xwQ8G72pgVQSGk9sYZrNdZ.q', 'CLIENTE', 1, NOW()),
('Iván', 'Maldonado', 'Fuentes', 'ivan.maldonado@gmail.com', '$2y$10$vYdhEN.jDoiejGCxWDqxKuXmF3wE9xwQ8G72pgVQSGk9sYZrNdZ.q', 'CLIENTE', 1, NOW());
INSERT INTO ecommerce.users (user_identifier, name, paternal_lastname, maternal_lastname, email, password, role, is_active, failed_login_attempts, locked_until)
VALUES
--Administrador(4)
('ADM-001-A', 'Ana', 'García', 'López', 'ana.garcia@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ADMINISTRADOR', TRUE, 0, NULL),
('ADM-002-A', 'Carlos', 'Hernández', 'Martínez', 'carlos.hernandez@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ADMINISTRADOR', TRUE, 0, NULL),
('ADM-003-A', 'María', 'Rodríguez', 'Sánchez', 'maria.rodriguez@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ADMINISTRADOR', TRUE, 0, NULL),
('ADM-004-A', 'José', 'Ramírez', 'Torres', 'jose.ramirez@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ADMINISTRADOR', TRUE, 0, NULL),
--Usuario(16)
('USR-005-I', 'Laura', 'Flores', 'Morales', 'laura.flores@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USUARIO', TRUE, 0, NULL),
('USR-006-I', 'Miguel', 'Vargas', 'Castro', 'miguel.vargas@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USUARIO', TRUE, 0, NULL),
('USR-007-I', 'Sofía', 'Navarro', 'Ortiz', 'sofia.navarro@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USUARIO', TRUE, 0, NULL),
('USR-008-I', 'Daniel', 'Mendoza', 'Ruiz', 'daniel.mendoza@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USUARIO', TRUE, 0, NULL),
('USR-009-I', 'Valentina', 'Guerrero', 'Reyes', 'valentina.guerrero@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USUARIO', TRUE, 0, NULL),
('USR-010-I', 'Alejandro', 'Jiménez', 'Cruz', 'alejandro.jimenez@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USUARIO', TRUE, 0, NULL),
('USR-011-I', 'Camila', 'Rojas', 'Silva', 'camila.rojas@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USUARIO', TRUE, 0, NULL),
('USR-012-I', 'Fernando', 'Pérez', 'Gómez', 'fernando.perez@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USUARIO', TRUE, 0, NULL),
('USR-013-I', 'Daniela', 'Aguilar', 'Romero', 'daniela.aguilar@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USUARIO', TRUE, 0, NULL),
('USR-014-I', 'Ricardo', 'Medina', 'Vega', 'ricardo.medina@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USUARIO', TRUE, 0, NULL),
('USR-015-I', 'Paola', 'Campos', 'Herrera', 'paola.campos@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USUARIO', TRUE, 0, NULL),
('USR-016-I', 'Andrés', 'Salazar', 'Núñez', 'andres.salazar@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USUARIO', TRUE, 0, NULL),
('USR-017-I', 'Gabriela', 'Delgado', 'Molina', 'gabriela.delgado@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USUARIO', TRUE, 0, NULL),
('USR-018-I', 'Javier', 'Cortés', 'Peña', 'javier.cortes@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USUARIO', TRUE, 0, NULL),
('USR-019-I', 'Natalia', 'Soto', 'Fuentes', 'natalia.soto@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USUARIO', TRUE, 0, NULL),
('USR-020-I', 'Roberto', 'Miranda', 'Acosta', 'roberto.miranda@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USUARIO', TRUE, 0, NULL);
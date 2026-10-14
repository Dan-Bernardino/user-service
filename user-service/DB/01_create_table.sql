CREATE DATABASE IF NOT EXISTS ecommerce;

CREATE TABLE IF NOT EXISTS ecommerce.usuarios (
                                                  id_usuarios BIGINT NOT NULL AUTO_INCREMENT,
                                                  nombre VARCHAR(100) NOT NULL,
                                                  email VARCHAR(150) NOT NULL,
                                                  contrasenia VARCHAR(255) NOT NULL,
                                                  rol VARCHAR(45) NOT NULL DEFAULT 'CLIENTE',
                                                  autenticado BOOLEAN NOT NULL DEFAULT FALSE,
                                                  activo BOOLEAN NOT NULL DEFAULT TRUE,
                                                  fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                                  PRIMARY KEY (id_usuarios),
                                                  CONSTRAINT uk_usuarios_email UNIQUE (email)
) ENGINE = InnoDB;
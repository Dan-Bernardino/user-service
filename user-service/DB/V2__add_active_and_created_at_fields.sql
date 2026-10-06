CREATE TABLE IF NOT EXISTS `ecommcerce`.`usuarios` (
    `id_usuarios` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(100) NOT NULL,
    `email` VARCHAR(150) NOT NULL,
    `contrasenia` VARCHAR(255) NOT NULL,
    `rol` VARCHAR(45) NOT NULL DEFAULT 'CLIENTE',
    `autenticado` TINYINT NOT NULL,
    `activo` TINYINT NOT NULL,
    `fecha_creacion` DATETIME NOT NULL,
    PRIMARY KEY (`id_usuarios`),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;
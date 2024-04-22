CREATE TABLE IF NOT EXISTS odontologos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    apellido VARCHAR(255),
    nombre VARCHAR(255),
    matricula VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS pacientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    apellido VARCHAR(255),
    domicilio VARCHAR(255),
    dni VARCHAR(255) UNIQUE,
    fecha_alta DATE
);

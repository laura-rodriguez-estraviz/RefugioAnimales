--Insertar valores en la tabla usuario
INSERT INTO usuario (nombre, dni, direccion, telefono, email, clave) VALUES
    ('Juan Martínez', '12345678E', 'Calle Principal 123', 623456789, 'juan@example.com','password123'),
    ('María García', '98765432D', 'Avenida Central 456', 987654321, 'maria@example.com','maria123'),
    ('Pedro Rodríguez', '45612378C', 'Plaza Mayor 789', 656123789, 'pedro@example.com','pedro456'),
    ('Ana López', '28945612A', 'Carretera Secundaria 321', 689456123, 'ana@example.com','ana789'),
    ('Elena García', '78965412Q', 'Calle Mayor 123', 611456789, 'elena@example.com','elena_123'),
    ('Luisa Pérez', '32165498B', 'Paseo del Parque 654', 621654987, 'luisa@example.com','luisaPerez');
   
   

 -- Insertar valores en la tabla voluntario
INSERT INTO voluntario (dni, horaEntrada, horaSalida)
VALUES
    ('12345678E', '08:00:00', '16:00:00'),
    ('98765432D', '09:00:00', '17:00:00');

-- Insertar valores en la tabla cliente
INSERT INTO cliente (dni, puntuacion)
VALUES
    ('45612378C', 5),
    ('28945612A', 4);

-- Insertar valores en la tabla personal
INSERT INTO personal (dni, cargo, horaEntrada, horaSalida, cuentaBancaria)
VALUES
    ('78965412Q', 'Recepcionista', '08:30:00', '17:30:00', 'ES0123456789012345678901'),
    ('32165498B', 'Contador', '09:00:00', '18:00:00', 'ES9876543210987654321098');
    
-- Insertar valores en la tabla sucursal
INSERT INTO sucursal (numSucursal, municipio) VALUES
    (1, 'Madrid'),
    (2, 'Barcelona'),
    (3, 'Valencia'),
    (4, 'Sevilla'),
    (5, 'Bilbao');
   
   
   
   
-- Generar valores para la tabla tarea
WITH nuevas_tareas AS (
    SELECT descripcion, dniPersonal
    FROM (
        VALUES 
            ('Limpiar jaulas', '78965412Q'),
            ('Contabilidad mensual', '32165498B'),
            ('Alimentar a los animales', '78965412Q'),
            ('Atender llamadas de adopción', '78965412Q'),
            ('Control de inventario de alimentos', '32165498B')
    ) AS t(descripcion, dniPersonal)
)
-- Insertar valores en la tabla tarea
INSERT INTO tarea (descripcion, dniPersonal)
SELECT descripcion, dniPersonal
FROM nuevas_tareas;

-- Insertar valores en la tabla realizarTarea
INSERT INTO realizarTarea (fechaInscripcion, fechaFin, dniVoluntario, idTarea)
SELECT '2024-04-14', '2024-04-16', '12345678E', id
FROM tarea
WHERE descripcion = 'Limpiar jaulas';

INSERT INTO realizarTarea (fechaInscripcion, fechaFin, dniVoluntario, idTarea)
SELECT '2024-04-15', '2024-04-17', '12345678E', id
FROM tarea
WHERE descripcion = 'Alimentar a los animales';

INSERT INTO realizarTarea (fechaInscripcion, fechaFin, dniVoluntario, idTarea)
SELECT '2024-04-16', '2024-04-18', '98765432D', id
FROM tarea
WHERE descripcion = 'Contabilidad mensual';

INSERT INTO realizarTarea (fechaInscripcion, fechaFin, dniVoluntario, idTarea)
SELECT '2024-04-17', '2024-04-19', '98765432D', id
FROM tarea
WHERE descripcion = 'Atender llamadas de adopción';




-- Insertar valores en la tabla evento (Refugio de Animales)
INSERT INTO evento (nombre, descripcion, fecha, numSucursal)
VALUES
    ('Jornada de Adopción de Mascotas', '¡Únete a nosotros para encontrar a tu nuevo mejor amigo! Tenemos una variedad de mascotas amorosas listas para ser adoptadas.', '2024-05-20', 1),
    ('Campaña de Esterilización de Gatos', 'Ayúdanos a controlar la población de gatos callejeros participando en nuestra campaña de esterilización gratuita para gatos.', '2024-06-15', 2),
    ('Clínica de Vacunación para Perros', 'Trae a tu perro a nuestra clínica de vacunación para mantenerlo sano y protegido contra enfermedades peligrosas.', '2024-07-10', 3),
    ('Taller de Entrenamiento Canino', 'Aprende técnicas de entrenamiento efectivas para ayudar a tu perro a comportarse mejor y ser un miembro feliz de tu familia.', '2024-08-05', 4),
    ('Campaña de Donación de Alimentos para Animales', 'Ayúdanos a alimentar a los animales necesitados donando comida para perros y gatos a nuestra campaña de donación.', '2024-09-20', 5);

-- Insertar valores en la tabla organizar
INSERT INTO organizar (nombreEvento, dniPersonal) VALUES
    ('Jornada de Adopción de Mascotas', '78965412Q'),
    ('Campaña de Esterilización de Gatos', '32165498B'),
    ('Clínica de Vacunación para Perros', '32165498B'),
    ('Taller de Entrenamiento Canino', '78965412Q'),
    ('Campaña de Donación de Alimentos para Animales', '78965412Q');
   
   
-- Insertar valores en la tabla asistir
INSERT INTO asistir (nombreEvento, dniCliente) VALUES
    ('Jornada de Adopción de Mascotas', '45612378C'),
    ('Campaña de Esterilización de Gatos', '28945612A'),
    ('Clínica de Vacunación para Perros', '45612378C'),
    ('Taller de Entrenamiento Canino', '28945612A'),
    ('Campaña de Donación de Alimentos para Animales', '45612378C');
   
   
-- Insertar valores en la tabla donacion
INSERT INTO donacion (cantidad, fecha, dniDonante)
VALUES
    (100, '2024-04-14 10:00:00', '12345678E'),
    (50, '2024-04-15 11:30:00', '98765432D'),
    (200, '2024-04-16 09:45:00', '45612378C'),
    (150, '2024-04-17 14:20:00', '28945612A');

   
   
   
-- Generar valores aleatorios para la tabla animal
WITH datos_animal AS (
    SELECT
        'Animal-' || i AS nombre,
        CASE (random() * 2)::INT
            WHEN 0 THEN 'Perro'
            WHEN 1 THEN 'Gato'
            ELSE 'Otro'
        END AS especie,
        CURRENT_DATE - (random() * 365 * 10)::INT AS fechaNacimiento, -- Fecha de nacimiento aleatoria en los últimos 10 años
        (SELECT dni FROM usuario ORDER BY random() LIMIT 1) AS dniDueño,
        (SELECT numSucursal FROM sucursal ORDER BY random() LIMIT 1) AS numSucursal
    FROM generate_series(1, 5) AS i
)
INSERT INTO animal (nombre, especie, fechaNacimiento, dniDueño, numSucursal)
SELECT nombre, especie, fechaNacimiento, dniDueño, numSucursal
FROM datos_animal;

-- Generar valores aleatorios para la tabla vacuna
WITH datos_vacuna AS (
    SELECT
        'Vacuna-' || i AS nombre,
        (random() * 3 + 1)::INT AS dosisNecesarias -- Entre 1 y 3 dosis necesarias
    FROM generate_series(1, 4) AS i
)
INSERT INTO vacuna (nombre, dosisNecesarias)
SELECT nombre, dosisNecesarias
FROM datos_vacuna;

-- Insertar valores en la tabla vacunar con ids generadas automáticamente
INSERT INTO vacunar (codigoVacuna, dniPersonal, idAnimal, fechaVacuna)
SELECT
    v.codigo,
    (SELECT dni FROM personal ORDER BY random() LIMIT 1) AS dniPersonal,
    (SELECT id FROM animal ORDER BY random() LIMIT 1) AS idAnimal,
    CURRENT_DATE - (random() * 30)::INT AS fechaVacuna -- Fecha de vacunación aleatoria en el último mes
FROM vacuna v;




-- Insertar valores en la tabla cita
INSERT INTO cita (fechaSolicitud, cancelado, fechaCita, numSucursal, dniCliente)
VALUES
    ('2024-04-14', FALSE, '2024-04-16', 1, '45612378C'),
    ('2024-04-15', FALSE, '2024-04-17', 2, '28945612A'),
    ('2024-04-16', FALSE, '2024-04-18', 3, '45612378C'),
    ('2024-04-17', FALSE, '2024-04-19', 4, '28945612A');

   
-- Insertar valores en la tabla tienda
INSERT INTO tienda (numSucursal, nombre)
VALUES
    (1, 'Tienda de Adopción'),
    (2, 'Tienda de Esterilización'),
    (3, 'Tienda de Vacunación'),
    (4, 'Tienda de Entrenamiento'),
    (5, 'Tienda de Alimentos');

   
-- Insertar valores en la tabla producto
INSERT INTO producto (nombre, descripcion, precio)
VALUES
    ('Comida para Perros', 'Alimento completo y equilibrado para perros de todas las edades y tamaños.', 15.99),
    ('Comida para Gatos', 'Alimento nutritivo para gatos adultos, con proteínas de alta calidad y vitaminas esenciales.', 12.49),
    ('Juguete para Perros', 'Juguete resistente para perros que proporciona horas de diversión y ejercicio.', 8.99),
    ('Juguete para Gatos', 'Juguete interactivo para gatos que estimula el instinto de caza y el ejercicio.', 6.99);

   
-- Insertar valores en la tabla ubicacionProducto
INSERT INTO ubicacionProducto (existencias, nombreTienda, nombreProducto, numSucursal)
VALUES
    (100, 'Tienda de Alimentos', 'Comida para Perros', 5),
    (50, 'Tienda de Alimentos', 'Comida para Gatos', 5),
    (30, 'Tienda de Entrenamiento', 'Juguete para Perros', 4),
    (40, 'Tienda de Entrenamiento', 'Juguete para Gatos', 4);

   
   
   
   
   
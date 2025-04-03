create table usuario
(
nombre varchar(50) not null,
dni varchar(9) not null primary key,
direccion varchar(80) not null,
telefono int not null,
email varchar(25) not null,
clave varchar(25) not null
);


create table voluntario
(
dni varchar(9) not null primary key,
horaEntrada time not null,
horaSalida time not null,
foreign key (dni) references usuario(dni) on delete cascade on update cascade
);


create table personal
(
dni varchar(9) not null primary key,
cargo varchar(25) not null,
horaEntrada time not null,
horaSalida time not null,
cuentaBancaria varchar(24) not null,
foreign key (dni) references usuario(dni)
  on delete cascade on update cascade
);


create table cliente
(
dni varchar(9) not null primary key,
puntuacion int not null,
foreign key (dni) references usuario(dni) on delete cascade on update cascade
);


create table sucursal
(
numSucursal int not null primary key,
municipio varchar(25) not null
);


CREATE TABLE tarea (
    id SERIAL PRIMARY KEY,
    descripcion VARCHAR(500),
    dniPersonal VARCHAR(9) NOT NULL,
    FOREIGN KEY (dniPersonal) REFERENCES personal(dni) ON DELETE CASCADE ON UPDATE CASCADE
);


create table realizarTarea
(
fechaInscripcion date not null,
 fechaFin date,
 dniVoluntario varchar(9) not null,
 idTarea int not null,
 primary key (fechaInscripcion, dniVoluntario, idTarea),
 foreign key (dniVoluntario) references voluntario(dni) on delete cascade on update cascade,
 foreign key (idTarea) references tarea(id) on delete cascade on update cascade
 );


create table evento
(
nombre varchar(60) not null primary key,
descripcion varchar(500),
fecha date not null,
numSucursal int not null,
foreign key (numSucursal) references sucursal(numSucursal) on delete restrict on update cascade
);


create table organizar
(
nombreEvento varchar(60) not null,
dniPersonal varchar(9) not null,
primary key(nombreEvento, dniPersonal),
foreign key (nombreEvento) references evento(nombre)
  on delete restrict on update cascade,
foreign key (dniPersonal) references personal(dni)
  on delete cascade on update cascade
);


create table asistir
(
nombreEvento varchar(60) not null,
dniCliente varchar(9) not null,
primary key(nombreEvento, dniCliente),
foreign key (nombreEvento) references evento(nombre) on delete restrict on update cascade,
foreign key (dniCliente) references cliente(dni) on delete cascade on update cascade
);



CREATE TABLE donacion (
    id SERIAL PRIMARY KEY,
    cantidad INT NOT NULL,
    fecha TIMESTAMP NOT NULL,
    dniDonante VARCHAR(9),
    FOREIGN KEY (dniDonante) REFERENCES usuario(dni) ON DELETE SET NULL ON UPDATE CASCADE
);


CREATE TABLE animal (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(25) NOT NULL,
    especie VARCHAR(25) NOT NULL,
    fechaNacimiento DATE NOT NULL,
    dniDueño VARCHAR(9),
    numSucursal INT NOT NULL,
    FOREIGN KEY (dniDueño) REFERENCES usuario(dni) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (numSucursal) REFERENCES sucursal(numSucursal) ON DELETE SET NULL ON UPDATE CASCADE
);


CREATE TABLE vacuna (
    codigo SERIAL PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    dosisNecesarias INT NOT NULL
);

create table vacunar
(
codigoVacuna int not null,
dniPersonal varchar(9) not null,
idAnimal int not null,
fechaVacuna date,
primary key(codigoVacuna, dniPersonal, idAnimal, fechaVacuna),
foreign key (codigoVacuna) references vacuna(codigo) on delete restrict on update cascade,
foreign key (dniPersonal) references personal(dni) on delete set null on update cascade,
foreign key (idAnimal) references animal(id) on delete restrict on update cascade
);


create table cita
(
fechaSolicitud date default current_date not null,
cancelado bool,
fechaCita date not null,
numSucursal int not null,
dniCliente varchar(9) not null,
primary key (fechaSolicitud, numSucursal, dniCliente),
foreign key (numSucursal) references sucursal(numSucursal) on delete restrict on update cascade,
foreign key (dniCliente) references cliente(dni) on delete restrict on update cascade
);


create table tienda
(
numSucursal int not null,
nombre varchar(60) not null,
primary key(numSucursal, nombre),
foreign key(numSucursal) references sucursal(numSucursal) on delete cascade on update cascade
);


create table producto
(
nombre varchar(60) not null primary key,
descripcion varchar(500) not null,
precio money not null
);


create table ubicacionProducto
(
existencias int not null,
nombreTienda varchar(60) not null,
nombreProducto varchar(60) not null,
numSucursal int not null,
primary key (nombreTienda, nombreProducto, numSucursal),
foreign key (nombreTienda, numSucursal) references tienda(nombre, numSucursal) on delete restrict on update cascade,
foreign key (nombreProducto) references producto(nombre) on delete cascade on update cascade
);

CREATE OR REPLACE FUNCTION validarUsuario (dni varchar(9), clave varchar(25))
RETURNS varchar(15) AS
$$
DECLARE 
    tipo_usuario varchar(15);
BEGIN
    SELECT tipo_usuario_var into tipo_usuario
    FROM (
        SELECT 'voluntario' as tipo_usuario_var
        FROM usuario
        JOIN voluntario ON usuario.dni = voluntario.dni
        WHERE usuario.dni = validarUsuario.dni AND usuario.clave = validarUsuario.clave
        UNION ALL
        SELECT 'personal' as tipo_usuario_var
        FROM usuario
        JOIN personal ON usuario.dni = personal.dni
        WHERE usuario.dni = validarUsuario.dni AND usuario.clave = validarUsuario.clave
        UNION ALL
        SELECT 'cliente' as tipo_usuario_var
        FROM usuario
        JOIN cliente ON usuario.dni = cliente.dni
        WHERE usuario.dni = validarUsuario.dni AND usuario.clave = validarUsuario.clave
        ) AS tipos;
    
    RETURN tipo_usuario;
END;
$$
LANGUAGE plpgsql;

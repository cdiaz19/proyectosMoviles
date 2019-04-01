drop table categoria;
drop table usuario;
drop table videojuego;
drop table bodega;
drop table orden;
drop table orderItem;

--------------------------------- Tablas ---------------------------------
prompt Crean tablas

--TABLE USUARIO
CREATE TABLE usuario(
id VARCHAR(10),
nombre VARCHAR(50),
cedula VARCHAR(10),
correo VARCHAR(25),
contrasena VARCHAR(8),
telefono int,
privilegio VARCHAR(25),
CONSTRAINTS pkUsuario PRIMARY KEY (id)
);

--TABLE CATEGORIA
CREATE TABLE categoria(
id VARCHAR(10),
nombre VARCHAR(50),
codigo int,
CONSTRAINTS pkCategoria PRIMARY KEY (id)
);

--TABLE VIDEOJUEGO
CREATE TABLE videojuego(
id VARCHAR(10),
nombre VARCHAR(50),
precio float,
empresa VARCHAR(10),
categoriaId VARCHAR(10) not null,
CONSTRAINTS pkVideojuego PRIMARY KEY (id),
CONSTRAINTS fkCategoria
  FOREIGN KEY (categoriaId)
  REFERENCES categoria(id) ON DELETE CASCADE
);

--TABLE BODEGA
CREATE TABLE bodega(
id VARCHAR(10),
fechaIngreso VARCHAR(50),
cantidad int,
videojuegoId VARCHAR(10) not null,
usuarioId VARCHAR(10) not null,
CONSTRAINTS pkBodega PRIMARY KEY (id),
CONSTRAINTS fkVideojuego
  FOREIGN KEY (videojuegoId)
  REFERENCES videojuego(id) ON DELETE CASCADE,
CONSTRAINTS fkUsuario
  FOREIGN KEY (usuarioId)
  REFERENCES usuario(id) ON DELETE CASCADE,
);

--TABLE ORDEN
CREATE TABLE orden(
id VARCHAR(10),
codigo VARCHAR(50),
fechaCompra VARCHAR(25),
montoTotal float,
ordenItemId VARCHAR(10) not null,
CONSTRAINTS pkCarrera PRIMARY KEY (id),
CONSTRAINTS fkordenItem
  FOREIGN KEY (ordenItemId)
  REFERENCES ordenItem(id) ON DELETE CASCADE,
);

--TABLE ORDENITEM
CREATE TABLE orderItem(
id VARCHAR(10),
cantidad int,
montoItem float,
videojuegoId VARCHAR(10) not null,
ordenId VARCHAR(10) not null,
CONSTRAINTS pkOrdenItem PRIMARY KEY (id),
CONSTRAINTS fkVideojuego
  FOREIGN KEY (videojuegoId)
  REFERENCES videojuego(id) ON DELETE CASCADE,
CONSTRAINTS fkOrden
  FOREIGN KEY (ordenId)
  REFERENCES orden(id) ON DELETE CASCADE,
);

-------------------------------- Datos de Prueba ---------------------------------
prompt Insertar datos

INSERT INTO usuario values('1', 'Georges', '304740933', 'george@example.com', 'admin1234', 87294567, "admin");
INSERT INTO usuario values('2', 'Alejandro', '304740934', 'alejandro@example.com', 'ale12345678', 87294567, "cliente");

INSERT INTO categoria values('1', 'Aventura', 001);
INSERT INTO categoria values('2', 'Roles', 002);
INSERT INTO categoria values('3', 'Estrategia', 003);

INSERT INTO videojuego values('1', 'Legend of Zelda', 12000, "Nintendo", 1 );
INSERT INTO videojuego values('2', 'Fifa19', 10000, "EASport", 2 );

INSERT INTO bodega values('1', '02/03/2019', 34, 1, 1);
INSERT INTO bodega values('2', '02/03/2019', 20, 2, 1);

INSERT INTO order values('1', 0001, '15/03/2019', 12000, 1);

INSERT INTO orderItem values('1', 1, 12000, 1, 1);

--------------------------------- Mantenimientos ---------------------------------
--CURSOR REFERENCE
CREATE OR REPLACE PACKAGE types
AS
  TYPE ref_cursor IS REF CURSOR;
END;
/

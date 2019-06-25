drop table pedido;
DROP Table videojuego;
DROP Table categoria;
drop table cliente;
drop table usuario;


----------------------Tablas-------------------------------------------------
-----------------TablaCategoria----------------------------------------------
CREATE TABLE categoria (
  codigo  VARCHAR(30) NOT NULL,
  nombre  VARCHAR(30) NOT NULL,
  CONSTRAINT pk_categoria PRIMARY KEY (codigo)
);

-----------------TablaUsuario--------------------------------------------

CREATE TABLE usuario(
cedula VARCHAR(30) NOT NULL,
contrasena VARCHAR(30),
email VARCHAR(30) NOT NULL,
rol VARCHAR(30) NOT NULL,
CONSTRAINT pkUsuario PRIMARY KEY (cedula)
);
-----------------TablaCliente---------------------------------------------
CREATE TABLE cliente(
nombre VARCHAR(100),
telefono int,
ced_usu VARCHAR(30) NOT NULL,
CONSTRAINT pkcliente PRIMARY KEY (ced_usu),
CONSTRAINT fk_usu
    FOREIGN KEY (ced_usu)
    REFERENCES usuario(cedula) ON DELETE CASCADE
);


-----------------TablaVideojuegos------------------------------------------
CREATE TABLE videojuego (
  codigo_juego VARCHAR(10) NOT NULL,
  nombre VARCHAR(50)NOT NULL,
  cantidad int NOT NULL,
  precio int NOT NULL,
  empresa VARCHAR(30) NOT NULL,
  categoria_id VARCHAR(30) NOT NULL,
  CONSTRAINT pk_videojuego PRIMARY KEY (codigo_juego),
  CONSTRAINT fk_categoria
    FOREIGN KEY (categoria_id)
    REFERENCES categoria(codigo) ON DELETE CASCADE
);
CREATE TABLE pedido(
	id int GENERATED ALWAYS AS IDENTITY,
 	fecha VARCHAR(20),
	cantidad int,
	total int,
	cliente_id VARCHAR(30) NOT NULL,
	videojuego_id VARCHAR(10) NOT NULL,
	CONSTRAINT pK_pedido PRIMARY KEY (id),
	CONSTRAINT fk_cliente
    	FOREIGN KEY (cliente_id)
    	REFERENCES cliente(ced_usu) ON DELETE CASCADE,
	CONSTRAINT fk_videojuego
    	FOREIGN KEY (videojuego_id)
    	REFERENCES videojuego(codigo_juego) ON DELETE CASCADE
);

----------------Datos Iniciales-------------------------------------------
--Usuario
Insert Into usuario values('115790444','12345','ale@gmail.com','cliente');
Insert Into usuario values('4646422','12345678','cdiaz@gmail.com','cliente');
--Clientes
Insert Into cliente values('Alejandro Gamboa',22928804,'115790444');
Insert Into cliente values('Cristian Diaz Jimenez',8999660,'4646422');

-- Categorias
INSERT INTO categoria VALUES ('ACC', 'Accion');
INSERT INTO categoria VALUES ('STR', 'Estrategia');
INSERT INTO categoria VALUES ('ARPG', 'Action Role-Playing ');

-- VideoJuegos
INSERT INTO videojuego VALUES ('GTAV', 'Grand Thelf Auto V', 20, 35000, 'RockStart', 'ACC');
INSERT INTO videojuego VALUES ('SK', 'Skyrim', 78, 42000, 'Bethesda Game Studios', 'ARPG');

--Pedidos
INSERT INTO pedido (fecha,cantidad,total,cliente_id,videojuego_id) VALUES ('2020',5,6,'115790444','GTAV');
----------------Mantenimientos--------------------------------------------

----------------MantenimientoDePedidos----------------------------------
CREATE OR REPLACE FUNCTION insertarpedido (
  fecha_IN VARCHAR,
  cantidad_IN int,
  cliente_id_IN VARCHAR,
  videojuego_id_IN VARCHAR
)
RETURNS VOID
AS
'
    INSERT INTO pedido(fecha,cantidad,total,cliente_id,videojuego_id) VALUES (fecha_IN,cantidad_IN,(SELECT precio from videojuego where codigo_juego=videojuego_id_IN) * cantidad_IN,cliente_id_IN,videojuego_id_IN);
    UPDATE videojuego
    SET cantidad= (SELECT cantidad from videojuego where codigo_juego=videojuego_id_IN) - cantidad_IN
	WHERE codigo_juego=videojuego_id_IN;  
'
LANGUAGE SQL;


CREATE OR REPLACE FUNCTION buscarpedido (
  id_pedido_IN int
)
RETURNS refcursor
AS
'
DECLARE
  ref refcursor;
BEGIN
   OPEN ref FOR SELECT id,fecha,cantidad,total,cliente_id, videojuego_id FROM pedido WHERE id=id_pedido_IN;
   RETURN ref;
END;
'
LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION actualizarpedido (
  id_pedido_IN int,
  fecha_IN VARCHAR,
  cantidad_IN int,	
  cliente_id_IN VARCHAR,
  videojuego_id_IN VARCHAR
)
RETURNS VOID
AS
'
    UPDATE pedido SET fecha=fecha_IN,
	cantidad=cantidad_IN,
	total=(SELECT precio from videojuego where codigo_juego=videojuego_id_IN) * cantidad_IN
	WHERE id=id_pedido_IN;
    UPDATE videojuego
    SET cantidad= (SELECT precio from videojuego where codigo_juego=videojuego_id_IN) - cantidad_IN
	WHERE codigo_juego=videojuego_id_IN;  
'
LANGUAGE SQL;

CREATE OR REPLACE FUNCTION listarpedidos ()
RETURNS refcursor
AS
'
DECLARE
  ref refcursor;
BEGIN
   OPEN ref FOR SELECT id,fecha,cantidad,total,cliente_id, videojuego_id FROM pedido;
RETURN ref;
END;
'
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION eliminarpedido (
  id_pedido_IN int
)
RETURNS VOID
AS
'
   DELETE FROM pedido WHERE id=id_pedido_IN;
'
LANGUAGE SQL;

----------------MantenimientoDeClientes---------------------------------


CREATE OR REPLACE FUNCTION insertarcliente (
  cedula_IN VARCHAR,
  contrasena_IN VARCHAR,
  email_IN VARCHAR,
  nombre_IN VARCHAR,
  telefono_IN int,
  rol_IN VARCHAR
)
RETURNS VOID
AS
'
    INSERT INTO usuario(cedula,contrasena,email,rol) VALUES (cedula_IN,contrasena_IN,email_IN,rol_IN);
    INSERT INTO cliente(nombre,telefono,ced_usu) VALUES (nombre_IN,telefono_IN,cedula_IN);
'
LANGUAGE SQL;


CREATE OR REPLACE FUNCTION buscarcliente (
  cedula_IN VARCHAR
)
RETURNS refcursor
AS
'
DECLARE
  ref refcursor;
BEGIN
   OPEN ref FOR SELECT u.cedula,c.nombre,u.email,c.telefono,u.contrasena FROM usuario u join cliente c on c.ced_usu=u.cedula WHERE c.ced_usu=cedula_IN;
   RETURN ref;
END;
'
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION listarclientes ()
RETURNS refcursor
AS
'
DECLARE
  ref refcursor;
BEGIN
   OPEN ref FOR SELECT u.cedula,c.nombre,u.email,c.telefono,u.contrasena FROM usuario u join cliente c on c.ced_usu=u.cedula;
RETURN ref;
END;
'
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION actualizarcliente (
  cedula_IN VARCHAR,
  contrasena_IN VARCHAR,
  email_IN VARCHAR,
  nombre_IN VARCHAR,
  telefono_IN int,
  rol_IN VARCHAR)
RETURNS VOID
AS
'
   UPDATE cliente
    SET nombre=nombre_IN,
    telefono=telefono_IN
    WHERE ced_usu=cedula_IN;
    UPDATE usuario
    SET contrasena=contrasena_IN,
    rol=rol_IN,
    email=email_IN
    WHERE cedula=cedula_IN;
	
'
LANGUAGE SQL;

CREATE OR REPLACE FUNCTION eliminarcliente (
  cedula_IN VARCHAR
)
RETURNS VOID
AS
'
   DELETE FROM usuario WHERE cedula=cedula_IN;
'
LANGUAGE SQL;



----------------MantenimientoDeVideojuegos--------------------------------
CREATE OR REPLACE FUNCTION insertarvideojuego (
  codigojuego_IN VARCHAR,
  nombrejuego_IN VARCHAR,
  cantidad_IN int,
  precio_IN int,
  empresa_IN VARCHAR,
  categoriaID_IN VARCHAR
)
RETURNS VOID
AS
'
    INSERT INTO videojuego(codigo_juego,nombre,cantidad,precio,empresa,categoria_id)
  VALUES(codigojuego_IN,nombrejuego_IN,cantidad_IN,precio_IN,empresa_IN,categoriaID_IN);
'
LANGUAGE SQL;

CREATE OR REPLACE FUNCTION buscarvideojuego (
  codigojuego_IN VARCHAR
)
RETURNS refcursor
AS
'
DECLARE
  ref refcursor;
BEGIN
   OPEN ref FOR SELECT v.codigo_juego,v.nombre,v.cantidad,v.precio,v.empresa,v.categoria_id,c.nombre AS "nombre_categoria" FROM videojuego v join categoria c on v.categoria_id=c.codigo WHERE v.codigo_juego=codigojuego_IN;
   RETURN ref;
END;
'
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION buscarvideojuegoporcategoria (
  categoriajuego_IN VARCHAR
)
RETURNS refcursor
AS
'
DECLARE
  ref refcursor;
BEGIN
   OPEN ref FOR SELECT v.codigo_juego,v.nombre,v.cantidad,v.precio,v.empresa,v.categoria_id,c.nombre AS "nombre_categoria" FROM videojuego v join categoria c on v.categoria_id=c.codigo WHERE v.categoria_id=categoriajuego_IN;
   RETURN ref;
END;
'
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION listarvideojuegos ()
RETURNS refcursor
AS
'
DECLARE
  ref refcursor;
BEGIN
   OPEN ref FOR SELECT v.codigo_juego,v.nombre,v.cantidad,v.precio,v.empresa,v.categoria_id,c.nombre AS "nombre_categoria" FROM videojuego v join categoria c on v.categoria_id=c.codigo;
RETURN ref;
END;
'
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION actualizarvideojuego (
  codigojuego_IN VARCHAR,
  nombrejuego_IN VARCHAR,
  cantidad_IN int,
  precio_IN int,
  empresa_IN VARCHAR,
  categoriaID_IN VARCHAR)
RETURNS VOID
AS
'
   UPDATE videojuego
    SET nombre=nombrejuego_IN,
    cantidad=cantidad_IN,
    precio=precio_IN,
    empresa=empresa_IN
    WHERE codigo_juego=codigojuego_IN;
'
LANGUAGE SQL;


CREATE OR REPLACE FUNCTION eliminarvideojuego (
  codigojuego_IN VARCHAR
)
RETURNS VOID
AS
'
   DELETE FROM videojuego WHERE codigo_juego=codigojuego_IN;
'
LANGUAGE SQL;

----------------MantenimientoDeCategorias-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION insertarCategoria (
  codigo_IN VARCHAR,
  nombre_IN VARCHAR
)
RETURNS VOID
AS
'
    INSERT INTO categoria(codigo,nombre)
  VALUES(codigo_IN,nombre_IN);
'
LANGUAGE SQL;

CREATE OR REPLACE FUNCTION buscarCategoria (
  codigo_IN VARCHAR
)
RETURNS refcursor
AS
'
DECLARE
  ref refcursor;
BEGIN
   OPEN ref FOR SELECT codigo,nombre FROM categoria WHERE codigo=codigo_IN;
   RETURN ref;
END;
'
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION listarCategorias ()
RETURNS refcursor
AS
'
DECLARE
  ref refcursor;
BEGIN
   OPEN ref FOR SELECT codigo,nombre FROM categoria;
RETURN ref;
END;
'
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION actualizarCategoria (
  codigo_IN VARCHAR,
  nombre_IN VARCHAR)
RETURNS VOID
AS
'
   UPDATE categoria
    SET nombre=nombre_IN
    WHERE codigo=codigo_IN;
'
LANGUAGE SQL;

CREATE OR REPLACE FUNCTION eliminarCategoria (
  codigo_IN VARCHAR
)
RETURNS VOID
AS
'
   DELETE FROM categoria WHERE codigo=codigo_IN;
'
LANGUAGE SQL;

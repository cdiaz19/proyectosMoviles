----------------------Tablas-------------------------------------------------------------------------------
-----------------TablaCategoria-----------------------------------------------------------------------------
CREATE TABLE categoria (
         codigo      VARCHAR(30) NOT NULL,
         nombre   VARCHAR(30) NOT NULL,
         CONSTRAINT pk_categoria PRIMARY KEY (codigo)
       );

-----------------TablaVideojuegos-----------------------------------------------------------------------------
CREATE TABLE videojuego(
codigo_juego VARCHAR(10) NOT NULL,
nombre VARCHAR(50)NOT NULL,
cantidad int NOT NULL,
precio int NOT NULL,
rentor VARCHAR(50) NOT NULL,
plazo VARCHAR(30) NOT NULL,
empresa VARCHAR(30) NOT NULL,
categoria_id VARCHAR(30) NOT NULL,
CONSTRAINT pk_videojuego PRIMARY KEY (codigo_juego),
CONSTRAINT fk_categoria
  FOREIGN KEY (categoria_id)
  REFERENCES categoria(codigo) ON DELETE CASCADE
);
----------------Mantenimientos-------------------------------------------------------------------------------
----------------MantenimientoDeVideojuegos-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION insertarvideojuego (
  codigojuego_IN VARCHAR,
  nombrejuego_IN VARCHAR,
  cantidad_IN int,
  precio_IN int,
  rentor_IN VARCHAR,
  plazo_IN VARCHAR,
  empresa_IN VARCHAR,
  categoriaID_IN VARCHAR
)
RETURNS VOID
AS
'
    INSERT INTO videojuego(codigo_juego,nombre,cantidad,precio,rentor,plazo,empresa,categoria_id)
  VALUES(codigojuego_IN,nombrejuego_IN,cantidad_IN,precio_IN,rentor_IN,plazo_IN,empresa_IN,categoriaID_IN);
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
   OPEN ref FOR SELECT v.codigo_juego,v.nombre,v.cantidad,v.precio,v.rentor,v.plazo,v.empresa,v.categoria_id,c.nombre AS "nombre_categoria" FROM videojuego v join categoria c on v.categoria_id=c.codigo WHERE v.codigo_juego=codigojuego_IN;
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
   OPEN ref FOR SELECT v.codigo_juego,v.nombre,v.cantidad,v.precio,v.rentor,v.plazo,v.empresa,v.categoria_id,c.nombre AS "nombre_categoria" FROM videojuego v join categoria c on v.categoria_id=c.codigo WHERE v.categoria_id=categoriajuego_IN;
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
   OPEN ref FOR SELECT v.codigo_juego,v.nombre,v.cantidad,v.precio,v.rentor,v.plazo,v.empresa,v.categoria_id,c.nombre AS "nombre_categoria" FROM videojuego v join categoria c on v.categoria_id=c.codigo;
RETURN ref;
END;
'
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION actualizarvideojuego (
  codigojuego_IN VARCHAR,
  nombrejuego_IN VARCHAR,
  cantidad_IN int,
  precio_IN int,
  rentor_IN VARCHAR,
  plazo_IN VARCHAR,
  empresa_IN VARCHAR,
  categoriaID_IN VARCHAR)
RETURNS VOID
AS
'
   UPDATE videojuego
    SET nombre=nombrejuego_IN,
    cantidad=cantidad_IN,
    precio=precio_IN,
    rentor=rentor_IN,
    plazo=plazo_IN,
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






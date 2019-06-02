drop table ciclo;
drop table profesor;
drop table curso;
drop table carrera;


--------------------------------- Tablas ---------------------------------
--TABLA CICLO
CREATE TABLE ciclo(
codigo VARCHAR(10) NOT NULL,
anno  int NOT NULL,
numero int NOT NULL,
fechaInicio VARCHAR(10) NOT NULL,
fechaFinal  VARCHAR(10) NOT NULL,
CONSTRAINT pkCiclo PRIMARY KEY (codigo)
);
--TABLA PROFESOR
CREATE TABLE profesor(
cedula VARCHAR(10) NOT NULL,
nombre VARCHAR(50) NOT NULL,
email VARCHAR(20) NOT NULL,
telefono int NOT NULL,
CONSTRAINT pkprofesor PRIMARY KEY (cedula)
);

--Tabla Carrera
CREATE TABLE carrera (
  codigo VARCHAR(10) NOT NULL,
  nombre VARCHAR(50)NOT NULL,
  titulo VARCHAR(50) NOT NULL,
  CONSTRAINT pk_carrera PRIMARY KEY (codigo)
);

--TABLA CURSO
CREATE TABLE curso(
codigo VARCHAR(20) NOT NULL,
nombre  VARCHAR(50) NOT NULL,
creditos int NOT NULL,
horas int NOT NULL,
codigo_carrera VARCHAR(10) NOT NULL,
CONSTRAINT pkcurso PRIMARY KEY (codigo),
CONSTRAINT fk_carrera
FOREIGN KEY (codigo_carrera)
    REFERENCES carrera(codigo) ON DELETE CASCADE
);

INSERT INTO ciclo VALUES ('120', 2018,1,'01/02/2018','01/07/2018');
INSERT INTO ciclo VALUES ('150', 2018,2,'01/08/2018','01/11/2018');
INSERT INTO ciclo VALUES ('160', 2019,1,'01/02/2019','01/07/2019');
INSERT INTO ciclo VALUES ('170', 2019,2,'01/08/2019','01/11/2019');

INSERT INTO profesor VALUES ('3045682','Cristian Diaz Jimenez','@cristian',22222222);
INSERT INTO profesor VALUES ('115790444','Alejandro Gamboa Barahona','@alejandro',22722222);
INSERT INTO carrera VALUES ('BIO', 'BIOLOGIA','BACHILLER');
INSERT INTO carrera VALUES ('INF', 'INFORMATICA','BACHILLER');
INSERT INTO carrera VALUES ('CP', 'CIENCIAS POLITICAS','BACHILLER');

INSERT INTO curso VALUES ('MATEINF', 'MATEMATICA PARA INFORMATICA',3,6,'INF');
INSERT INTO curso VALUES ('EIF401', 'PARADIGMAS DE PROGRAMACION',3,6,'INF');
INSERT INTO curso VALUES ('MATEI', 'CALCULO I',3,6,'BIO');
INSERT INTO curso VALUES ('BOT', 'INTRODUCCION A LA BOTANICA',3,8,'BIO');
INSERT INTO curso VALUES ('AD', 'INTRODUCCION A LA ADMINISTRACION PUBLICA',3,8,'CP');






------------------------------------Mantenimientos------------------------------
--Mantenimiento Carrera
CREATE OR REPLACE FUNCTION insertarcarrera (
  codigo_IN VARCHAR,
  nombre_IN VARCHAR,
  titulo_IN VARCHAR
)
RETURNS VOID
AS
'
    INSERT INTO carrera(codigo,nombre,titulo)
  VALUES(codigo_IN,nombre_IN,titulo_IN);
'
LANGUAGE SQL;


CREATE OR REPLACE FUNCTION buscarcarrera (
  codigo_IN VARCHAR
)
RETURNS refcursor
AS
'
DECLARE
  ref refcursor;
BEGIN
   OPEN ref FOR SELECT codigo,nombre,titulo FROM carrera WHERE codigo=codigo_IN;
   RETURN ref;
END;
'
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION listarCarreras ()
RETURNS refcursor
AS
'
DECLARE
  ref refcursor;
BEGIN
   OPEN ref FOR SELECT codigo,nombre,titulo FROM carrera;
RETURN ref;
END;
'
LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION actualizarcarrera (
  codigo_IN VARCHAR,
  nombre_IN VARCHAR,
  titulo_IN VARCHAR)

RETURNS VOID
AS
'
   UPDATE carrera
    SET nombre=nombre_IN,
    titulo=titulo_IN
    WHERE codigo=codigo_IN;
'
LANGUAGE SQL;


CREATE OR REPLACE FUNCTION eliminarcarrera (
  codigo_IN VARCHAR
)
RETURNS VOID
AS
'
   DELETE FROM carrera WHERE codigo=codigo_IN;
'
LANGUAGE SQL;

--Mantenimiento Curso
CREATE OR REPLACE FUNCTION insertarcurso (
  codigo_IN VARCHAR,
  nombre_IN VARCHAR,
  creditos_IN int,
  horas_IN int,
  codigocarrera_IN VARCHAR
)
RETURNS VOID
AS
'
    INSERT INTO curso(codigo,nombre,creditos,horas,codigo_carrera)
  VALUES(codigo_IN,nombre_IN,creditos_IN,horas_IN,codigocarrera_IN);
'
LANGUAGE SQL;

CREATE OR REPLACE FUNCTION buscarcurso (
  codigo_IN VARCHAR
)
RETURNS refcursor
AS
'
DECLARE
  ref refcursor;
BEGIN
   OPEN ref FOR SELECT v.codigo,v.nombre,v.creditos,v.horas, v.codigo_carrera, m.nombre AS "nombre_carrera" FROM curso v join carrera m on v.codigo_carrera=m.codigo WHERE v.codigo=codigo_IN;
   RETURN ref;
END;
'
LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION listarcursos ()
RETURNS refcursor
AS
'
DECLARE
  ref refcursor;
BEGIN
  OPEN ref FOR SELECT v.codigo,v.nombre,v.creditos,v.horas, v.codigo_carrera, m.nombre AS "nombre_carrera" FROM curso v join carrera m on v.codigo_carrera=m.codigo;
RETURN ref;
END;
'
LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION actualizarcurso (
  codigo_IN VARCHAR,
  nombre_IN VARCHAR,
  creditos_IN int,
  horas_IN int,
  codigocarrera_IN VARCHAR)

RETURNS VOID
AS
'
   UPDATE curso
    SET nombre=nombre_IN,
    creditos=creditos_IN,
    horas=horas_IN,
    codigo_carrera=codigocarrera_IN
    WHERE codigo=codigo_IN;
'
LANGUAGE SQL;

CREATE OR REPLACE FUNCTION eliminarcurso (
  codigo_IN VARCHAR
)
RETURNS VOID
AS
'
   DELETE FROM curso WHERE codigo=codigo_IN;
'
LANGUAGE SQL;

--Mantenimiento Profesor

CREATE OR REPLACE FUNCTION insertarprofesor (
  cedula_IN VARCHAR,
  nombre_IN VARCHAR,
  email_IN VARCHAR,
  telefono_IN int
)
RETURNS VOID
AS
'
    INSERT INTO profesor(cedula,nombre,email,telefono)
  VALUES(cedula_IN,nombre_IN,email_IN,telefono_IN);
'
LANGUAGE SQL;

CREATE OR REPLACE FUNCTION buscarProfesor (
  cedula_IN VARCHAR
)
RETURNS refcursor
AS
'
DECLARE
  ref refcursor;
BEGIN
   OPEN ref FOR SELECT cedula,nombre,email,telefono FROM profesor WHERE cedula=cedula_IN;
   RETURN ref;
END;
'
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION listarProfesores ()
RETURNS refcursor
AS
'
DECLARE
  ref refcursor;
BEGIN
   OPEN ref FOR SELECT cedula,nombre,email,telefono FROM profesor;
RETURN ref;
END;
'
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION actualizarProfesor (
  cedula_IN VARCHAR,
  nombre_IN VARCHAR,
  email_IN VARCHAR,
  telefono_IN int)

RETURNS VOID
AS
'
   UPDATE profesor
    SET nombre=nombre_IN,
    email=email_IN,
    telefono=telefono_IN
    WHERE cedula=cedula_IN;
'
LANGUAGE SQL;

CREATE OR REPLACE FUNCTION eliminarProfesor (
  cedula_IN VARCHAR
)
RETURNS VOID
AS
'
   DELETE FROM profesor WHERE cedula=cedula_IN;
'
LANGUAGE SQL;


--Mantenimiento Ciclos
CREATE OR REPLACE FUNCTION insertarCiclo (
  codigo_IN VARCHAR,
  anno_IN int,
  numero_IN int,
  fechaInicio_IN VARCHAR,
  fechaFinal_IN VARCHAR
)
RETURNS VOID
AS
'
    INSERT INTO ciclo(codigo,anno,numero,fechaInicio,fechaFinal)
  VALUES(codigo_IN,anno_IN,numero_IN,fechaInicio_IN,fechaFinal_IN);
'
LANGUAGE SQL;

CREATE OR REPLACE FUNCTION buscarCiclo (
  codigo_IN VARCHAR
)
RETURNS refcursor
AS
'
DECLARE
  ref refcursor;
BEGIN
   OPEN ref FOR SELECT codigo,anno,numero,fechaInicio,fechaFinal FROM ciclo WHERE codigo=codigo_IN;
   RETURN ref;
END;
'
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION listarCiclos ()
RETURNS refcursor
AS
'
DECLARE
  ref refcursor;
BEGIN
   OPEN ref FOR SELECT codigo,anno,numero,fechaInicio,fechaFinal FROM ciclo;
RETURN ref;
END;
'
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION actualizarCiclo (
  codigo_IN VARCHAR,
  anno_IN int,
  numero_IN int,
  fechaInicio_IN VARCHAR,
  fechaFinal_IN VARCHAR)

RETURNS VOID
AS
'
   UPDATE ciclo
    SET anno=anno_IN,
    numero=numero_IN,
    fechaInicio=fechaInicio_IN,
    fechaFinal=fechaFinal_IN
    WHERE codigo=codigo_IN;
'
LANGUAGE SQL;

CREATE OR REPLACE FUNCTION eliminarciclo (
  codigo_IN VARCHAR
)
RETURNS VOID
AS
'
   DELETE FROM ciclo WHERE codigo=codigo_IN;
'
LANGUAGE SQL;




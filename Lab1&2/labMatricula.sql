conn system/root

drop tablespace TB_TABLAS including contents and datafiles;
CREATE TABLESPACE TB_TABLAS
  datafile 'C:\oraclexe\app\oracle\oradata\XE\TB_TABLAS.DBF'
  SIZE  5M REUSE AUTOEXTEND OFF;

drop table ciclo;
drop table profesor;
drop table curso;

--------------------------------- TABLES ---------------------------------
--TABLE SCHOOL YEAR
CREATE TABLE ciclo(
id VARCHAR(10),
anno int,
numero int,
fechaInicio VARCHAR(10),
fechaFinal VARCHAR(10),
CONSTRAINTS pkciclo PRIMARY KEY (id)
);

--TABLE PROFESSOR
CREATE TABLE profesor(
id VARCHAR(10),
cedula VARCHAR(10),
nombre VARCHAR(50),
email VARCHAR(25),
contrasena VARCHAR(8),
telefono int,
CONSTRAINTS pkprofesor PRIMARY KEY (id)
);

--TABLE COURSE
CREATE TABLE curso(
id VARCHAR(10),
codigo VARCHAR(10),
nombre VARCHAR(50),
creditos int,
horasSemanales int,
CONSTRAINTS pkcurso PRIMARY KEY (id)
);

--------------------------------- METHODS ---------------------------------
--CURSOR REFERENCE
CREATE OR REPLACE PACKAGE types
AS
  TYPE ref_cursor IS REF CURSOR;
END;
/

------------ INDEX #GET ------------
-- SCHOOL YEAR INDEX
CREATE OR REPLACE FUNCTION listarCiclos
RETURN Types.ref_cursor
AS
  cursorCiclo types.ref_cursor;
BEGIN
  OPEN cursorCiclo FOR
    SELECT id, anno, numero, fechaInicio, fechaFinal from ciclo;
  RETURN cursorCiclo;
END;
/

-- PROFESSOR INDEX
CREATE OR REPLACE FUNCTION listarProfesores
RETURN Types.ref_cursor
AS
  cursorProfesor types.ref_cursor;
BEGIN
  OPEN cursorProfesor FOR
    SELECT id, cedula, nombre, email, contrasena, telefono from profesor;
  RETURN cursorProfesor;
END;
/

-- COURSE INDEX
CREATE OR REPLACE FUNCTION listarCursos
RETURN Types.ref_cursor
AS
  cursorCurso types.ref_cursor;
BEGIN
  OPEN cursorCurso FOR
    SELECT id, codigo, nombre, creditos, horasSemanales from curso;
  RETURN cursorCurso;
END;
/

------------ CREATE #POST ------------
-- INSERT SCHOOL YEAR
CREATE OR REPLACE PROCEDURE insertarCiclo(id IN ciclo.id%TYPE, anno IN ciclo.anno%TYPE, numero IN ciclo.numero%TYPE,
fechaInicio IN ciclo.fechaInicio%TYPE, fechaFinal IN ciclo.fechaFinal%TYPE)
AS
BEGIN
	INSERT INTO ciclo VALUES(id, anno, numero, fechaInicio, fechaFinal);
END;
/

-- INSERT PROFESSOR
CREATE OR REPLACE PROCEDURE insertarProfesor(id IN profesor.id%TYPE, cedula IN profesor.cedula%TYPE, nombre IN profesor.nombre%TYPE, email IN profesor.email%TYPE,
contrasena IN profesor.contrasena%TYPE, telefono IN profesor.telefono%TYPE)
AS
BEGIN
	INSERT INTO profesor VALUES(id, cedula, nombre, email, contrasena, telefono);
END;
/

-- INSERT COURSE
CREATE OR REPLACE PROCEDURE insertarCurso(id IN curso.id%TYPE, codigo IN curso.codigo%TYPE, nombre IN curso.nombre%TYPE, creditos IN curso.creditos%TYPE,
horasSemanales IN curso.horasSemanales%TYPE)
AS
BEGIN
	INSERT INTO curso VALUES(id, codigo, nombre, creditos, horasSemanales);
END;
/

------------ SHOW #GET (object_id)------------
-- SHOW SCHOOL YEAR
CREATE OR REPLACE FUNCTION buscarCiclo(idCiclo IN varchar)
RETURN Types.ref_cursor
AS
  cursorCiclo types.ref_cursor;
BEGIN
  OPEN cursorCiclo FOR
    SELECT id, anno, numero, fechaInicio, fechaFinal from ciclo WHERE id=idCiclo;
RETURN cursorCiclo;
END;
/

-- SHOW PROFESSOR
CREATE OR REPLACE FUNCTION buscarProfesor(idProfesor IN varchar)
RETURN Types.ref_cursor
AS
  cursorProfesor types.ref_cursor;
BEGIN
  OPEN cursorProfesor FOR
    SELECT id, cedula, nombre, email, contrasena, telefono from profesor WHERE id=idProfesor;
RETURN cursorProfesor;
END;
/

-- SHOW COURSE
CREATE OR REPLACE FUNCTION buscarCurso(idCurso IN varchar)
RETURN Types.ref_cursor
AS
  cursorCurso types.ref_cursor;
BEGIN
  OPEN cursorCurso FOR
    SELECT id, codigo, nombre, creditos, horasSemanales from curso WHERE id=idCurso;
RETURN cursorCurso;
END;
/

------------ UPDATE #PUT (object_id)------------
-- UPDATE SCHOOL YEAR
CREATE OR REPLACE PROCEDURE modificarCiclo(idCiclo IN ciclo.id%TYPE, annoNuevo IN ciclo.anno%TYPE, numeroNuevo IN ciclo.numero%TYPE,
fechaInicioNuevo IN ciclo.fechaInicio%TYPE, fechaFinalNuevo IN ciclo.fechaFinal%TYPE)
AS
BEGIN
  UPDATE ciclo SET anno=annoNuevo, numero=numeroNuevo, fechaInicio=fechaInicioNuevo, fechaFinal=fechaFinalNuevo WHERE id=idCiclo;
END;
/

-- UPDATE PROFESSOR
CREATE OR REPLACE PROCEDURE modificarProfesor(idProfesor IN profesor.id%TYPE, cedulaNuevo IN profesor.cedula%TYPE, nombreNuevo IN profesor.nombre%TYPE, emailNuevo IN profesor.email%TYPE,
contrasenaNuevo IN profesor.contrasena%TYPE, telefonoNuevo IN profesor.telefono%TYPE)
AS
BEGIN
  UPDATE profesor SET cedula=cedulaNuevo, nombre=nombreNuevo, email=emailNuevo, contrasena=contrasenaNuevo, telefono=telefonoNuevo WHERE id=idProfesor;
END;
/

-- UPDATE COURSE
CREATE OR REPLACE PROCEDURE modificarCurso(idCurso IN curso.id%TYPE, codigoNuevo IN curso.codigo%TYPE, nombreNuevo IN curso.nombre%TYPE, creditosNuevo IN curso.creditos%TYPE,
horasSemanalesNuevo IN curso.horasSemanales%TYPE)
AS
BEGIN
  UPDATE curso SET codigo=codigoNuevo, nombre=nombreNuevo, creditos=creditosNuevo, horasSemanales=horasSemanalesNuevo WHERE id=idCurso;
END;
/

------------ DESTROY #DELETE (object_id)------------
-- DESTROY SCHOOL YEAR
create or replace procedure eliminarCiclo(idCiclo IN varchar)
as
begin
  delete from ciclo where id=idCiclo;
end;
/

-- DESTROY PROFESSOR
create or replace procedure eliminarProfesor(idProfesor IN varchar)
as
begin
  delete from profesor where id=idProfesor;
end;
/

-- DESTROY COURSE
create or replace procedure eliminarCurso(idCurso IN varchar)
as
begin
  delete from curso where id=idCurso;
end;
/

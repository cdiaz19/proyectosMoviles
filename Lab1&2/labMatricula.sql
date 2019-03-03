drop tablespace TB_TABLAS including contents and datafiles;
CREATE TABLESPACE TB_TABLAS
  datafile 'C:\oraclexe\app\oracle\oradata\XE\TB_TABLAS.DBF'
  SIZE  5M REUSE AUTOEXTEND OFF;

drop table listaCurso;
drop table ciclo;
drop table profesor;
drop table usuario;
drop table curso;
drop table carrera;

--------------------------------- Tablas ---------------------------------
--TABLE SCHOOL YEAR
CREATE TABLE ciclo(
id VARCHAR(10),
anno int,
numero int,
fechaInicio VARCHAR(10),
fechaFinal VARCHAR(10),
CONSTRAINTS pkCiclo PRIMARY KEY (id)
);

--TABLE COURSE
CREATE TABLE curso(
id VARCHAR(10),
codigo VARCHAR(10),
nombre VARCHAR(50),
creditos int,
horasSemanales int,
CONSTRAINTS pkCurso PRIMARY KEY (id)
);

--TABLE USER
CREATE TABLE usuario(
id VARCHAR(10),
cedula VARCHAR(10),
contrasena VARCHAR(8),
CONSTRAINTS pkUsuario PRIMARY KEY (id)
);

--TABLE PROFESSOR
CREATE TABLE profesor(
id VARCHAR(10),
nombre VARCHAR(50),
correo VARCHAR(25),
telefono int,
usuarioId VARCHAR(10) not null,
CONSTRAINTS pkProfesor PRIMARY KEY (id),
CONSTRAINTS fkUsuario
  FOREIGN KEY (usuarioId)
  REFERENCES usuario(id) ON DELETE CASCADE
);

--TABLE CARRER
CREATE TABLE carrera(
id VARCHAR(10),
codigo VARCHAR(50),
nombre VARCHAR(25),
titulo VARCHAR(50),
CONSTRAINTS pkCarrera PRIMARY KEY (id)
);

--TABLE COURSES_LIST
CREATE TABLE listaCurso(
id VARCHAR(10),
anno VARCHAR(50),
carreraId VARCHAR(10) not null,
cicloId VARCHAR(10) not null,
cursoId VARCHAR(10) not null,
CONSTRAINTS pkListaCurso PRIMARY KEY (id),
CONSTRAINTS fkCiclo
  FOREIGN KEY (cicloId)
  REFERENCES ciclo(id),
CONSTRAINTS fkCarrera
  FOREIGN KEY (carreraId)
  REFERENCES carrera(id),
CONSTRAINTS fkCurso
  FOREIGN KEY (cursoId)
  REFERENCES curso(id)
);


INSERT INTO usuario values('1','304740935','12345678');
INSERT INTO profesor values('1','Georges','george@example.com',222222, 1);

INSERT INTO ciclo values('1',2018, 1, '6/02/2018', '6/06/2018');
INSERT INTO ciclo values('2',2018, 1, '6/02/2018', '6/06/2018');

INSERT INTO curso values('1','123','Mate I', 3, 8);
INSERT INTO curso values('2','456','Mate II', 3, 12);

INSERT INTO carrera values('1','INF','Informatica', 'Bachiller');
INSERT INTO carrera values('2','BIO','Biologia', 'Licenciatura');

INSERT INTO listaCurso values('1','2019',1, 1, 1);
INSERT INTO listaCurso values('2','2018',2, 2, 1);
INSERT INTO listaCurso values('3','2019',1, 1, 2);


--------------------------------- Mantenimientos ---------------------------------
--CURSOR REFERENCE
CREATE OR REPLACE PACKAGE types
AS
  TYPE ref_cursor IS REF CURSOR;
END;
/

-- 1 - Mantenimiento de Profesores (búsqueda por nombre y cédula)
-- PROFESSOR INDEX
CREATE OR REPLACE FUNCTION listarProfesores
RETURN Types.ref_cursor
AS
  cursorProfesor types.ref_cursor;
BEGIN
  OPEN cursorProfesor FOR
    select p.id, p.nombre, u.cedula, p.correo, u.contrasena, p.telefono from profesor p join usuario u on p.id=u.id;
  RETURN cursorProfesor;
END;
/

-- INSERT PROFESSOR
CREATE OR REPLACE PROCEDURE insertarProfesor(id IN profesor.id%TYPE, nombre IN profesor.nombre%TYPE, correo IN profesor.correo%TYPE,
telefono IN profesor.telefono%TYPE, usuarioId IN usuario.id%TYPE, usuarioCedula IN usuario.cedula%TYPE, usuarioContrasena IN usuario.contrasena%TYPE)
AS
BEGIN
  INSERT INTO usuario values (usuarioId, usuarioCedula, usuarioContrasena);
  INSERT INTO profesor values (id, nombre, correo, telefono, usuarioId);
END;
/

-- SHOW PROFESSOR
CREATE OR REPLACE FUNCTION buscarProfesor(idProfesor IN varchar)
RETURN Types.ref_cursor
AS
  cursorProfesor types.ref_cursor;
BEGIN
  OPEN cursorProfesor FOR
    select p.id, p.nombre, u.cedula, p.correo, u.contrasena, p.telefono from profesor p join usuario u on p.id=u.id WHERE p.id=idProfesor;
RETURN cursorProfesor;
END;
/

-- UPDATE PROFESSOR
CREATE OR REPLACE PROCEDURE modificarProfesor(idProfesor IN profesor.id%TYPE, nombreNuevo IN profesor.nombre%TYPE, correoNuevo IN profesor.correo%TYPE,
telefonoNuevo IN profesor.telefono%TYPE, idUsuario IN usuario.id%TYPE, usuarioCedulaNueva IN usuario.cedula%TYPE, usuarioContrasenaNueva IN usuario.contrasena%TYPE)
AS
BEGIN
  UPDATE usuario SET id=idUsuario, cedula=usuarioCedulaNueva, contrasena=usuarioContrasenaNueva WHERE id=idUsuario;
  UPDATE profesor SET nombre=nombreNuevo, correo=correoNuevo, telefono=telefonoNuevo, usuarioID=idUsuario WHERE id=idProfesor;
END;
/

-- SHOW PROFESSOR FIND BY NAME
CREATE OR REPLACE FUNCTION buscarNombres(nombrebuscar IN profesor.nombre%TYPE)
RETURN Types.ref_cursor
AS
  cursorProfesor types.ref_cursor;
BEGIN
  OPEN cursorProfesor FOR
    select p.id, p.nombre, u.cedula, p.correo, u.contrasena, p.telefono from profesor p join usuario u on p.id=u.id WHERE p.nombre=nombrebuscar;
RETURN cursorProfesor;
END;
/

-- SHOW PROFESSOR FIND BY Identify Card
CREATE OR REPLACE FUNCTION buscarCedulaProfesor(cedulaBuscar IN usuario.cedula%TYPE)
RETURN Types.ref_cursor
AS
  cursorProfesor types.ref_cursor;
BEGIN
  OPEN cursorProfesor FOR
    select p.id, p.nombre, u.cedula, p.correo, u.contrasena, p.telefono from profesor p, usuario u where u.cedula=cedulaBuscar;
RETURN cursorProfesor;
END;
/

-- DESTROY PROFESOR
create or replace procedure eliminarProfesor(idProfesor IN varchar)
as
begin
  delete from usuario where id=idProfesor;
end;
/

-- 2 - Mantenimiento de cursos (búsqueda por nombre, código y por carrera (FALTA)).
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

-- INSERT COURSE
CREATE OR REPLACE PROCEDURE insertarCurso(id IN curso.id%TYPE, codigo IN curso.codigo%TYPE, nombre IN curso.nombre%TYPE, creditos IN curso.creditos%TYPE,
horasSemanales IN curso.horasSemanales%TYPE)
AS
BEGIN
	INSERT INTO curso VALUES(id, codigo, nombre, creditos, horasSemanales);
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

-- UPDATE COURSE
CREATE OR REPLACE PROCEDURE modificarCurso(idCurso IN curso.id%TYPE, codigoNuevo IN curso.codigo%TYPE, nombreNuevo IN curso.nombre%TYPE, creditosNuevo IN curso.creditos%TYPE,
horasSemanalesNuevo IN curso.horasSemanales%TYPE)
AS
BEGIN
  UPDATE curso SET codigo=codigoNuevo, nombre=nombreNuevo, creditos=creditosNuevo, horasSemanales=horasSemanalesNuevo WHERE id=idCurso;
END;
/

-- DESTROY COURSE
create or replace procedure eliminarCurso(idCurso IN varchar)
as
begin
  delete from curso where id=idCurso;
end;
/

-- SHOW COURSE BY NAME
CREATE OR REPLACE FUNCTION buscarCursoNombre(nombreBuscar IN curso.nombre%TYPE)
RETURN Types.ref_cursor
AS
  cursorCurso types.ref_cursor;
BEGIN
  OPEN cursorCurso FOR
    SELECT id, codigo, nombre, creditos, horasSemanales from curso WHERE nombre=nombreBuscar;
RETURN cursorCurso;
END;
/

-- SHOW COURSE FIND BY NAME
CREATE OR REPLACE FUNCTION buscarCursoCodigo(codigoBuscar IN curso.codigo%TYPE)
RETURN Types.ref_cursor
AS
  cursorCurso types.ref_cursor;
BEGIN
  OPEN cursorCurso FOR
    SELECT id, codigo, nombre, creditos, horasSemanales from curso WHERE codigo=codigoBuscar;
RETURN cursorCurso;
END;
/

-- SHOW COURSE FIND BY CARRER
CREATE OR REPLACE FUNCTION listarCursosPorCarrera(idCarrera IN varchar)
RETURN Types.ref_cursor
AS
  cursorListaCursosCarrera types.ref_cursor;
BEGIN
  OPEN cursorListaCursosCarrera FOR
    select anno, carreraId "Carrera" , anno, cursoId "Curso", cicloId "Ciclo" from listaCurso where carreraId=idCarrera;
  RETURN cursorListaCursosCarrera;
END;
/

-- 3 - Mantenimiento de carreras (búsqueda por nombre y código),
-- al editar una carrera debe poderse dar mantenimiento a la lista de cursos que la forman(CURSOS CRUD and SORT)

-- INDEX CARRER
CREATE OR REPLACE FUNCTION listarCarreras
RETURN Types.ref_cursor
AS
  cursorCarrera types.ref_cursor;
BEGIN
  OPEN cursorCarrera FOR
    SELECT id, codigo, nombre, titulo from carrera;
  RETURN cursorCarrera;
END;
/

-- INSERT CARRER
CREATE OR REPLACE PROCEDURE insertarCarrera(id IN carrera.id%TYPE, codigo IN carrera.codigo%TYPE, nombre IN carrera.nombre%TYPE,
titulo IN carrera.titulo%TYPE)
AS
BEGIN
	INSERT INTO carrera VALUES(id, codigo, nombre, titulo);
END;
/

-- SHOW CARRER
CREATE OR REPLACE FUNCTION buscarCarrera(idCarrera IN varchar)
RETURN Types.ref_cursor
AS
  cursorCarrera types.ref_cursor;
BEGIN
  OPEN cursorCarrera FOR
    SELECT id, codigo, nombre, titulo from carrera WHERE id=idCarrera;
RETURN cursorCarrera;
END;
/

-- UPDATE CARRER
CREATE OR REPLACE PROCEDURE modificarCarrera(idCarrera IN carrera.id%TYPE, codigoNuevo IN carrera.codigo%TYPE, nombreNuevo IN carrera.nombre%TYPE,
tituloNuevo IN carrera.titulo%TYPE)
AS
BEGIN
  UPDATE ciclo SET codigo=codigoNuevo, nombre=nombreNuevo, titulo=tituloNuevo WHERE id=idCarrera;
END;
/


-- SHOW CARRER FIND BY NAME
CREATE OR REPLACE FUNCTION buscarCarreraNombre(nombreCarrera IN varchar)
RETURN Types.ref_cursor
AS
  cursorCarrera types.ref_cursor;
BEGIN
  OPEN cursorCarrera FOR
    SELECT id, codigo, nombre, titulo from carrera WHERE nombre=nombreCarrera;
RETURN cursorCarrera;
END;
/

-- SHOW CARRER FIND BY CODE
CREATE OR REPLACE FUNCTION buscarCodeNombre(codigoCarrera IN varchar)
RETURN Types.ref_cursor
AS
  cursorCarrera types.ref_cursor;
BEGIN
  OPEN cursorCarrera FOR
    SELECT id, codigo, nombre, titulo from carrera WHERE codigo=codigoCarrera;
RETURN cursorCarrera;
END;
/

-- al editar una carrera debe poderse dar mantenimiento a la lista de cursos que la forman(CURSOS CRUD and SORT)
CREATE OR REPLACE FUNCTION listarCursosPorCarrera(idCarrera IN varchar)
RETURN Types.ref_cursor
AS
  cursorListaCursosCarrera types.ref_cursor;
BEGIN
  OPEN cursorListaCursosCarrera FOR
    select anno, carreraId "Carrera" , anno, cursoId "Curso", cicloId "Ciclo" from listaCurso where carreraId=idCarrera;
  RETURN cursorListaCursosCarrera;
END;
/

-- COURSE BY CARRER SORT BY COLUMN (HAY QUE DEFINIRLO EN EL FRONTEND)
-- CREATE OR REPLACE FUNCTION listarCursosPorCarrera(idCarrera IN varchar)
-- RETURN Types.ref_cursor
-- AS
--   cursorListaCursosCarrera types.ref_cursor;
-- BEGIN
--   OPEN cursorListaCursosCarrera FOR
--     select anno, carreraId "Carrera" , anno, cursoId "Curso", cicloId "Ciclo" from listaCurso where carreraId=idCarrera ORDER BY [COLUMNA];
--   RETURN cursorListaCursosCarrera;
-- END;
-- /


-- 4 - Mantenimiento de ciclos (búsqueda por año). Debe además poderse seleccionar un ciclo como el ciclo activo, ese será el ciclo “default” al preparar la oferta académica, al matricular y al registrar notas.
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

-- INSERT SCHOOL YEAR
CREATE OR REPLACE PROCEDURE insertarCiclo(id IN ciclo.id%TYPE, anno IN ciclo.anno%TYPE, numero IN ciclo.numero%TYPE,
fechaInicio IN ciclo.fechaInicio%TYPE, fechaFinal IN ciclo.fechaFinal%TYPE)
AS
BEGIN
	INSERT INTO ciclo VALUES(id, anno, numero, fechaInicio, fechaFinal);
END;
/

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

-- UPDATE SCHOOL YEAR
CREATE OR REPLACE PROCEDURE modificarCiclo(idCiclo IN ciclo.id%TYPE, annoNuevo IN ciclo.anno%TYPE, numeroNuevo IN ciclo.numero%TYPE,
fechaInicioNuevo IN ciclo.fechaInicio%TYPE, fechaFinalNuevo IN ciclo.fechaFinal%TYPE)
AS
BEGIN
  UPDATE ciclo SET anno=annoNuevo, numero=numeroNuevo, fechaInicio=fechaInicioNuevo, fechaFinal=fechaFinalNuevo WHERE id=idCiclo;
END;
/

-- DESTROY SCHOOL YEAR
create or replace procedure eliminarCiclo(idCiclo IN varchar)
as
begin
  delete from ciclo where id=idCiclo;
end;
/



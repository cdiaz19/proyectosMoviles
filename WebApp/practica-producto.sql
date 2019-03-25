conn system/root
spool practica-producto.log
drop tablespace TB_PRACTICA_PRODUCTO including contents and datafiles;
CREATE TABLESPACE TB_PRACTICA_PRODUCTO datafile 'C:\oraclexe\app\oracle\oradata\XE\TB_PRODUCTO.DBF'
size 1M REUSE AUTOEXTEND ON;
drop table producto;
create table producto
(codigo varchar2(10),nombre varchar2(20), importado int,precio float,tipo VARCHAR2(15)) tablespace TB_PRACTICA_PRODUCTO;
alter table producto add constraint 
producto_pk primary key(codigo);
insert into producto (codigo,nombre,importado,precio,tipo) values ('01', 'cereal',0,2600.00,'lujo');
commit;

CREATE OR REPLACE PACKAGE types
AS
     TYPE ref_cursor IS REF CURSOR;
END;
/

CREATE OR REPLACE PROCEDURE insertarProducto(codigo IN producto.codigo%TYPE,nombre IN producto.nombre%TYPE,importado IN producto.importado%TYPE,precio IN producto.precio%TYPE, tipo IN producto.tipo%TYPE)
AS
BEGIN
	INSERT INTO producto VALUES(codigo,nombre,importado,precio,tipo);
END;
/
CREATE OR REPLACE PROCEDURE modificarProducto (codigoin IN producto.codigo%TYPE,nombrein IN producto.nombre%TYPE,importadoin IN producto.importado%TYPE,precioin IN producto.precio%TYPE, tipoin IN producto.tipo%TYPE)
AS
BEGIN
UPDATE producto SET codigo=codigoin,nombre=nombrein,importado=importadoin,precio=precioin, tipo=tipoin WHERE nombre=nombrein;
END;
/
CREATE OR REPLACE PROCEDURE eliminarProducto(codigoin IN producto.codigo%TYPE)
AS
BEGIN
    DELETE FROM producto WHERE codigo=codigoin;
END;
/
CREATE OR REPLACE FUNCTION buscarProducto(codigobuscar IN producto.codigo%TYPE)
RETURN Types.ref_cursor 
AS 
        producto_cursor types.ref_cursor; 
BEGIN 
  OPEN producto_cursor FOR 
       SELECT codigo,nombre,importado,precio,tipo FROM producto WHERE codigo=codigobuscar; 
RETURN producto_cursor; 
END;
/
CREATE OR REPLACE FUNCTION listarProducto
RETURN Types.ref_cursor 
AS 
        producto_cursor types.ref_cursor; 
BEGIN 
  OPEN producto_cursor FOR 
       SELECT codigo,nombre,importado,precio,tipo FROM producto ; 
RETURN producto_cursor; 
END;
/
CREATE OR REPLACE FUNCTION buscarNombres(nombrebuscar IN producto.nombre%TYPE)
RETURN Types.ref_cursor 
AS 
        producto_cursor types.ref_cursor; 
BEGIN 
  OPEN producto_cursor FOR 
       SELECT codigo,nombre,importado,precio,tipo FROM producto WHERE nombre=nombrebuscar; 
RETURN producto_cursor; 
END;
/
CREATE OR REPLACE FUNCTION buscarTipos(tiposbuscar IN producto.tipo%TYPE)
RETURN Types.ref_cursor 
AS 
        producto_cursor types.ref_cursor; 
BEGIN 
  OPEN producto_cursor FOR 
       SELECT codigo,nombre,importado,precio,tipo FROM producto WHERE tipo=tiposbuscar; 
RETURN producto_cursor; 
END;
/
spool off
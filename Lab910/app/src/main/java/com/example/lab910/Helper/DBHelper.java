package com.example.lab910.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lab910.LogicaDeNegocio.Curso;
import com.example.lab910.LogicaDeNegocio.Estudiante;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
  private static final String ESTUDIANTES_TABLE_CREATE = "CREATE TABLE estudiantes(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR(30), apellidos TEXT, edad INTEGER)";
  private static final String CURSOS_TABLE_CREATE = "CREATE TABLE cursos(_id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion VARCHAR(30), creditos INTEGER, FOREIGN KEY(estudianteId) REFERENCES estudiante(id))";

  private static final String DB_NAME = "database.sqlite";
  private static final int DB_VERSION = 1;
  private SQLiteDatabase db;

  public DBHelper(Context context) {
    super(context, DB_NAME, null, DB_VERSION);
    db=this.getWritableDatabase();
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(ESTUDIANTES_TABLE_CREATE);
    db.execSQL(CURSOS_TABLE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  }

  //Insertar un nuevo estudiante
  public void insertarEstudiante(String nombre, String apellidos, int edad){
    ContentValues cv = new ContentValues();
    cv.put("nombre", nombre);
    cv.put("apellidos", apellidos);
    cv.put("edad", edad);
    db.insert("estudiantes", null, cv);
  }

  //Borrar un estudiante a partir de su id
  public void borrarEstudiante(int id){
    String[] args = new String[]{String.valueOf(id)};
    db.delete("estudiantes", "_id=?", args);
  }

  //Actualizar un estudiante
  public void actualizarEstudiante(int id, String nombre, String apellidos, int edad) {
    System.out.println(nombre);
    System.out.println(apellidos);
    System.out.println(edad);
    ContentValues cv = new ContentValues();
    cv.put("nombre", nombre);
    cv.put("apellidos", apellidos);
    cv.put("edad", edad);

    db.update("estudiantes", cv, "_id="+id, null );
  }

  //Obtener la lista de Estudiantes en la base de datos
  public ArrayList<Estudiante> getEstudiantes(){
    //Creamos el cursor
    ArrayList<Estudiante> lista = new ArrayList<Estudiante>();
    Cursor c = db.rawQuery("select _id, nombre, apellidos, edad from estudiantes", null);
    if (c != null && c.getCount()>0) {
      c.moveToFirst();
      do {
        //Asignamos el valor en nuestras variables para crear un nuevo objeto Estudiante
        String nombre = c.getString(c.getColumnIndex("nombre"));
        String apellido = c.getString(c.getColumnIndex("apellidos"));
        int edad = Integer.parseInt(c.getString(c.getColumnIndex("edad")));
        int id = c.getInt(c.getColumnIndex("_id"));

        Estudiante com = new Estudiante(id, nombre, apellido, edad);

        //Añadimos el Estudiante a la lista
        lista.add(com);
      } while (c.moveToNext());
    }

    //Cerramos el cursor
    c.close();
    return lista;
  }

  // Estudiante por nombre
  public Estudiante buscarNombreEstudiante(String argument) {
    Cursor c = db.rawQuery("select _id, nombre, apellidos, edad from estudiantes where nombre =?", new String[]{argument});
    Estudiante e = new Estudiante();

    if(c != null)
    {
      while(c.moveToNext()){
        String nombre = c.getString(c.getColumnIndex("nombre"));
        String apellidos = c.getString(c.getColumnIndex("apellidos"));
        int edad = c.getInt(c.getColumnIndex("edad"));
        int id = c.getInt(c.getColumnIndex("_id"));

        e.setId(id);
        e.setNombre(nombre);
        e.setApellidos(apellidos);
        e.setEdad(edad);
      }
    }
    return e;
  }

  //Insertar un nuevo Curso
  public void insertarCurso(String descripcion, int creditos, int estudianteId){
    db.execSQL("INSERT INTO cursos (descripcion, creditos, estudianteId) VALUES (" + descripcion + creditos + estudianteId + ")");
  }

  public ArrayList<Curso> getCursos(){
    //Creamos el cursor
    ArrayList<Curso> lista = new ArrayList<Curso>();
    Cursor c = db.rawQuery("select _id, descripcion, creditos, estudianteId from cursos", null);
    if (c != null && c.getCount()>0) {
      c.moveToFirst();
      do {
        // Asignamos el valor en nuestras variables para crear un nuevo objeto Estudiante
        String descripcion = c.getString(c.getColumnIndex("creditos"));
        int creditos = Integer.parseInt(c.getString(c.getColumnIndex("creditos")));
        int id = c.getInt(c.getColumnIndex("_id"));
        int estudiante = c.getInt(c.getColumnIndex("estudianteId"));

        Curso com = new Curso(id, creditos, descripcion, estudiante);

        // Añadimos el Estudiante a la lista
        lista.add(com);
      } while (c.moveToNext());
    }

    // Cerramos el cursor
    c.close();
    return lista;
  }
}

package com.academiasmoviles.appacademia.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.academiasmoviles.appacademia.Model.Alumno;

import java.io.File;
import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "appacademia.db";
    private static final int DATABASE_VERSION = 2;
    private String DB_PATH = null;
    private Context mContext;

    public static DBHelper GetDBHelper(Context context)
    {
        DBHelper dbHelper = new DBHelper(context);

        if (!dbHelper.isDataBaseExist())
        {
            dbHelper.deleteAllAlumno();
            dbHelper.createDataBase();
        }

        return dbHelper;
    }

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        mContext = context;

        DB_PATH = "/data/data/" + mContext.getPackageName() + "/databases/";
    }

    private void createDataBase()
    {
        boolean isExist = isDataBaseExist();

        if (!isExist)
        {
            this.getReadableDatabase();

            onCreate(this.getWritableDatabase());
        }
    }

    public boolean isDataBaseExist()
    {
        File file = new File(DB_PATH + DATABASE_NAME);

        return file.exists();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Escribir la estructura de la bd: Tablas, ...
        db.execSQL(" CREATE TABLE alumno (id TEXT primary key, nombres TEXT, apellidos TEXT, email TEXT, direccion TEXT, latitud TEXT, longitud TEXT); ");
        // ....
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Escribir las modificaciones en la bd.
        db.execSQL(" DROP TABLE IF EXISTS alumno; ");

        onCreate(db);
    }


    /* IMPLEMENTACIÓN: MÉTODOS CRUD */

    /* TABLA: alumno */

    private static final String TABLE_NAME_ALUMNO = "alumno";

    public boolean addAlumno(Alumno alumno)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", alumno.get_id());
        contentValues.put("nombres", alumno.getNombres());
        contentValues.put("apellidos", alumno.getApellidos());
        contentValues.put("email", alumno.getEmail());
        contentValues.put("direccion", alumno.getDireccion());
        contentValues.put("latitud", alumno.getLatitud());
        contentValues.put("longitud", alumno.getLongitud());

        db.insert(TABLE_NAME_ALUMNO, null, contentValues);

        return true;
    }

    public boolean updateAlumno(Alumno alumno)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", alumno.get_id());
        contentValues.put("nombres", alumno.getNombres());
        contentValues.put("apellidos", alumno.getApellidos());
        contentValues.put("email", alumno.getEmail());
        contentValues.put("direccion", alumno.getDireccion());
        contentValues.put("latitud", alumno.getLatitud());
        contentValues.put("longitud", alumno.getLongitud());

        db.update(TABLE_NAME_ALUMNO, contentValues, "id = ?",
                new String[]{ alumno.get_id() });

        return true;
    }

    public boolean deleteAlumno(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME_ALUMNO, "id = ?",
                new String[]{ id });

        return true;
    }

    public boolean deleteAllAlumno()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME_ALUMNO, "",
                new String[]{  });

        return true;
    }

    public Alumno getAlumno(String id)
    {
        Alumno alumno = new Alumno();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE_NAME_ALUMNO + " WHERE id = '" + id + "'", null);

        cursor.moveToFirst();

        while (cursor.isAfterLast() == false)
        {
            alumno.set_id(cursor.getString(cursor.getColumnIndex("id")));
            alumno.setNombres(cursor.getString(cursor.getColumnIndex("nombres")));
            alumno.setApellidos(cursor.getString(cursor.getColumnIndex("apellidos")));
            alumno.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            alumno.setDireccion(cursor.getString(cursor.getColumnIndex("direccion")));
            alumno.setLatitud(cursor.getString(cursor.getColumnIndex("latitud")));
            alumno.setLongitud(cursor.getString(cursor.getColumnIndex("longitud")));

            cursor.moveToNext();
        }

        return alumno;
    }

    public ArrayList<Alumno> listAlumnos()
    {
        ArrayList<Alumno> lista = new ArrayList<>();

        Alumno alumno;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE_NAME_ALUMNO, null);

        cursor.moveToFirst();

        while (cursor.isAfterLast() == false)
        {
            alumno = new Alumno();

            alumno.set_id(cursor.getString(cursor.getColumnIndex("id")));
            alumno.setNombres(cursor.getString(cursor.getColumnIndex("nombres")));
            alumno.setApellidos(cursor.getString(cursor.getColumnIndex("apellidos")));
            alumno.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            alumno.setDireccion(cursor.getString(cursor.getColumnIndex("direccion")));
            alumno.setLatitud(cursor.getString(cursor.getColumnIndex("latitud")));
            alumno.setLongitud(cursor.getString(cursor.getColumnIndex("longitud")));

            lista.add(alumno);

            cursor.moveToNext();
        }

        return lista;
    }
}

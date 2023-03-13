package com.example.proyecto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class BaseDatos extends SQLiteOpenHelper {

    String  tabla="CREATE TABLE EMPLEADOS(DNI TEXT PRIMARY KEY, NOMBRE TEXT, APELLIDO TEXT, FECHANACIMIENTO DATE, DIRECCION TEXT, TELEFONO NUMBER, DEPARTAMENTO TEXT)";
    public BaseDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Creo mi tabla
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table empleados");
        db.execSQL(tabla);

    }
}

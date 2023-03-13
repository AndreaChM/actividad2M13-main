package com.example.appempleadosentrega;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String EmpleadosDB, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, EmpleadosDB, factory, version);
        Log.i("DATABASE", "Constructor de MiBaseDeDatosHelper llamado");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREAR_USUARIOS =
                "CREATE TABLE usuarios (" +
                        "username TEXT PRIMARY KEY," +
                        "password TEXT)";



        db.execSQL(CREAR_USUARIOS);


        final String CREAR_ADMIN =
                "CREATE TABLE admin (" +
                        "usernameAdmin TEXT PRIMARY KEY," +
                        "passwordAdmin TEXT)";

        db.execSQL(CREAR_ADMIN);

        final String CREAR_EMPLEADOS =

                "CREATE TABLE EMPLEADOS (" +
                        "DNI TEXT PRIMARY KEY," +
                        "NOMBRE TEXT," +
                        "APELLIDO TEXT," +
                        "DIRECCION TEXT," +
                        " TELEFONO TEXT," +
                        " username TEXT," +
                        " usernameAdmin TEXT," +
                        " FOREIGN KEY (username) REFERENCES usuarios(username)," +
                        " FOREIGN KEY (usernameAdmin) REFERENCES admin(usernameAdmin))";


        db.execSQL(CREAR_EMPLEADOS);


        String usernameAdmin = "admin1";
        String passwordAdmin  = "1";
        ContentValues userAdmin = new ContentValues();
        userAdmin.put("usernameAdmin", usernameAdmin);
        userAdmin.put("passwordAdmin", passwordAdmin);
        db.insert("admin",null, userAdmin);






    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}



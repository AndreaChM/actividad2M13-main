package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Lista extends AppCompatActivity {

    ListView listView;
    ArrayList <String> listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        listView = (ListView) findViewById(R.id.listView);


    }

    //Método
    private void MostrarListado(){
        listado = ListaEmpleados();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listado);
        listView.setAdapter(adapter);
    }


    //Método para listar registros de la tabla
    //Conectamos con los datos de nuestra bd y hacemos una consulta.
    private ArrayList<String> ListaEmpleados(){
        ArrayList<String> lista = new ArrayList<String>();
        BaseDatos helper = new BaseDatos(this,"Demo",null,1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql= "select DNI, Nombre, Apellido from Empleados";

        //Creamos un cursor para movernos entre nuestros datos.

        Cursor c= db.rawQuery(sql,null);
        if (c.moveToFirst()){

            do{
                String linea = c.getInt(0)+""+ c.getString(1)+""+c.getString(2);
                lista.add(linea);
            }while (c.moveToNext());
        }
        db.close();
        return lista;
    }
}
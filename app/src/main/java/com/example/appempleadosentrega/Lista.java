package com.example.appempleadosentrega;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Lista extends AppCompatActivity{

    ListView listView;
    ArrayList<String> listado;

    @Override
    protected void onPostResume(){
        super.onPostResume();
        MostrarListado();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        listView = (ListView) findViewById(R.id.listView);

        MostrarListado();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                String DNI = listado.get(position).split(" ")[0];
                String nombre = listado.get(position).split(" ")[1];
                String apellido = listado.get(position).split(" ")[2];
                String direccion = listado.get(position).split(" ")[3];
                String telefono = listado.get(position).split(" ")[4];
                Intent intent = new Intent(Lista.this, Modificar.class);
                intent.putExtra("DNI",DNI);
                intent.putExtra("Nombre",nombre);
                intent.putExtra("Apellido",apellido);
                intent.putExtra("Direccion",direccion);
                intent.putExtra("Telefono",telefono);
                startActivity(intent);

            }
        });

        //Acción visualizar el botón de volver atrás
        if (getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    //


    //Método para cuando se clique el boton hacia atras regrese a la página principal
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //
    private void MostrarListado(){
        listado = ListaEmpleados();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listado);
        listView.setAdapter(adapter);

    }

    //Método para listar registros de la tabla
    //Conectamos con los datos de nuestra bd y hacemos una consulta.
    private ArrayList<String> ListaEmpleados(){
        ArrayList<String> lista = new ArrayList<String>();
        AdminSQLiteOpenHelper helper = new AdminSQLiteOpenHelper(this,"usuario1",null,1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql= "select * from Empleados";

        //Creamos un cursor para movernos entre nuestros datos.
        Cursor c= db.rawQuery(sql,null);
        if (c.moveToFirst()){

            do{
                String linea = c.getString(0)+" "+c.getString(1)+" "+c.getString(2)+" "+c.getString(3)+" "+c.getString(4);
                lista.add(linea);
            }while (c.moveToNext());
        }
        db.close();
        return lista;
    }
}

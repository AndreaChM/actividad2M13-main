package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etDNI, etName, etApellidos, etDireccion, etTelefono;
    Button btGuardar, btListado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDNI = (EditText) findViewById(R.id.etDNI);
        etName = (EditText) findViewById(R.id.etName);
        etApellidos = (EditText) findViewById(R.id.etApellido);
        etDireccion = (EditText) findViewById(R.id.etDireccion);
        etTelefono = (EditText) findViewById(R.id.etTelefono);

        btGuardar = (Button) findViewById(R.id.btGuardar);
        btListado = (Button) findViewById(R.id.btListado);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar(etDNI.getText().toString(),etName.getText().toString(),etApellidos.getText().toString(),etDireccion.getText().toString(),etTelefono.getText().toString());
            }
        });

        btListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Lista.class));
            }
        });



    }

    private void guardar(String DNI,String Nombre, String Apellido, String Direccion, String Telefono){
        BaseDatos helper = new BaseDatos(this,"Demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            ContentValues c = new ContentValues();
            c.put("DNI", DNI);
            c.put("Nombre", Nombre);
            c.put("Apellido",Apellido);
            c.put("Direccion", Direccion);
            c.put("Telefono", Telefono);
            db.insert("EMPLEADOS",null,c);
            db.close();
            Toast.makeText(this,"Empleado ingresado correctamente",Toast.LENGTH_LONG).show();
        }catch (Exception e){

        }
    }
}
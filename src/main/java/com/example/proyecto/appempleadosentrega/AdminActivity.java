package com.example.appempleadosentrega;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    EditText etDNI, etName, etApellidos, etDireccion, etTelefono;
    Button btGuardar, btListado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        String username = getIntent().getStringExtra("usernameAdmin");

        Toast.makeText(this, "Bienvenido " +username+ " estás en modo Admin", Toast.LENGTH_SHORT).show();

        etDNI = (EditText) findViewById(R.id.etDNI1);
        etName = (EditText) findViewById(R.id.etName);
        etApellidos = (EditText) findViewById(R.id.etApellido1);
        etDireccion = (EditText) findViewById(R.id.etDNI1);
        etTelefono = (EditText) findViewById(R.id.etTelefono1);

        btGuardar = (Button) findViewById(R.id.btModificar1);
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
                startActivity(new Intent(AdminActivity.this, Lista.class));
            }
        });
    }

    private void guardar(String DNI,String Nombre, String Apellido, String Direccion, String Telefono){
        AdminSQLiteOpenHelper helper = new AdminSQLiteOpenHelper(this,"usuario1",null,1);
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
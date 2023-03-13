package com.example.appempleadosentrega;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Empleado extends AppCompatActivity {

    EditText etDNI1, etNombre1, etApellidos1, etDireccion1, etTelefono1;
    Button btGuardar1, btModificar1, btBorrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado);

        String username = getIntent().getStringExtra("username");
        Toast.makeText(this, "Bienvenido " +username, Toast.LENGTH_SHORT).show();

        etDNI1 = (EditText) findViewById(R.id.etDNI1);
        etNombre1 = (EditText) findViewById(R.id.etNombre1);
        etApellidos1 = (EditText) findViewById(R.id.etApellido1);
        etDireccion1 = (EditText) findViewById(R.id.etDireccion1);
        etTelefono1 = (EditText) findViewById(R.id.etTelefono1);

        btGuardar1 = (Button) findViewById(R.id.btGuardar1);
        btModificar1 = (Button) findViewById(R.id.btModificar1);


        btGuardar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar(etDNI1.getText().toString(),etNombre1.getText().toString(),etApellidos1.getText().toString(),etDireccion1.getText().toString(),etTelefono1.getText().toString());
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
            Toast.makeText(this,"Usuario creado correctamente",Toast.LENGTH_LONG).show();

            etDNI1.setText("");
            etNombre1.setText("");
            etApellidos1.setText("");
            etDireccion1.setText("");
            etTelefono1.setText("");
        }catch (Exception e){

        }
    }
}
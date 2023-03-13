package com.example.appempleadosentrega;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Modificar extends AppCompatActivity {

    EditText etDNI,etNombre, etApellido, etDireccion, etTelefono;
    Button btModificar, btBorrar;
    String DNI, nombre, apellido, direccion, telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        etDNI = (EditText) findViewById(R.id.etDNI);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellido = (EditText) findViewById(R.id.etApellido);
        etDireccion = (EditText) findViewById(R.id.etDireccion);
        etTelefono = (EditText) findViewById(R.id.etTelefono);

        //paso los datos recogidos en el intent
        Bundle bun = getIntent().getExtras();
        DNI = bun.getString("DNI");
        nombre = bun.getString("Nombre");
        apellido = bun.getString("Apellido");
        direccion = bun.getString("Direccion");
        telefono = bun.getString("Telefono");

        etDNI.setText(DNI);
        etNombre.setText(nombre);
        etApellido.setText(apellido);
        etDireccion.setText(direccion);
        etTelefono.setText(telefono);

        btModificar = (Button) findViewById(R.id.btModificar);
        btBorrar = (Button) findViewById(R.id.btBorrar);

        btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Borrar(DNI);
                onBackPressed();
            }
        });

        btModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Modificar(etDNI.getText().toString(),etNombre.getText().toString(),etApellido.getText().toString(),etDireccion.getText().toString(),etTelefono.getText().toString());
                onBackPressed();
            }
        });
    }

    private void Modificar (String DNI, String Nombre, String Apellido, String Direccion, String Telefono){
        AdminSQLiteOpenHelper helper = new AdminSQLiteOpenHelper(Modificar.this,"usuario1",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "update Empleados set DNI='"+DNI+"',Nombre= '"+ Nombre + "',Apellido='" + Apellido +"',Direccion= '"+ Direccion + "',Telefono= '"+ Telefono + "' where DNI="+DNI;
        db.execSQL(sql);
        db.close();
    }

    private void Borrar (String DNI){
        AdminSQLiteOpenHelper helper = new AdminSQLiteOpenHelper(this,"Demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "delete from Empleados  where DNI="+DNI;
        db.execSQL(sql);
        db.close();
    }

}

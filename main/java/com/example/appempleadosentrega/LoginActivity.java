package com.example.appempleadosentrega;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText TxtUsernameInicioSesion, TxtPasswordInicioSesion;
    Button BtnInicioSesion, BtnPagRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Verificar si se ha registrado un nuevo usuario
        if (getIntent().getBooleanExtra("nuevoRegistro", false)) {
            // Obtener el nombre de usuario del extra del intent
            String username = getIntent().getStringExtra("username");

            // Mostrar el nombre de usuario en la pantalla de inicio de sesión
            EditText UsernameRegistrado = findViewById(R.id.TxtUsernameInicioSesion);
            UsernameRegistrado.setText(username);
        }

        TxtUsernameInicioSesion = findViewById(R.id.TxtUsernameInicioSesion) ;
        TxtPasswordInicioSesion = findViewById(R.id.TxtPasswordInicioSesion) ;
        BtnInicioSesion = findViewById(R.id.BtnInicioSesion);
        BtnPagRegistrarse = findViewById(R.id.BtnPagRegistrarse);

        BtnInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Instancio de la conexión con la Base de datos
                AdminSQLiteOpenHelper adminHelper = new AdminSQLiteOpenHelper(LoginActivity.this, "usuarios1", null, 1);
                //Abro la conexión de base de datos, con permisos de escritura para realizar las altas
                SQLiteDatabase db = adminHelper.getReadableDatabase();

                //Leo el nombre y contraseña del usuario
                String username = TxtUsernameInicioSesion.getText().toString();
                String password = TxtPasswordInicioSesion.getText().toString();


                //Campo usuario vacío, no puedo realizar la búsqueda
                if(username.length()==0)
                {
                    Toast.makeText(LoginActivity.this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
                } else {
// a partir de aquí
                    String consultaAdmin = "SELECT * FROM admin WHERE usernameAdmin = ? AND passwordAdmin = ?";

                    // Definir los argumentos de la consulta (el nombre de usuario y la contraseña proporcionados por el usuario)
                    String[] argumentosAdmin = new String[] {username, password};

                    // Ejecutar la consulta y obtener los resultados
                    Cursor cursorAdmin = db.rawQuery(consultaAdmin, argumentosAdmin);



                    if(cursorAdmin.moveToFirst()){
                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        intent.putExtra("usernameAdmin", username);
                        startActivity(intent);
                    }
                    else{






//hasta aquí



                        //Preparamos para realizar la búsqueda por usuario


                        //El resultado de la consulta de select se guarda en el Cursor
                        String consulta = "SELECT * FROM usuarios WHERE username = ? AND password = ?";

                        // Definir los argumentos de la consulta (el nombre de usuario y la contraseña proporcionados por el usuario)
                        String[] argumentos = new String[] {username, password};

                        // Ejecutar la consulta y obtener los resultados
                        Cursor cursor = db.rawQuery(consulta, argumentos);

                        // Verificar si se encontraron resultados
                        if (cursor.moveToFirst()) {
                            // Se encontró un usuario con el nombre de usuario y la contraseña proporcionados
                            // Aquí puedes iniciar una nueva actividad o abrir una nueva pestaña en la aplicación
                            // para el usuario autenticado
                            Intent intent = new Intent(getApplicationContext(), Empleado.class);
                            intent.putExtra("username", username);
                            startActivity(intent);


                        } else {
                            // No se encontró un usuario con el nombre de usuario y la contraseña proporcionados
                            // Aquí se muestra un mensaje de error al usuario diciendo que algún dato no es correcto
                            // y vaciamos el contenido de password
                            Toast.makeText(LoginActivity.this, "El usuario y/o la contraseña no son correctos", Toast.LENGTH_LONG).show();
                            TxtPasswordInicioSesion.setText("");
                        }
                        // Cerramos el cursor
                        cursor.close();
                        cursorAdmin.close();

                    }

                    //Cerramos conexión con base de datos
                    db.close();

                }
            }

        });

        //Si clickamos en el botón de registrar nos llevará a la ventana
        BtnPagRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);

            }
        });


    }
}
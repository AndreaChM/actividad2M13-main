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

public class RegisterActivity extends AppCompatActivity {

    EditText TxtUsernameRegistrarse, txtPasswordRegistrarse, TxtRepeatPasswordRegistrarse;
    Button BtnRegistrarse, BtnPaginaInicioSesion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TxtUsernameRegistrarse = findViewById(R.id.TxtUsernameRegistrarse);
        txtPasswordRegistrarse = findViewById(R.id.txtPasswordRegistrarse);
        TxtRepeatPasswordRegistrarse = findViewById(R.id.TxtRepeatPasswordRegistrarse);
        BtnRegistrarse = findViewById(R.id.BtnRegistrarse);
        BtnPaginaInicioSesion = findViewById(R.id.BtnPaginaInicioSesion);



        BtnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Instancio de la conexión con la Base de datos
                AdminSQLiteOpenHelper adminHelper = new AdminSQLiteOpenHelper(RegisterActivity.this, "usuarios1", null, 1);
                //Abro la conexión de base de datos, con permisos de escritura para realizar las altas
                SQLiteDatabase db = adminHelper.getWritableDatabase();

                //Recogemos la información que el usuario rellenó en los campos del formulario
                String username = TxtUsernameRegistrarse.getText().toString();
                String password = txtPasswordRegistrarse.getText().toString();
                String password2 = TxtRepeatPasswordRegistrarse.getText().toString();

                //Si no rellenó todos los valores del formulario error y no se genera el registro
                if(username.length()==0 || password.length()==0 )
                {
                    Toast.makeText(RegisterActivity.this, "Tienes que rellenar todos los campos", Toast.LENGTH_SHORT).show();
                }
                else if( !password.equals(password2)){

                    Toast.makeText(RegisterActivity.this, "Las contraseñas deben ser iguales", Toast.LENGTH_SHORT).show();
                    txtPasswordRegistrarse.setText("");
                    TxtRepeatPasswordRegistrarse.setText("");
                }
                else
                {
                    //Los valores están cubiertos y preparamos para el registro

                    //Preparar la información anterior en un array de valores para incluirlos en la consulta de insert
                    ContentValues valores = new ContentValues();
                    //valores.put(<<nombre de la columna de la tabla>>, <<valor del campo>>);
                    valores.put("username", username);
                    valores.put("password", password);

                    //Consulta que lanza el insert sobre la tabla con los valores indicados.

                    //En nuestro caso, la clave principal es dada por el usuario, por lo que no insteresa generarla
                    long resultado = db.insert("usuarios",null, valores);

                    comprobarUsuario(resultado,username);

                    //Vacío los campos del formulario
                    TxtUsernameRegistrarse.setText("");
                    txtPasswordRegistrarse.setText("");
                    TxtRepeatPasswordRegistrarse.setText("");
                }

                //Cierro conexión con base de datos
                db.close();
            }
        });

        BtnPaginaInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void comprobarUsuario(long resultado, String username){
        // Si el resultado es -1 significa que ya existen esos valores por lo que lo redigiremos a la ventana de iniciar
        // sesión y pasaremos el nombre de usuario que haya puesto
        if(resultado ==-1){
            Toast.makeText(RegisterActivity.this, "Este usuario ya existe. Inicie sesión!!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("nuevoRegistro", true); // Agregar una bandera adicional
            startActivity(intent);

        } else{
            //Mensaje informativo de que se ha registrado el usuario
            //Toast.makeText(RegisterActivity.this, "Usuario creado correctamente!!", Toast.LENGTH_SHORT).show();
            mostrarMensaje("Usuario creado correctamente");
        }
    }


}
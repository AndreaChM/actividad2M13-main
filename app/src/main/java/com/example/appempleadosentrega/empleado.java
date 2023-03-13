package com.example.appempleadosentrega;

public class empleado extends AppCompatActivity {

    EditText etDNI, etName, etApellidos, etDireccion, etTelefono;
    Button btGuardar, btActualizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado);

        String username = getIntent().getStringExtra("username");
        Toast.makeText(this, "Bienvenido " +username, Toast.LENGTH_SHORT).show();

        etDNI = (EditText) findViewById(R.id.etDNI);
        etName = (EditText) findViewById(R.id.etName);
        etApellidos = (EditText) findViewById(R.id.etApellido);
        etDireccion = (EditText) findViewById(R.id.etDireccion);
        etTelefono = (EditText) findViewById(R.id.etTelefono);

        btGuardar = (Button) findViewById(R.id.btGuardar);
        btActualizar = (Button) findViewById(R.id.btActualizar);


        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar(etDNI.getText().toString(),etName.getText().toString(),etApellidos.getText().toString(),etDireccion.getText().toString(),etTelefono.getText().toString());
            }
        });

        btActualizar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Modificar(etDNI.getText().toString(),etNombre.getText().toString(),etApellido.getText().toString(),etDireccion.getText().toString(),etTelefono.getText().toString());
            onBackPressed();
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
        }catch (Exception e){

        }
    }

    private void actualizar (String DNI,String Nombre, String Apellido, String Direccion, String Telefono) {
        AdminSQLiteOpenHelper helper = new AdminSQLiteOpenHelper(Empleado.this,"usuario1",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "update Empleados set DNI='"+DNI+"',Nombre= '"+ Nombre + "',Apellido='" + Apellido +"',Direccion= '"+ Direccion + "',Telefono= '"+ Telefono + "' where DNI="+DNI;
        db.execSQL(sql);
        db.close();

    }
}

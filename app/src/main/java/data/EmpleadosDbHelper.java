package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Clase que facilita la gestión de la base de datos de empleados
 *
 * @author Jorge Igareda
 */
public class EmpleadosDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Empleados.db";

    public EmpleadosDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Crear la tabla
        sqLiteDatabase.execSQL("CREATE TABLE " + EmpleadosContract.EmpleadoEntry.TABLE_NAME + " ("
            + EmpleadosContract.EmpleadoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + EmpleadosContract.EmpleadoEntry.ID + " TEXT NOT NULL,"
            + EmpleadosContract.EmpleadoEntry.NOMBRE + " TEXT NOT NULL,"
            + EmpleadosContract.EmpleadoEntry.APELLIDOS + "TEXT NOT NULL,"
            + EmpleadosContract.EmpleadoEntry.PUESTO_DE_TRABAJO + " TEXT NOT NULL,"
            + EmpleadosContract.EmpleadoEntry.PHONE_NUMBER + " TEXT NOT NULL,"
            + EmpleadosContract.EmpleadoEntry.MAIL + " TEXT NOT NULL,"
            + EmpleadosContract.EmpleadoEntry.HORARIO + " TEXT NOT NULL,"
            + "UNIQUE (" + EmpleadosContract.EmpleadoEntry.ID + "))");

        // Contenedor de values
        ContentValues values = new ContentValues();

        // Pares clave-valor
        values.put(EmpleadosContract.EmpleadoEntry.ID, "E-001");
        values.put(EmpleadosContract.EmpleadoEntry.NOMBRE, "Jose Carlos");
        values.put(EmpleadosContract.EmpleadoEntry.APELLIDOS, "Rodríguez González");
        values.put(EmpleadosContract.EmpleadoEntry.PUESTO_DE_TRABAJO, "Recursos humanos");
        values.put(EmpleadosContract.EmpleadoEntry.PHONE_NUMBER, "936 01 23 94");
        values.put(EmpleadosContract.EmpleadoEntry.MAIL, "josecarlos@gmail.com");
        values.put(EmpleadosContract.EmpleadoEntry.HORARIO, "L-V 14:30 - 20:30");

        // Insertar el primer empleado para que tenga algún dato la base de datos
        sqLiteDatabase.insert(EmpleadosContract.EmpleadoEntry.TABLE_NAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * Método para facilitar la inserción de empleados
     * @param empleado el objeto de la clase Empleado a insertar
     * @return
     */
    public long guardarEmpleado (Empleado empleado) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return  sqLiteDatabase.insert(EmpleadosContract.EmpleadoEntry.TABLE_NAME, null, empleado.toContentValues());
    }

    /**
     * Query de todos los empleados
     */
    public Cursor getAllEmpleados() {
        return getReadableDatabase().rawQuery("select * from " + EmpleadosContract.EmpleadoEntry.TABLE_NAME, null);
    }

    /**
     * Query que busca un empleado por ID
     * @param empleadoID el ID del empleado que se quiere buscar
     * @return el objeto Empleado cuyo ID es el que se pasa por parámetro
     */
    public Empleado getEmpleadoByID (String empleadoID) {
        Cursor cursor = getReadableDatabase().rawQuery("select * from " + EmpleadosContract.EmpleadoEntry.TABLE_NAME + " WHERE _id=?", new String[]{empleadoID});
        return new Empleado(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
    }

    /**
     * Modifica un empleado
     * @param empleadoID
     * @param nombre
     * @param apellidos
     * @param puestoDeTrabajo
     * @param phoneNumber
     * @param mail
     * @param horario
     * @return
     */
    public long modificaEmpleado (String empleadoID, String nombre, String apellidos, String puestoDeTrabajo, String phoneNumber, String mail, String horario) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Empleado empleado = new Empleado(nombre, apellidos, puestoDeTrabajo, phoneNumber, mail, horario);
        return sqLiteDatabase.update(EmpleadosContract.EmpleadoEntry.TABLE_NAME, empleado.toContentValues(), "ID =?" ,new String[] {String.valueOf(empleadoID)});
    }

    /**
     * Borra un empleado
     * @param empleadoID
     * @return
     */
    public long borraEmpleado (String empleadoID) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(EmpleadosContract.EmpleadoEntry.TABLE_NAME, "ID =?", new String[] {empleadoID});
    }

}

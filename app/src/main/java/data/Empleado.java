package data;

import android.content.ContentValues;

import java.util.UUID;

/**
 * Entidad "empleado"
 *
 * @author Jorge Igareda
 */
public class Empleado {
    private String id;
    private String nombre;
    private String apellidos;
    private String puestoDeTrabajo;
    private String phoneNumber;
    private String mail;
    private String horario;

    public Empleado(String nombre, String apellidos, String puestoDeTrabajo, String phoneNumber, String mail, String horario) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.puestoDeTrabajo = puestoDeTrabajo;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.horario = horario;
    }

    public String getId () {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos () { return apellidos; }

    public String getPuestoDeTrabajo() {
        return puestoDeTrabajo;
    }

    public String getPhoneNumber () {
        return phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public String getHorario() {
        return horario;
    }

    /**
     * Convierte el empleado a valores para una operacion de INSERT
     * @return
     */
    public ContentValues toContentValues() {
        ContentValues values =  new ContentValues();
        values.put(EmpleadosContract.EmpleadoEntry.ID, id);
        values.put(EmpleadosContract.EmpleadoEntry.NOMBRE, nombre);
        values.put(EmpleadosContract.EmpleadoEntry.APELLIDOS, apellidos);
        values.put(EmpleadosContract.EmpleadoEntry.PUESTO_DE_TRABAJO, puestoDeTrabajo);
        values.put(EmpleadosContract.EmpleadoEntry.PHONE_NUMBER, phoneNumber);
        values.put(EmpleadosContract.EmpleadoEntry.MAIL, mail);
        values.put(EmpleadosContract.EmpleadoEntry.HORARIO, horario);
        return values;
    }
}

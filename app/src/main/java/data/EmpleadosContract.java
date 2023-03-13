package data;

import android.provider.BaseColumns;

/**
 * Clase que define las columnas de la base de datos
 *
 * @author Jorge Igareda
 */
public class EmpleadosContract {
    public static abstract class EmpleadoEntry implements BaseColumns {
        public static final String TABLE_NAME = "empleado";

        public static final String ID = "id";
        public static final String NOMBRE = "nombre";
        public static final String APELLIDOS = "apellidos";
        public static final String PUESTO_DE_TRABAJO = "puestoDeTrabajo";
        public static final String PHONE_NUMBER = "phoneNumber";
        public static final String MAIL = "mail";
        public static final String HORARIO = "horario";
    }
}

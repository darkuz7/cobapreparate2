package mx.edu.cobaev.preparate;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created on 10/10/2019.
 */
public class Database extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = BuildConfig.VERSION_CODE;
    public static final String DATABASE_NAME = "smartDB.db";

    private static final String sqlTutor =
            "CREATE TABLE IF NOT EXISTS tutor (" +
                    "id VARCHAR, " +
                    "nombre VARCHAR, " +
                    "email VARCHAR, " +
                    "usuario VARCHAR, " +
                    "escuela VARCHAR, " +
                    "nombreesc VARCHAR, " +
                    "foto VARCHAR, " +
                    "pass VARCHAR " +
                    ");";

    private static final String sqlColegio =
            "CREATE TABLE IF NOT EXISTS colegio (" +
                    "id VARCHAR, " +
                    "nombre VARCHAR, " +
                    "contacto VARCHAR, " +
                    "telefono VARCHAR, " +
                    "descripcion VARCHAR, " +
                    "color VARCHAR, " +
                    "correo VARCHAR, " +
                    "direccion VARCHAR, " +
                    "sitioweb VARCHAR, " +
                    "foto VARCHAR " +
                    ");";

    private static final String sqlDirectorio =
            "CREATE TABLE IF NOT EXISTS directorio (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre VARCHAR, " +
                    "puesto VARCHAR, " +
                    "contacto VARCHAR " +
                    ");";
    private static final String sqlHijos =
            "CREATE TABLE IF NOT EXISTS hijos (" +
                    "id VARCHAR, " +
                    "nombre VARCHAR, " +
                    "clave VARCHAR, " +
                    "id_grado VARCHAR, " +
                    "grado VARCHAR, " +
                    "id_grupo VARCHAR, " +
                    "grupo VARCHAR, " +
                    "id_escuela VARCHAR, " +
                    "boleta TEXT, " +
                    "fecha varchar, " +
                    "referencia_bancaria VARCHAR " +
                    ");";
    private static final String sqlMaterias =
            "CREATE TABLE IF NOT EXISTS materias (" +
                    "id VARCHAR, " +
                    "alumno VARCHAR, " +
                    "clave VARCHAR, " +
                    "nombre VARCHAR, " +
                    "tipo VARCHAR " +
                    ");";

    private static final String sqlAvisos =
            "CREATE TABLE IF NOT EXISTS avisos (" +
                    "id VARCHAR, " +
                    "titulo VARCHAR, " +
                    "mensaje VARCHAR, " +
                    "fecha VARCHAR, " +
                    "tipo VARCHAR " +
                    ");";

    public Database(Context contexto) {
        super(contexto, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creacion de base de datos
        db.execSQL(sqlTutor);
        db.execSQL(sqlColegio);
        db.execSQL(sqlDirectorio);
        db.execSQL(sqlHijos);
        db.execSQL(sqlMaterias);
        db.execSQL(sqlAvisos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Updates de base de datos
        db.execSQL("DROP TABLE IF EXISTS tutor");
        db.execSQL("DROP TABLE IF EXISTS colegio");
        db.execSQL("DROP TABLE IF EXISTS directorio");
        db.execSQL("DROP TABLE IF EXISTS hijos");
        db.execSQL("DROP TABLE IF EXISTS materias");
        db.execSQL("DROP TABLE IF EXISTS avisos");
        onCreate(db);
    }
}

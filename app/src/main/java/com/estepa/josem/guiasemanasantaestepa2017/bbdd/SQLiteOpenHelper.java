package com.estepa.josem.guiasemanasantaestepa2017.bbdd;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

import static com.estepa.josem.guiasemanasantaestepa2017.bbdd.BDHelperContract.*;

/**
 * Created by xatox on 4/3/17.
 */

public class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {

    /**
     * VARIABLES GLOBALES
     */
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "GuiaSSBD.db";

    /**
     * SENTENCIAS DE CREACION Y BORRADO DE TABLAS
     */
    private static final String SQL_CREAR_TABLA_HDADES = "CREATE TABLE " + Hdades.NOMBRE_TABLA_HDADES +
            " (" +
            Hdades.COLUMNA_IDHDAD + " INTEGER PRIMARY KEY, " +
            Hdades.COLUMNA_NOMBRE_HDAD + " TEXT " +
            ");";

    private static final String SQL_CREAR_TABLA_IMAGENESHDADES = "CREATE TABLE " + ImagenesHdad.NOMBRE_TABLA_IMAGENESHDAD +
            " (" +
            ImagenesHdad.COLUMNA_IDIMAGEN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Hdades.COLUMNA_IDHDAD + " INTEGER, " +
            ImagenesHdad.COLUMNA_IMAGEN_LISTAEXT + " TEXT, " +
            ImagenesHdad.COLUMNA_IMAGEN_MEDIANAEXT + " TEXT, " +
            ImagenesHdad.COLUMNA_IMAGEN_GRANDEEXT + " TEXT, " +
            ImagenesHdad.COLUMNA_IMAGEN_LISTAINT + " TEXT, " +
            ImagenesHdad.COLUMNA_IMAGEN_MEDIANAINT + " TEXT, " +
            ImagenesHdad.COLUMNA_IMAGEN_GRANDEINT + " TEXT " +
            ");";

    private static final String SQL_CREAR_TABLA_DETALLESHDADES = "CREATE TABLE " + DetallesHdad.NOMBRE_TABLA_DETALLESHDAD +
            " (" +
            DetallesHdad.COLUMNA_IDDETALLE + " INTEGER PRIMARY KEY, " +
            Hdades.COLUMNA_IDHDAD + " INTEGER , " +
            DetallesHdad.COLUMNA_IDTITULO + " INTEGER, " +
            DetallesHdad.COLUMNA_TITULO_DETALLE + " TEXT, " +
            DetallesHdad.COLUMNA_CONTENIDO_DETALLE + " TEXT " +
            ");";

    private static final String SQL_BORRAR_TABLA_HDADES = "DROP TABLE IF EXISTS " + Hdades.NOMBRE_TABLA_HDADES;

    private static final String SQL_BORRAR_TABLA_IMAGENESHDADES = "DROP TABLE IF EXISTS " + ImagenesHdad.NOMBRE_TABLA_IMAGENESHDAD;

    private static final String SQL_BORRAR_TABLA_DETALLESHDADES = "DROP TABLE IF EXISTS " + DetallesHdad.NOMBRE_TABLA_DETALLESHDAD;

    /**
     * CONSTRUCTORES
     */
    public SQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public SQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREAR_TABLA_HDADES);
        db.execSQL(SQL_CREAR_TABLA_IMAGENESHDADES);
        db.execSQL(SQL_CREAR_TABLA_DETALLESHDADES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_BORRAR_TABLA_HDADES);
        db.execSQL(SQL_BORRAR_TABLA_IMAGENESHDADES);
        db.execSQL(SQL_BORRAR_TABLA_DETALLESHDADES);
        onCreate(db);
    }
}

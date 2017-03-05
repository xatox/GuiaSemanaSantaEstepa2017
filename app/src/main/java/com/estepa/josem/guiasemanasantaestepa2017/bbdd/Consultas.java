package com.estepa.josem.guiasemanasantaestepa2017.bbdd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.estepa.josem.guiasemanasantaestepa2017.bbdd.BDHelperContract.*;

/**
 * Created by xatox on 4/3/17.
 */

public class Consultas {

    Context context;
    SQLiteOpenHelper laConfiteraBD;
    //Para leer
    SQLiteDatabase dbReadable;

    public Consultas(Context context){
        this.context = context;
        this.laConfiteraBD = new SQLiteOpenHelper(context.getApplicationContext());
        this.dbReadable = laConfiteraBD.getReadableDatabase();
    }

    public boolean isEmptyHdades() {
        boolean retorno = false;

        String consulta = "SELECT COUNT(*) as Contador FROM " + Hdades.NOMBRE_TABLA_HDADES + ";";
        Cursor c=dbReadable.rawQuery(consulta, null);

        int contador;
        c.moveToFirst();
        do {
            contador = c.getInt(0);
        }while(c.moveToNext());
        c.close();

        if (contador == 0){
            retorno = true;
        }
        return retorno;
    }

}

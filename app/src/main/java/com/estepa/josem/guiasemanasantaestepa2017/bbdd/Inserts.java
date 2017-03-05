package com.estepa.josem.guiasemanasantaestepa2017.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import static com.estepa.josem.guiasemanasantaestepa2017.bbdd.BDHelperContract.*;

import com.estepa.josem.guiasemanasantaestepa2017.clases.DetalleHdad;
import com.estepa.josem.guiasemanasantaestepa2017.clases.Hermandad;
import com.estepa.josem.guiasemanasantaestepa2017.clases.ImagenHdad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xatox on 4/3/17.
 */

public class Inserts {

    Context context;
    SQLiteOpenHelper guiaSSBD;
    //Para escribir
    SQLiteDatabase dbWritable;

    public Inserts(Context context){
        this.context = context;
        this.guiaSSBD = new SQLiteOpenHelper(context.getApplicationContext());
        this.dbWritable = guiaSSBD.getWritableDatabase();
    }

    public List<Long> insertarHdades(List<Hermandad> listaHdades) {
        List<Long> listIdsAddHdades = new ArrayList<Long>();

        for (int x = 0; x < listaHdades.size(); x++) {

            Hermandad hdad = listaHdades.get(x);

            long idHdadAdd = insertarHdad(hdad);
            listIdsAddHdades.add(idHdadAdd);

            long idAddImagenesHdad = insertarImagenHdad(hdad.getImagenesHdad());

            List<Long> listIdsAddDetallesHdades = insertarDetallesHdadades(hdad.getListaDetalleHdads());
        }

        return listIdsAddHdades;
    }

    private long insertarHdad(Hermandad hdad) {

        ContentValues values = new ContentValues();
        values.put(Hdades.COLUMNA_IDHDAD, hdad.getIdHdad());
        values.put(Hdades.COLUMNA_NOMBRE_HDAD, hdad.getNombreHdad());

        return dbWritable.insert(Hdades.NOMBRE_TABLA_HDADES, null, values);
    }

    /*private List<Long> insertarImagenesHdadades(List<ImagenHdad> listaImagenes) {

        List<Long> listIdsAddImagenesHdades = new ArrayList<Long>();

        for (int x = 0; x < listaImagenes.size(); x++) {

            ImagenHdad imagen = listaImagenes.get(x);

            long idImagenAdd = insertarImagenHdad(imagen);

            listIdsAddImagenesHdades.add(idImagenAdd);

        }

        return listIdsAddImagenesHdades;
    }*/

    private long insertarImagenHdad(ImagenHdad imagen) {

        ContentValues values = new ContentValues();
        values.put(ImagenesHdad.COLUMNA_IDIMAGEN, (byte[]) null);
        values.put(Hdades.COLUMNA_IDHDAD, imagen.getIdHdad());
        values.put(ImagenesHdad.COLUMNA_IMAGEN_LISTAEXT, imagen.getRutaLogoListaExt());
        values.put(ImagenesHdad.COLUMNA_IMAGEN_MEDIANAEXT, imagen.getRutaLogoMedianoExt());
        values.put(ImagenesHdad.COLUMNA_IMAGEN_GRANDEEXT, imagen.getRutaLogoGrandeExt());
        values.put(ImagenesHdad.COLUMNA_IMAGEN_LISTAINT, "");
        values.put(ImagenesHdad.COLUMNA_IMAGEN_MEDIANAINT, "");
        values.put(ImagenesHdad.COLUMNA_IMAGEN_GRANDEINT, "");

        return dbWritable.insert(ImagenesHdad.NOMBRE_TABLA_IMAGENESHDAD, null, values);
    }

    private List<Long> insertarDetallesHdadades(List<DetalleHdad> listaDetalleHdad) {

        List<Long> listIdsAddDetallesHdades = new ArrayList<Long>();

        for (int x = 0; x < listaDetalleHdad.size(); x++) {

            DetalleHdad detalleHdad = listaDetalleHdad.get(x);

            long idDetalleHdadAdd = insertarDetalleHdad(detalleHdad);

            listIdsAddDetallesHdades.add(idDetalleHdadAdd);

        }

        return listIdsAddDetallesHdades;
    }

    private long insertarDetalleHdad(DetalleHdad detalleHdad) {

        ContentValues values = new ContentValues();
        values.put(DetallesHdad.COLUMNA_IDDETALLE, detalleHdad.getIdDetalle());
        values.put(Hdades.COLUMNA_IDHDAD, detalleHdad.getIdHdad());
        values.put(DetallesHdad.COLUMNA_IDTITULO, detalleHdad.getIdTitulo());
        values.put(DetallesHdad.COLUMNA_TITULO_DETALLE, detalleHdad.getTitulo());
        values.put(DetallesHdad.COLUMNA_CONTENIDO_DETALLE, detalleHdad.getContenido());

        return dbWritable.insert(DetallesHdad.NOMBRE_TABLA_DETALLESHDAD, null, values);
    }
}

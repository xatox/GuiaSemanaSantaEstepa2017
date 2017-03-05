package com.estepa.josem.guiasemanasantaestepa2017.bbdd;

import android.provider.BaseColumns;

/**
 * Created by xatox on 29/8/15.
 */
public class BDHelperContract {

    public BDHelperContract() {}

    public static final class Hdades implements BaseColumns {
        private Hdades() {}
        public static final String NOMBRE_TABLA_HDADES = "Hdades";
        public static final String COLUMNA_IDHDAD = "idHdad";
        public static final String COLUMNA_NOMBRE_HDAD = "nombreHdad";
    }

    public static final class ImagenesHdad implements BaseColumns {
        private ImagenesHdad() {}
        public static final String NOMBRE_TABLA_IMAGENESHDAD = "ImagenesHdad";
        public static final String COLUMNA_IDIMAGEN = "idImagen";
        public static final String COLUMNA_IMAGEN_LISTAEXT = "rutaImgListaExt";
        public static final String COLUMNA_IMAGEN_MEDIANAEXT = "rutaImgMedExt";
        public static final String COLUMNA_IMAGEN_GRANDEEXT = "rutaImgGrandeExt";
        public static final String COLUMNA_IMAGEN_LISTAINT = "rutaImgListaInt";
        public static final String COLUMNA_IMAGEN_MEDIANAINT = "rutaImgMedInt";
        public static final String COLUMNA_IMAGEN_GRANDEINT = "rutaImgGrandeInt";
    }

    public static final class DetallesHdad implements BaseColumns {
        private DetallesHdad() {}
        public static final String NOMBRE_TABLA_DETALLESHDAD = "detallesHdad";
        public static final String COLUMNA_IDDETALLE = "idDetalle";
        public static final String COLUMNA_IDTITULO = "idTitulo";
        public static final String COLUMNA_TITULO_DETALLE = "tituloDetalle";
        public static final String COLUMNA_CONTENIDO_DETALLE = "contenidoDetalle";

    }



}

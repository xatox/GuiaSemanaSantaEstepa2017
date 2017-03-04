package com.estepa.josem.guiasemanasantaestepa2017.bbdd;

import android.provider.BaseColumns;

/**
 * Created by xatox on 29/8/15.
 */
public class BDHelperContract {

    public BDHelperContract() {}

    public static final class Hdades implements BaseColumns {
        private Hdades() {}
        public static final String NOMBRE_TABLA_DETALLESHDAD = "Hdades";
        public static final String COLUMNA_IDHDAD = "idHdad";
        public static final String COLUMNA_NOMBRE_HDAD = "nombreHdad";
        public static final String COLUMNA_IMAGEN_LISTA = "imgLista";
        public static final String COLUMNA_IMAGEN_MEDIANA = "imgMediana";
        public static final String COLUMNA_IMAGEN_GRANDE = "imgGrande";
    }

    public static final class DetallesHdad implements BaseColumns {
        private DetallesHdad() {}
        public static final String NOMBRE_TABLA_DETALLESHDAD = "detallesHdad";
        public static final String COLUMNA_IDDETALLE = "idDetalle";
        public static final String COLUMNA_TITULO_DETALLE = "tituloDetalle";
        public static final String COLUMNA_CONTENIDO_DETALLE = "contenidoDetalle";

    }



}

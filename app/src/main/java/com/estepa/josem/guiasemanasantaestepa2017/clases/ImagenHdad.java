package com.estepa.josem.guiasemanasantaestepa2017.clases;

/**
 * Created by xatox on 5/3/17.
 */

public class ImagenHdad {

    int idImagen, idHdad;
    String nombreHdad, rutaLogoListaExt, rutaLogoMedianoExt, rutaLogoGrandeExt, rutaLogoListaInt, rutaLogoMedianoInt, rutaLogoGrandeInt;

    public ImagenHdad() {}

    public ImagenHdad(int idImagen, int idHdad, String nombreHdad, String rutaLogoListaExt, String rutaLogoMedianoExt, String rutaLogoGrandeExt) {
        this.idImagen = idImagen;
        this.idHdad = idHdad;
        this.nombreHdad = nombreHdad;
        this.rutaLogoListaExt = rutaLogoListaExt;
        this.rutaLogoMedianoExt = rutaLogoMedianoExt;
        this.rutaLogoGrandeExt = rutaLogoGrandeExt;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public int getIdHdad() {
        return idHdad;
    }

    public void setIdHdad(int idHdad) {
        this.idHdad = idHdad;
    }

    public String getNombreHdad() {
        return nombreHdad;
    }

    public void setNombreHdad(String nombreHdad) {
        this.nombreHdad = nombreHdad;
    }

    public String getRutaLogoListaExt() {
        return rutaLogoListaExt;
    }

    public void setRutaLogoListaExt(String rutaLogoListaExt) {
        this.rutaLogoListaExt = rutaLogoListaExt;
    }

    public String getRutaLogoMedianoExt() {
        return rutaLogoMedianoExt;
    }

    public void setRutaLogoMedianoExt(String rutaLogoMedianoExt) {
        this.rutaLogoMedianoExt = rutaLogoMedianoExt;
    }

    public String getRutaLogoGrandeExt() {
        return rutaLogoGrandeExt;
    }

    public void setRutaLogoGrandeExt(String rutaLogoGrandeExt) {
        this.rutaLogoGrandeExt = rutaLogoGrandeExt;
    }

    public String getRutaLogoListaInt() {
        return rutaLogoListaInt;
    }

    public void setRutaLogoListaInt(String rutaLogoListaInt) {
        this.rutaLogoListaInt = rutaLogoListaInt;
    }

    public String getRutaLogoMedianoInt() {
        return rutaLogoMedianoInt;
    }

    public void setRutaLogoMedianoInt(String rutaLogoMedianoInt) {
        this.rutaLogoMedianoInt = rutaLogoMedianoInt;
    }

    public String getRutaLogoGrandeInt() {
        return rutaLogoGrandeInt;
    }

    public void setRutaLogoGrandeInt(String rutaLogoGrandeInt) {
        this.rutaLogoGrandeInt = rutaLogoGrandeInt;
    }
}

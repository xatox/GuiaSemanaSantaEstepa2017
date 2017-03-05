package com.estepa.josem.guiasemanasantaestepa2017.clases;

import java.util.List;

/**
 * Created by xatox on 3/3/17.
 */

public class Hermandad {

    int idHdad;
    String nombreHdad;
    //String nombreHdad, rutaLogoLista, rutaLogoMediano, rutaLogoGrande;
    ImagenHdad imagenesHdad;
    List<DetalleHdad> listaDetalleHdads;

    public Hermandad(int idHdad, String nombreHdad, ImagenHdad imagenesHdad, List<DetalleHdad> listaDetalleHdads) {
        this.idHdad = idHdad;
        this.nombreHdad = nombreHdad;
        this.imagenesHdad = imagenesHdad;
        //this.rutaLogoMediano = rutaLogoMediano;
        //this.rutaLogoGrande = rutaLogoGrande;
        this.listaDetalleHdads = listaDetalleHdads;
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

    public ImagenHdad getImagenesHdad() {
        return imagenesHdad;
    }

    public void setImagenesHdad(ImagenHdad imagenesHdad) {
        this.imagenesHdad = imagenesHdad;
    }

    public List<DetalleHdad> getListaDetalleHdads() {
        return listaDetalleHdads;
    }

    public void setListaDetalleHdads(List<DetalleHdad> listaDetalleHdads) {
        this.listaDetalleHdads = listaDetalleHdads;
    }
}

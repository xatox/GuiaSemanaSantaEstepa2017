package com.estepa.josem.guiasemanasantaestepa2017.clases;

import java.util.List;

/**
 * Created by xatox on 3/3/17.
 */

public class Hermandad {

    int idHdad;
    String nombreHdad, rutaLogoLista, rutaLogoMediano, rutaLogoGrande;
    List<Detalle> listaDetalles;

    public Hermandad(int idHdad, String nombreHdad, String rutaLogoLista, String rutaLogoMediano, String rutaLogoGrande, List<Detalle> listaDetalles) {
        this.idHdad = idHdad;
        this.nombreHdad = nombreHdad;
        this.rutaLogoLista = rutaLogoLista;
        this.rutaLogoMediano = rutaLogoMediano;
        this.rutaLogoGrande = rutaLogoGrande;
        this.listaDetalles = listaDetalles;
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

    public String getRutaLogoLista() {
        return rutaLogoLista;
    }

    public void setRutaLogoLista(String rutaLogoLista) {
        this.rutaLogoLista = rutaLogoLista;
    }

    public String getRutaLogoMediano() {
        return rutaLogoMediano;
    }

    public void setRutaLogoMediano(String rutaLogoMediano) {
        this.rutaLogoMediano = rutaLogoMediano;
    }

    public String getRutaLogoGrande() {
        return rutaLogoGrande;
    }

    public void setRutaLogoGrande(String rutaLogoGrande) {
        this.rutaLogoGrande = rutaLogoGrande;
    }

    public List<Detalle> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(List<Detalle> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }
}

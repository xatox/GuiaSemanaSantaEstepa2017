package com.estepa.josem.guiasemanasantaestepa2017.clases;

/**
 * Created by xatox on 3/3/17.
 */

public class DetalleHdad {

    int idDetalle, idHdad, idTitulo;
    String titulo, contenido;

    public DetalleHdad(int idDetalle, int idHdad, int idTitulo, String titulo, String contenido) {
        this.idDetalle = idDetalle;
        this.idHdad = idHdad;
        this.idTitulo = idTitulo;
        this.titulo = titulo;
        this.contenido = contenido;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdTitulo() {
        return idTitulo;
    }

    public void setIdTitulo(int idTitulo) {
        this.idTitulo = idTitulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getIdHdad() {
        return idHdad;
    }

    public void setIdHdad(int idHdad) {
        this.idHdad = idHdad;
    }
}

package com.estepa.josem.guiasemanasantaestepa2017.clases;

/**
 * Created by xatox on 3/3/17.
 */

public class Detalle {

    int idDetalle, idTitulo;
    String titulo, contenido;

    public Detalle(int idDetalle, int idTitulo, String titulo, String contenido) {
        this.idDetalle = idDetalle;
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
}

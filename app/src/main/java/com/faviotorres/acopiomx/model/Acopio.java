package com.faviotorres.acopiomx.model;

import java.util.List;

public class Acopio {

    private String id;
    private String nombre;
    private String legacyId;
    private String lat, lng;
    private String comments;
    private String direccion;
    //private List<Contacto> contactos;
    private List<String> productosIds;
    private String fechaDeActualizacion;
    //private List<Comentario> comentarios;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLegacyId() {
        return legacyId;
    }

    public void setLegacyId(String legacyId) {
        this.legacyId = legacyId;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<String> getProductosIds() {
        return productosIds;
    }

    public void setProductosIds(List<String> productosIds) {
        this.productosIds = productosIds;
    }

    public String getFechaDeActualizacion() {
        return fechaDeActualizacion;
    }

    public void setFechaDeActualizacion(String fechaDeActualizacion) {
        this.fechaDeActualizacion = fechaDeActualizacion;
    }

    @Override
    public String toString() {
        return "id: "+id+" | fecha de actualizacion: "+fechaDeActualizacion;
    }
}

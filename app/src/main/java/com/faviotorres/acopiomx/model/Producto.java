package com.faviotorres.acopiomx.model;

public class Producto {

    private String id;
    private String nombre;
    private String acopioId;
    private String fechaDeActualizacion;

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

    public String getAcopioId() {
        return acopioId;
    }

    public void setAcopioId(String acopioId) {
        this.acopioId = acopioId;
    }

    public String getFechaDeActualizacion() {
        return fechaDeActualizacion;
    }

    public void setFechaDeActualizacion(String fechaDeActualizacion) {
        this.fechaDeActualizacion = fechaDeActualizacion;
    }
}

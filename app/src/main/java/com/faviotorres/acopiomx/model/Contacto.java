package com.faviotorres.acopiomx.model;

public class Contacto {

    private String id;
    private String email;
    private String nombre;
    private String twitter;
    private String facebook;
    private String telefono;
    private String acopioId;
    private String legacy_id;

    public String getLegacy_id() {
        return legacy_id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getId() {
        return id;
    }

    public String getAcopioId() {
        return acopioId;
    }
}

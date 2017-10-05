package com.faviotorres.acopiomx.model;

import java.io.Serializable;
import java.util.List;

public class Acopio implements Serializable {

    private String id;
    private String nombre;
    private GeoPos geopos;
    private String legacyId;
    private String comments;
    private String direccion;

    public GeoPos getGeopos() {
        return geopos;
    }

    public void setGeopos(GeoPos geopos) {
        this.geopos = geopos;
    }

    private List<String> productosIds;


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

    @Override
    public String toString() {
        return "id: "+id+" | geopos: "+geopos.getLat()+", "+geopos.getLng()+" | name: "+nombre;
    }

    public class GeoPos implements Serializable {

        private Double lat;
        private Double lng;

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }
    }
}

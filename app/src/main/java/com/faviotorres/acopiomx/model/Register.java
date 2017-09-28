package com.faviotorres.acopiomx.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Register {

    private String email;
    private String username;
    private String password;

    public Register(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public class Result {

        @SerializedName("habilidades")
        private List<Object> habilidades = null;
        @SerializedName("username")
        private String username;
        @SerializedName("email")
        private String email;
        @SerializedName("id")
        private String id;

        @SerializedName("habilidades")
        public List<Object> getHabilidades() {
            return habilidades;
        }

        @SerializedName("habilidades")
        public void setHabilidades(List<Object> habilidades) {
            this.habilidades = habilidades;
        }

        @SerializedName("username")
        public String getUsername() {
            return username;
        }

        @SerializedName("username")
        public void setUsername(String username) {
            this.username = username;
        }

        @SerializedName("email")
        public String getEmail() {
            return email;
        }

        @SerializedName("email")
        public void setEmail(String email) {
            this.email = email;
        }

        @SerializedName("id")
        public String getId() {
            return id;
        }

        @SerializedName("id")
        public void setId(String id) {
            this.id = id;
        }
    }
}

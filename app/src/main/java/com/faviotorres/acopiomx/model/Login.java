package com.faviotorres.acopiomx.model;

import com.google.gson.annotations.SerializedName;

public class Login {

    private String email;
    private String username;
    private String password;

    public Login(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public class Result {

        @SerializedName("id")
        private String id;

        @SerializedName("tlt")
        private String ttl;

        @SerializedName("created")
        private String created;

        @SerializedName("userId")
        private String userId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTtl() {
            return ttl;
        }

        public void setTtl(String ttl) {
            this.ttl = ttl;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}

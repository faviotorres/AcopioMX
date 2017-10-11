package com.faviotorres.acopiomx.retro;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retro {

    //private static final String DEV_BASE_URL = "http://hapi.balterbyte.com:8080/api/";
    private static final String PROD_BASE_URL = "https://hapi.balterbyte.com/api/";

    public static RetroService getService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(PROD_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(RetroService.class);
    }

}

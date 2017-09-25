package com.faviotorres.acopiomx.retro;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retro {

    private static final String BASE_URL = "http://hapi.balterbyte.com:8080/api/";

    public RetroService getService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(RetroService.class);
    }

}

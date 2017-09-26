package com.faviotorres.acopiomx.retro;


import com.faviotorres.acopiomx.model.Acopio;
import com.faviotorres.acopiomx.model.Login;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetroService {

    @GET("acopios/")
    Observable<List<Acopio>> fetchAcopios();

    @POST("voluntarios/login/")
    Observable<Login.Result> login(@Body Login login);
}

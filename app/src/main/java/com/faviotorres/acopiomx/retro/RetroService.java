package com.faviotorres.acopiomx.retro;


import com.faviotorres.acopiomx.model.Acopio;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RetroService {

    @GET("acopios/")
    Observable<List<Acopio>> fetchAcopios();
}

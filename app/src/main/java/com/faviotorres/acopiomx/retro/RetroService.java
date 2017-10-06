package com.faviotorres.acopiomx.retro;


import com.faviotorres.acopiomx.model.Acopio;
import com.faviotorres.acopiomx.model.Contacto;
import com.faviotorres.acopiomx.model.Login;
import com.faviotorres.acopiomx.model.Producto;
import com.faviotorres.acopiomx.model.Register;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetroService {

    @GET("acopios/")
    Observable<List<Acopio>> fetchAcopios();

    @POST("voluntarios/login/")
    Observable<Login.Result> login(@Body Login login);

    @POST("voluntarios/")
    Observable<Register.Result> register(@Body Register register);

    @GET("acopios/{acopioId}/productos")
    Observable<List<Producto>> fetchProductos(@Path("acopioId") String acopioId);

    @GET("acopios/{acopioId}/contactos")
    Observable<List<Contacto>> fetchContacto(@Path("acopioId") String acopioId);
}

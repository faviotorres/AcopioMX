package com.faviotorres.acopiomx.retro;


import com.faviotorres.acopiomx.model.Acopio;
import com.faviotorres.acopiomx.model.Contacto;
import com.faviotorres.acopiomx.model.Login;
import com.faviotorres.acopiomx.model.Producto;
import com.faviotorres.acopiomx.model.Register;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface RetroService {

    @GET("acopios/")
    Observable<List<Acopio>> fetchAcopios();

    @POST("users/login/")
    Observable<Login.Result> login(@Body Login login);

    @POST("users/")
    Observable<Register.Result> register(@Body Register register);

    @GET("acopios/{acopioId}/productos/")
    Observable<List<Producto>> fetchProductos(@Path("acopioId") String acopioId);

    @GET("acopios/{acopioId}/contactos/")
    Observable<List<Contacto>> fetchContacto(@Path("acopioId") String acopioId);

    @GET("acopios/{acopioId}")
    Observable<Acopio> fetchAcopio(@Path("acopioId") String acopioId);

    @GET("productos")
    Observable<List<Producto>> searchProducto(@QueryMap Map<String, String> params);
}

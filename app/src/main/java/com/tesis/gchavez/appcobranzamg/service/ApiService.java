package com.tesis.gchavez.appcobranzamg.service;

import com.tesis.gchavez.appcobranzamg.models.Banco;
import com.tesis.gchavez.appcobranzamg.models.Cliente;
import com.tesis.gchavez.appcobranzamg.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface  ApiService {

    String API_BASE_URL = "https://mgcobranza-gustavoch25.c9users.io";

    @FormUrlEncoded
    @POST("/api/api/v1/login")
    Call<Usuario> login(
            @Field("usuario") String login,
            @Field("password") String password
    );

    @GET("api/v1/user/{id}")
    Call<Usuario> showUsuario(@Path("id") Integer id);

    @GET("api/v1/name/{name}")
    Call<List<Cliente>> showName(@Path("name") String name);

    @GET("api/v1/ruc/{ruc}")
    Call<List<Cliente>> showRuc(@Path("ruc") String ruc);

    @GET("api/v1/banco")
    Call<List<Banco>> getBanco();

    @GET("api/v1/cliente/{fchcob}")
    Call<List<Cliente>> getCliente(@Path("fchcob") String fchcob);

}

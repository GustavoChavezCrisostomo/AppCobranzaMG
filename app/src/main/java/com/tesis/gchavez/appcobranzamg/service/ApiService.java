package com.tesis.gchavez.appcobranzamg.service;

import com.tesis.gchavez.appcobranzamg.ResponseMessage;
import com.tesis.gchavez.appcobranzamg.models.Banco;
import com.tesis.gchavez.appcobranzamg.models.Cliente;
import com.tesis.gchavez.appcobranzamg.models.Usuario;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface  ApiService {

    String API_BASE_URL = "https://mgcobranza-gustavoch25.c9users.io";

    @FormUrlEncoded
    @POST("/api/v1/login")
    Call<Usuario> login(
            @Field("usuario") String login,
            @Field("password") String password
    );
    //perfil
    @GET("api/v1/user/{id}")
    Call<Usuario> showUsuario(@Path("id") Integer id);
    //cliente busqueda
    @GET("api/v1/name/{name}")
    Call<List<Cliente>> showName(@Path("name") String name);
    //cliente busqueda
    @GET("api/v1/ruc/{ruc}")
    Call<List<Cliente>> showRuc(@Path("ruc") String ruc);
    //spenner banco
    @GET("api/v1/banco")
    Call<List<Banco>> getBanco();
    //seleccion de cobra por fchacob
    @GET("api/v1/cliente/{fchcob}")
    Call<List<Cliente>> getCliente(@Path("fchcob") String fchcob);

    @FormUrlEncoded
    @PUT("api/v1/client/{idClient}")
    Call<ResponseMessage> addCliente(
            @Path("idClient") String idClient,
            @Field("fchCobra") String fchCobra
    );

}

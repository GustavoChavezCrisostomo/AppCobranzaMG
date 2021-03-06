package com.tesis.gchavez.appcobranzamg.service;

import com.tesis.gchavez.appcobranzamg.ResponseMessage;
import com.tesis.gchavez.appcobranzamg.models.Banco;
import com.tesis.gchavez.appcobranzamg.models.Cliente;
import com.tesis.gchavez.appcobranzamg.models.Cobranza;
import com.tesis.gchavez.appcobranzamg.models.Documento;
import com.tesis.gchavez.appcobranzamg.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface  ApiService {

    //String API_BASE_URL = "https://montgroup.pe/api/public/";
    String API_BASE_URL = "https://mgcobranza-gustavoch25.c9users.io/";


    @FormUrlEncoded
    @POST("api/v1/login")
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
    //seleccion de clientes por fchacob
    @GET("api/v1/cliente/{fchcob}/{user}")
    Call<List<Cliente>> getCliente(
            @Path("fchcob") String fchcob,
            @Path("user") String user
    );
    //lista de cliente
    @FormUrlEncoded
    @PUT("api/v1/client/{idClient}")
    Call<ResponseMessage> addCliente(
            @Path("idClient") String idClient,
            @Field("fchCobra") String fchCobra,
            @Field("user") String user
    );
    //List de cobranza
    @GET("api/v1/cobranza/{fch}/{user}")
    Call<List<Cobranza>> getCobranza(
            @Path("fch") String fch,
            @Path("user") String user
    );
    //selecctor de documento
    @GET("api/v1/doc/{id}")
    Call<Documento> show(@Path("id") Integer id);
    //crear informe cobranza
    @FormUrlEncoded
    @POST("api/v1/cobra")
    Call<Cobranza> store(
            @Field("usuario_id") String usuario_id,
            @Field("serie") String serie,
            @Field("fecha") String fecha,
            @Field("tipo") String tipo,
            @Field("numDoc") String numDoc,
            @Field("cliente_id") Integer cliente_id,
            @Field("distritoCli") String distritoCli,
            @Field("rucCli") String rucCli,
            @Field("tipoPago") String tipoPago,
            @Field("monto") Double monto,
            @Field("numCheque") String numCheque,
            @Field("banco_id") Integer banco_id,
            @Field("numOpe") String numOpe,
            @Field("observaciones") String observaciones
    );
}

package com.example.retrofit.retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface PostService {

    @GET("posts")//Estamos dizendo que o método 'list' abaixo, fará requisições do tipo GET para o endpoint 'posts'. O resto da URL que é 'https://jsonplaceholder.typicode.com/' será configurado dentro do RetroFit e não aqui.
    fun list(): Call<List<PostEntity>>//Preste atenção na dependência de call, pois se não for do RetroFit2, ele dá erro

    //Tenha em mente que o retorno está setado como List<PostEntry>, nesse caso o servidor deve responder em formato de lista dessa forma: [{...}, {...}]
    //Caso o servidor resposta diretamente, como por exemplo: {...}, aí, nós deveríamos usar: Call<PostEntity>

    @FormUrlEncoded//Neste exemplo estamos usando uma anotação do tipo FormUrlEncoded para que o RetroFit monte os parâmetros da função e os envie como application/x-www-form-urlencoded para que o servidor possa recuperar atráves do método POST
    @POST("posts")//Estamos dizendo que o método postRequest abaixo, fará requisições do tipo POST para o endpoint 'posts'
    fun postRequest(
        @Field("title") title: String,//Usamos o @Field para declarar os parâmetros do tipo POST que serão enviados pela requisição
        @Field("body") body: String,
        @Field("userId") userId: Int
    ): Call<PostEntity>

    //Observe no método acima, que estamos recebendo de forma manual cada um dos campos que serão enviados na requisição, e aqui não estamos retornando uma lista, até porque o retorno não será uma lista e sim os parâmetros soltos.

    //Caso o servidor responda em formato de lista, aí sim, você deveria setar esse retorno como: Call<List<PostEntity>>

    @FormUrlEncoded//Neste exemplo estamos usando uma anotação do tipo FormUrlEncoded para que o RetroFit monte os parâmetros da função e os envie como application/x-www-form-urlencoded para que o servidor possa recuperar atráves do método POST
    @PUT("posts/{id}")//Estamos dizendo que o método putRequest abaixo, fará requisições do tipo POST para o endpoint 'posts/{id}', onde esse {id} será preenchido pelo valor que vier pelo parâmetro id da função abaixo.
    fun putRequest(
        @Path("id") id: Int,//O @Path é usado para mapear o parâmetro inserido no @PUT, fazendo com que o parâmetro id que esse método recebe, vá para ali dentro do @PUT
        @Field("title") title: String,
        @Field("body") body: String,
        @Field("userId") userId: Int
    ): Call<PostEntity>

    //A lógica do @Path pode tanto ser usada em outros verbos como @GET, @POST e @DELETE

    @DELETE("posts/{id}")//Aqui estamos usando a mesma lógica dos comandos acima, só que usando o delete
    fun deleteRequest(
        @Path("id") id:Int
    ): Call<PostEntity>

    //Observe como podemos fazer o uso dos Headers com RetroFit:
    @Headers(
        "Accept: application/json",
        "User-Agent : PostmanRuntime/7.29.0",
        "Accept : */*",
        "Accept-Encoding : gzip, deflate, br",
        "Connection : keep-alive"
    )
    @GET("posts")
    fun listWithReaders(): Call<List<PostEntity>>



}
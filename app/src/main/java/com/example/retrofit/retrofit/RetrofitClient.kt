package com.example.retrofit.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    //No caso do RetroFit, devemos criar um Singleton para ele, para garantir que existam mais de uma única instancia dessa biblioteca.

    companion object {

        private lateinit var INSTANCE: Retrofit//Parte 1 do Singleton, que armazena a instancia de RetroFit
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"//Atributo que armazena a URL base. Aqui a URL sempre deve terminar em barra (/), pois se não a biblioteca da um erro

        //Caso você tenha uma máquina local ou servidor local que está instalado na mesma máquina que está o Android Studio, a BASE_URL deve apontar para http://10.0.2.2/, que o Kotlin reconhece que estamos nos referindo ao localhost ou ao 127.0.0.1 da sua máquina local.
        //Não use o localhost ou 127.0.0.1 na BASE_URL pois no caso do Android Studio ele irá apontar para si mesmo, ok?

        private fun getRetrofitInstance(): Retrofit {//Método responsável por retornar uma instancia do tipo RetroFit

            val http = OkHttpClient.Builder()//A classe OkHttpClient ela não faz parte do RetroFit, mas é necessária pois é ela que se conecta com a internet.

            if (!::INSTANCE.isInitialized) {//Verifica que já foi instanciado o RetroFit
                INSTANCE = Retrofit.Builder()
                    .baseUrl(BASE_URL)//Método responsável por conter a URL base que iremos nos conectar
                    .client(http.build())//Método responsável por selecionar o cliente, que é a instancia que se comunica com a internet (OkHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())//Método responsável por definir quem vai converter o retorno da chamada em uma classe de Entity para que possamos usar posteriormente. Quem faz isso é o Gson.
                    .build()//Reune as informações definidas acima e cria a comunicação em si
            }

            return INSTANCE//Retorna o RetroFit
        }

        //O método abaixo irá retornar a instancia do RetroFit de modo a acessar os serviços que foram definidos pelos Services, aqui estamos usando o conceito de Genericts, pois podem haver mais de um Service na aplicação
        fun <S> createService(classService: Class<S>): S{
            return getRetrofitInstance().create(classService)
        }

    }

}
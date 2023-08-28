package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofit.retrofit.PostEntity
import com.example.retrofit.retrofit.PostService
import com.example.retrofit.retrofit.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Implementação do RetroFit (Assíncrono)

        //Usando o Método GET para retornar dados da requisição.

        val service =
            RetrofitClient.createService(PostService::class.java)//Retorna a Instancia de RetrofitClient que criamos em outro arquivo, retornando o serviço que iremos utilizar (PostService)

        val call: Call<List<PostEntity>> =
            service.list()//Aqui chamamos o método de listagem dentro da classe de Call que futuramente será responsável por chamar o método enqueue para executar a chamada em outro Thread (Background)

        call.enqueue(object :
            Callback<List<PostEntity>> {//Estamos chamando o método Enqueue que executa a requisição em outra Thread, onde precisamos passar uma classe herdada de CallBack

            //A classe herdada nos diz que precisamos implementar dois métodos, um para obter a resposta, e outro para tratar um erro caso haja.

            //É importante ressaltar que um desses métodos só serão chamados quando servidor responder a requisição, isso faz com que a experiência do nosso usuário não trave.

            override fun onResponse(
                call: Call<List<PostEntity>>,
                response: Response<List<PostEntity>>
            ) {//Método chamado sempre quando existe uma resposta

                if (response != null && response.isSuccessful()) {//A resposta pode não vir aquilo que estavamos esperando, por isso é bom checar se ela é diferente de nula, e fazer o uso do método isSuccessful() para verificar se a mesma aconteceu com sucesso

                    //Dentro da variável "response" temos diversos respostas HTTP da URL incluíndo o corpo (body)
                    Log.d("Resposta: Ok", "Ok")

                    val list = response.body()//Pega a resposta da API que esta salva em body()

                    if (list != null && list.size > 0) {//Por segurança, a resposta da API pode vir vazia, então é sempre bom fazer essas validações.

                        //Exemplos de como selecionar a resposta: Como a resposta se trata de uma LISTA de instâncias da classe PostEntity, podemos usar os métodos abaixo:

                        Log.d("Resposta ID", "${list[0].id}")//Seleciona o índice 0, retornando o id

                        //Podemos também fazer o uso do ForEach
                        list.forEach {
                            //Log.d("Resposta", "${it.title}")
                        }

                        //Podemos também usar o For
                        for (item in list) {
                            //Log.d("Resposta Body", "${item.body}")
                        }

                        //Com esses dados em mãos, podemos usa-los para passar para ums RecyclerView, banco de dados e afins.

                    }


                }

            }

            override fun onFailure(
                call: Call<List<PostEntity>>,
                t: Throwable
            ) {//Método chamado quando acontece um erro na requisição
                Log.d("Resposta: Fail", "$t")//Aqui estamos tratando o erro mostrando no LogCat
            }

        })

        //Usando o método POST para enviar os dados da requisição

        val callPost: Call<PostEntity> = service.postRequest("This is a Great Title", "Lorem Impsum dolor is awesome...", 765)//Aqui estamos recuperando a instancia do RetroFit e acessando o método POST, ao mesmo tempo que enviamos os parâmetros manualmente

        callPost.enqueue(object : Callback<PostEntity> {
            override fun onResponse(
                call: Call<PostEntity>,
                response: Response<PostEntity>
            ) {
                if (response != null && response.isSuccessful()) {
                    val list = response.body()
                    if (list != null) {
                        Log.d("Resposta POST", "${list.id}")
                    }
                }
            }

            override fun onFailure(call: Call<PostEntity>, t: Throwable) {
                //Método chamado quando a operação falha
                Log.d("Resposta POST FAIL", "$t")
            }
        })

        //Usando o método PUT para atualizar os dados da requisição

        val callPut: Call<PostEntity> = service.putRequest(1, "This is a Great Title", "Lorem Impsum dolor is awesome...", 765)

        callPut.enqueue(object : Callback<PostEntity> {
            override fun onResponse(
                call: Call<PostEntity>,
                response: Response<PostEntity>
            ) {
                if (response != null && response.isSuccessful()) {
                    val list = response.body()
                    if (list != null) {
                        Log.d("Resposta PUT", "${list.title}")//Aqui vemos que a atualização ocorreu com sucesso, pois o titulo que retorna ja é o novo.
                    }
                }
            }

            override fun onFailure(call: Call<PostEntity>, t: Throwable) {
                //Método chamado quando a operação falha
                Log.d("Resposta PUT FAIL", "$t")
            }
        })

        //Usando o método DELETE para remover os dados da requisição

        val callDelete: Call<PostEntity> = service.deleteRequest(1)

        callDelete.enqueue(object : Callback<PostEntity> {
            override fun onResponse(
                call: Call<PostEntity>,
                response: Response<PostEntity>
            ) {
                if (response != null && response.isSuccessful()) {
                    val list = response.body()
                    if (list != null) {
                        Log.d("Resposta DELETE", "${list.title}")//Apesar de ser executado o DELETE da API que estamos usando não tem qualquer tipo de retorno
                    }
                }
            }

            override fun onFailure(call: Call<PostEntity>, t: Throwable) {
                //Método chamado quando a operação falha
                Log.d("Resposta DELETE FAIL", "$t")
            }
        })

    }

}
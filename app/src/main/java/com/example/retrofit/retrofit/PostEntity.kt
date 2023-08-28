package com.example.retrofit.retrofit

import com.google.gson.annotations.SerializedName

class PostEntity {

    @SerializedName("userId")//Usamos a anotação do gson para mapear o parâmetro que virá pelo Json para dentro do atributo da classe abaixo.
    var userId: Int = 0//Isso significa que o atributo "userId" que existe no JSON da API, será salvo automaticamente dentro dessa variável

    @SerializedName("id")//Mesma lógica vista acima.
    var id: Int = 0

    @SerializedName("title")//Mesma lógica vista acima.
    var title: String = ""

    @SerializedName("body")//Mesma lógica vista acima.
    var body: String = ""

    //O ponto bom de usar o @SerializedName, é que podemos ter um parâmetro chamado "nomeUsuario", mas o nosso atributo estar com o nome de "nome_usuario".

}
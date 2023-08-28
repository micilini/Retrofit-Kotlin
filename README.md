![Retrofit](https://micilini.com/assets/img/android/retrofit-android-micilini.png)

# Aprenda a utilizar a bilioteca Retrofit com Kotlin (Android Studio)

Este repositório faz parte do conteúdo sobre [Retrofit](https://micilini.com/conteudos/android/retrofit) no portal da [Micilini.com](https://micilini.com), que contém algumas dicas de como utilizar o a biblioteca Retrofit para fazer requisições na web com Kotlin no Android Studio.

## Sobre o Projeto

Este sistema não é um aplicativo pronto, e não possui qualquer tipo de interação entre telas, visto que toda a lógica do banco de dados como a criação, seleção, inserção, atualização e remoção, está sendo declarada dentro do método ```onCreate``` presente na ```MainActivity```.

É importante ressaltar que as dicas aqui presentes estão utilizando o padrão de arquitetura de software chamado MVVM (Model View ViewModel).

Nos exemplos, você vai ver e conhecer diversas anotações como ```@POST```, ```@PUT```, ```@GET``` e ```@DELETE```, além é claro, de aprender a criar as camadas do Retrofit.

Todos os arquivos necessários foram comentados detalhadamente para o seu entendimento.

## Instalação 

Para usar este sistema é necessário que você já tenha instalado na sua máquina local o [Android Studio](https://developer.android.com/studio).

Com o ambiente já configurado, basta fazer o clone deste repositório para dentro do seu ambiente:

```git clone https://github.com/micilini/Retrofit-Kotlin.git```

Por fim, basta abrir a pasta do projeto com o Android Studio e esperar que ele faça a sincronização dos pacotes necessários.

## Por onde começar?

A ideia principal é que você entenda como funciona a comunicação com a biblioteca Retrofit por meio do Kotlin.

Nesse caso, comece pelo arquivo ```MainActivity.kt``` e vá seguindo os comandos ali presentes.

## Posso reutilizar os arquivos no meu projeto?

Sim (e deve), a ideia deste projeto é que ele também sirva como template para suas proprias aplicações.

Sendo assim, existem alguns passos para fazer esta implementação:

1) Você vai precisar criar um  ```package ``` cujo nome será  ```retrofit```.

2) Dentro de ```retrofit``` crie um arquivo que vai representar a sua classe do Retrofit (```RetrofitClient.kt```), e use a mesma lógica que você aprendeu no artigo da micilini.com.

3) Após isso, você vai precisar fazer as mesmas coisas para as camadas de Entidades e Service (```PostEntity.kt``` e ```PostService.kt```).

4) Não se esqueça que este projeto usa o padrão MVVM, de modo que você é convidado a utilizar um ViewModel para tratar todas as interações com o Model.


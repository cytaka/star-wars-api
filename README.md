# star-wars-api

Uma API em Java, banco MongoDB e Spring Framework que contem dados dos planetas da serie Star Wars

## Como a API funciona?

API deve inserir, atualizar, deletar e listar planetas. Além disso, deve consultar outra API externa verificando a quantidade de apariações em filmes de acordo com o nome criado e retornar em todas as requisições(exceto delete). A API terá os seguintes endpoints:

`POST/planetas`: inserir um planeta. 

**Body:**

<code>
{
  "nome": "Tatooine",
  "clima": "arid",
  "terreno": "desert"
}
</code>

Deve ser enviado o objeto que será inserido. O retorno deve ser o próprio objeto.

<code>
{
    "id": "6098619f8ac74d626db3db02",
    "nome": "Tatooine",
    "clima": "arid",
    "terreno": "desert",
    "apariacoes": 5
}
</code>

Deve retornar com body vazio com um dos códigos a seguir:

* 201: caso o planeta seja criado com sucesso.
* 400: caso o JSON seja inválido.
* 500: erro no servidor

`PUT/planetas/{id}`: atualiza um planeta.

**Body:**

<code>
{
  "nome": "Tatooine",
  "clima": "arid",
  "terreno": "desert"
}
</code>

Deve ser enviado o objeto que será modificado. O retorno deve ser o próprio objeto modificado.

<code>
{
    "id": "6098619f8ac74d626db3db02",
    "nome": "Tatooine",
    "clima": "arid",
    "terreno": "desert",
    "apariacoes": 5
}
</code>

A resposta deve conter os códigos a seguir:

* 200: em caso de sucesso.
* 400: caso o JSON seja inválido.
* 500: se o id não for encontrado

`GET/planetas`: retorna todas os planetas cadastrados.

Deve retornar uma lista de planetas.

<code>
[  
  {
      "id": "6097134690d0210d61c51d7c",
      "nome": "Alderaan",
      "clima": "temperate",
      "terreno": "grasslands, mountains",
      "apariacoes": 2
  },
  {
      "id": "6097134b90d0210d61c51d7d",
      "nome": "Yavin IV",
      "clima": "temperate, tropical",
      "terreno": "jungle, rainforests",
      "apariacoes": 1
  }
]
</code>

A resposta deve conter os códigos a seguir:

* 200: retorno da lista de planeta

`GET/planetas?nome={nome}`: retorna todas os planetas que tenham nome passado como parametro.

Deve retornar uma lista de planetas.

<code>
[{
    "id": "6097134690d0210d61c51d7c",
    "nome": "Alderaan",
    "clima": "temperate",
    "terreno": "grasslands, mountains",
    "apariacoes": 2
}]
</code>

A resposta deve conter os códigos a seguir:

* 200: caso exista planetas cadastradas

`GET/planetas?{id}`: retorna um planeta de acordo com id.

Deve retornar um planeta.

<code>
{
    "id": "6097134690d0210d61c51d7c",
    "nome": "Alderaan",
    "clima": "temperate",
    "terreno": "grasslands, mountains",
    "apariacoes": 2
}
</code>

A resposta deve conter os códigos a seguir:

* 200: caso exista planetas cadastrados
* 500: caso não exista planetas criados.

`DELETE/planetas/{id}`: remove um planeta.

Deve aceitar uma requisição com body vazio e retornar 204.

### Execução

Para rodar a API via .jar:

```
java -jar travels-api-2.0.1.jar
```
Por default, a API está disponível no endereço [http://localhost:8080/](http://localhost:8080/)

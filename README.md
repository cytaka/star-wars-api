# star-wars-api

Uma API em Java, banco MongoDB e Spring Framework que contem dados dos planetas da serie Star Wars

## Como a API funciona?

API deve inserir, atualizar, deletar e listar planetas. Além disso, deve consultar outra API externa verificando a quantidade de aparições em filmes de acordo com o nome criado e retornar em todas as requisições(exceto delete). A API terá os seguintes endpoints:

`POST/planetas`: inserir um planeta. 

**Body:**

```
{
  "nome": "Tatooine",
  "clima": "arid",
  "terreno": "desert"
}
```

Deve ser enviado o objeto que será inserido. O retorno deve ser o próprio objeto.

```
{
    "id": "6098619f8ac74d626db3db02",
    "nome": "Tatooine",
    "clima": "arid",
    "terreno": "desert",
    "aparicoes": 5
}
```

Deve retornar com body vazio com um dos códigos a seguir:

* 201: caso o planeta seja criado com sucesso.
* 400: caso o JSON seja inválido.
* 500: erro no servidor

`PUT/planetas/{id}`: atualiza um planeta.

**Body:**

```
{
  "nome": "Tatooine",
  "clima": "arid",
  "terreno": "desert"
}
```

Deve ser enviado o objeto que será modificado. O retorno deve ser o próprio objeto modificado.

```
{
    "id": "6098619f8ac74d626db3db02",
    "nome": "Tatooine",
    "clima": "arid",
    "terreno": "desert",
    "apariacoes": 5
}
```

A resposta deve conter os códigos a seguir:

* 200: em caso de sucesso.
* 400: caso o JSON seja inválido.
* 404: caso o registro não existe.

`GET/planetas`: retorna todas os planetas cadastrados.

Deve retornar uma lista de planetas.

```
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
```

A resposta deve conter os códigos a seguir:

* 200: retorno da lista de planeta

`GET/planetas?nome={nome}`: retorna todas os planetas que tenham nome passado como parametro.

Deve retornar uma lista de planetas.

```
[{
    "id": "6097134690d0210d61c51d7c",
    "nome": "Alderaan",
    "clima": "temperate",
    "terreno": "grasslands, mountains",
    "apariacoes": 2
}]
```

A resposta deve conter os códigos a seguir:

* 200: caso exista planetas cadastradas

`GET/planetas?{id}`: retorna um planeta de acordo com id.

Deve retornar um planeta.

```
{
    "id": "6097134690d0210d61c51d7c",
    "nome": "Alderaan",
    "clima": "temperate",
    "terreno": "grasslands, mountains",
    "apariacoes": 2
}
```

A resposta deve conter os códigos a seguir:

* 200: caso exista planetas cadastrados
* 404: caso o registro não existe.

`DELETE/planetas/{id}`: remove um planeta.

Deve aceitar uma requisição com body vazio e retornar 204.

A resposta deve conter os códigos a seguir:

* 200: caso remova planeta
* 404: caso o registro não existe.

### Execução

Para rodar a API via .jar:

```
java -jar star-wars-api-0.0.1.jar
```
Por default, a API está disponível no endereço [http://localhost:8080/](http://localhost:8080/)

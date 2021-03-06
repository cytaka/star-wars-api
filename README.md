# star-wars-api

Uma API em Java que utiliza banco MongoDB e Spring Boot Framework que contem dados dos planetas da serie Star Wars

## Como a API funciona?

API deve inserir, atualizar, deletar e listar planetas. Além disso, deve consultar outra API externa verificando a quantidade de aparições em filmes de acordo com o nome criado e retornar em todas as requisições(exceto delete). A API terá os seguintes endpoints:

### Inserir
`POST/planetas`: inserir um planeta. 

Deve ser enviado o objeto que será inserido. 

**Body:**

```
{
  "nome": "Tatooine",
  "clima": "arid",
  "terreno": "desert"
}
```
Deve retornar o próprio objeto com um dos códigos a seguir:

* 201: caso o planeta seja criado com sucesso.
* 400: caso o JSON seja inválido.
* 500: erro no servidor

**Response:**

```
{
    "id": "6098619f8ac74d626db3db02",
    "nome": "Tatooine",
    "clima": "arid",
    "terreno": "desert",
    "aparicoes": 5
}
```

### Alterar
`PUT/planetas/{id}`: atualiza um planeta de acordo com o id.

Deve ser enviado o objeto que será modificado. 

**Body:**

```
{
  "nome": "Tatooine",
  "clima": "arid",
  "terreno": "desert"
}
```
Deve retornar o próprio objeto com um dos códigos a seguir:

* 200: em caso de sucesso.
* 400: caso o JSON seja inválido.
* 404: caso o registro não existe.

**Response:**

```
{
    "id": "6098619f8ac74d626db3db02",
    "nome": "Tatooine",
    "clima": "arid",
    "terreno": "desert",
    "aparicoes": 5
}
```
### Listar

`GET/planetas`: retorna todas os planetas cadastrados.

Deve retornar uma lista de planetas com um dos códigos a seguir:

* 200: retorno da lista de planeta

**Response:**

```
[  
  {
      "id": "6097134690d0210d61c51d7c",
      "nome": "Alderaan",
      "clima": "temperate",
      "terreno": "grasslands, mountains",
      "aparicoes": 2
  },
  {
      "id": "6097134b90d0210d61c51d7d",
      "nome": "Yavin IV",
      "clima": "temperate, tropical",
      "terreno": "jungle, rainforests",
      "aparicoes": 1
  }
]
```

### Listar por nome

`GET/planetas?nome={nome}`: retorna todas os planetas que tenham nome passado como parametro.

Deve retornar uma lista de planetas filtrando pelo nome com um dos códigos a seguir:

* 200: caso exista planetas cadastradas

**Response:**

```
[{
    "id": "6097134690d0210d61c51d7c",
    "nome": "Alderaan",
    "clima": "temperate",
    "terreno": "grasslands, mountains",
    "aparicoes": 2
}]
```

### Pesquisar

`GET/planetas?{id}`: retorna um planeta de acordo com id.

Deve retornar um planeta com um dos códigos a seguir:

* 200: caso exista planetas cadastrados
* 404: caso o registro não existe.

**Response:**

```
{
    "id": "6097134690d0210d61c51d7c",
    "nome": "Alderaan",
    "clima": "temperate",
    "terreno": "grasslands, mountains",
    "aparicoes": 2
}
```

### Remover

`DELETE/planetas/{id}`: remove um planeta.

A resposta deve conter os códigos a seguir:

* 200: caso remova planeta
* 404: caso o registro não existe.

## Execução

Para rodar a API via .jar:

```
java -jar star-wars-api-0.0.1.jar
```
Por default, a API está disponível no endereço [http://localhost:8080/](http://localhost:8080/)

## Imagem Docker

Esta disponível uma imagem do docker.

Para executar basta baixar o arquivo [docker-compose.yml](https://github.com/cytaka/star-wars-api/blob/master/docker-compose.yml) e executar o comando no terminal.
Para subir um docker com o Mongo e outro com a API
```
docker-compose up -d
```
Para parar o docker
```
docker-compose down
```

## Collection Postman

Esta disponível collection do Postman para teste.

[StarWars-API.postman_collection.json](https://github.com/cytaka/star-wars-api/blob/master/StarWars-API.postman_collection.json)


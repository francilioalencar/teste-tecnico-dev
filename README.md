
# Teste tecnico dev

### Requisitos
No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. Imagine que você deve criar uma solução para dispositivos móveis para gerenciar e participar dessas sessões de votação.  

Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API REST: 

 
* Cadastrar uma nova pauta 
* Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default) 
* Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta) 
* Contabilizar os votos e dar o resultado da votação na pauta 

 

Para fins de exercício, a segurança das interfaces pode ser abstraída e qualquer chamada para as interfaces pode ser considerada como autorizada. A solução deve ser construída em java, usando Spring-boot, mas os frameworks e bibliotecas são de livre escolha (desde que não infrinja direitos de uso). 

 

É importante que as pautas e os votos sejam persistidos e que não sejam perdidos com o restart da aplicação. 

 

O foco dessa avaliação é a comunicação entre o backend e o aplicativo mobile. Essa comunicação é feita através de mensagens no formato JSON, onde essas mensagens serão interpretadas pelo cliente para montar as telas onde o usuário vai interagir com o sistema. A aplicação cliente não faz parte da avaliação, apenas os componentes do servidor. O formato padrão dessas mensagens será detalhado no anexo 1. 

 

# Tarefas bônus 

* ### Tarefa Bônus 1 - Integração com sistemas externos. 
Integrar com um sistema que verifique, a partir do CPF do associado, se ele pode votar 

GET https://user-info.herokuapp.com/users/{cpf} 

Caso o CPF seja inválido, a API retornará o HTTP Status 404 (Not found). Você pode usar geradores de CPF para gerar CPFs válidos 

Caso o CPF seja válido, a API retornará se o usuário pode (ABLE_TO_VOTE) ou não pode (UNABLE_TO_VOTE) executar a operação. Essa operação retorna resultados aleatórios, portanto um mesmo CPF pode funcionar em um teste e não funcionar no outro. 


* ### Tarefa Bônus 2 - Performance 

Imagine que sua aplicação possa ser usada em cenários que existam centenas de milhares de votos. Ela deve se comportar de maneira performática nesses cenários 

Testes de performance são uma boa maneira de garantir e observar como sua aplicação se comporta 

* ### Tarefa Bônus 3 - Versionamento da API 

Como você versionaria a API da sua aplicação? Que estratégia usar? 

## O que será analisado 
* Simplicidade no design da solução (evitar over engineering) 
* Organização do código 
* Arquitetura do projeto 
* Boas práticas de programação (manutenibilidade, legibilidade etc) 
* Possíveis bugs 
* Tratamento de erros e exceções 
* Explicação breve do porquê das escolhas tomadas durante o desenvolvimento da solução 
* Uso de testes automatizados e ferramentas de qualidade 
* Limpeza do código 
* Documentação do código e da API 
* Logs da aplicação 
* Mensagens e organização dos commits 



# 
# Documentação

### porta:80

## Docker/Docker compose

Acessar diretorio raiz do projeto, onde encontra-se o arquivo docker-compose.yml  e execute o comando via terminal 
``` bash
  docker-compose up --build (inicializa os containers gerando as builds)
  docker-compose up (inicializa os containers exibindo informações no console)
  docker-compose up -d (inicializa os containers liberando o console)
```

```

services:
    mysql: Servindo com base de dados mysql:8.0.36
    api-1 / api-2: Servindo com aplicação, persistindo dados em mysql
    nginx: Servicno com balanceamento de carga entre os servidores da aplicação api-1 e 

```

#### swagger
    http://endereco-da-aplicacao/swagger-ui/index.html#/

### Sequencia dos cadastros
 1. Cadastrar associado : POST
 2. Cadastrar pauta : POST
 3. Iniciar sessao : POST
 4. Registrar voto : POST

### EndPoints
```
  POST /votacao (Registra votacao)
  
  POST /sessao (Grava sessao para votacao)
  PATCH /sessao/{id} (Atualiza data de apuração da sessão de votação totalizando os votos)

  GET /pauta (Lista todas as pautas)
  POST /pauta (Cadastra pauta)
  GET /pauta/{id} (Lista pauta do ID informado)
  GET /pauta/votos/{id} (Lista a pauta e total de votos conforme ID)

  POST /associado (Cadastra novo associado)
  GET /associado/{id} (Exibe associado conforme ID)

```

| Tarefa Bônus   |  Explicação                           |
| :---------- | :--------- | 
| `1` | `O endpoint descrito na atividade não fuinciou, optei por não implementar para evitar erros na aplicação` | 
| `2` | `Não realizei os teste de perfomace, porém, configurei o nginx fazendo o balanceamento de carga entre 2 instancias, como é de conhecimento, pode-se incluir mais instancias  ` | 
| `3` | `Foi utilizado migration flyway, permitindo versionar o banco de dados semelhantemente ao codigo. Outro ponto seria uso da tecnica de versionamento semântica aditiva` | 

##
#### Explicação breve do porquê das escolhas tomadas durante o desenvolvimento da solução 
```

Uso de docker permite desacoplar a infra da aplicação, dessa forma, configura-se o ambiente de produção igualmente ao ambiente de desenvolvimento minimizando falhas no ambiente de produção.
Como um dos critérios é a pessistencia dos dados, utilizei o MYSQL juntamente migration flyway, permitindo o versionamento do banco de dados.
O spring é um requisito, para deixar o desenvolvimento mais rápido e o código menos verboso foi utilizado o lombok;
Uso do nginx  para deixar a aplicação escalável e apta ao ambiente em nuvem.

```

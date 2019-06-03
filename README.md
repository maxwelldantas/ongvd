# ONGvd

Projeto de Trabalho de Conclusão de Curso (TCC) realizado no Centro Universitário Una Uberlândia, baseado no desenvolvimento de um sistema web que permite Organizações não Governamentais (ONGs) se registrarem neste sistema, podendo cadastrar serviços para os voluntários e cadastrar pedidos de doação para doadores onde ambos poderão visualizar estes serviços e pedidos de doação, assim podendo entrar em contato com a ONG que cadastrou os serviços e pedidos de doação para poder prosseguir com a execução do serviço ou doação na ONG.

## Tecnologias

Tecnologias utilizadas no desenvolvimento:

	1. Java SE Development Kit 11 (Java JDK 11)
    2. Spring Boot 2.1.4
    3. Thymeleaf
    4. Bootstrap
    5. PostgreSQL
    6. H2
    7. Flyway
    8. Apache Maven

O projeto utiliza o banco de dados PostgreSQL para produção e o banco de dados H2 para testes.

## 1. Requisitos e Configurações

Para executar o projeto é necessária a instalação das seguintes tecnologias:

   - Java JDK 11 [Download](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
   - Apache Maven [Download](https://maven.apache.org/download.cgi)
   - PostgreSQL [Download](https://www.postgresql.org/download/)
      - PostgreSQL deve ser configurado de acordo com o arquivo contido a partir da pasta raiz `src/main/resources/application.properties`, deve-se fazer essa configuração antes de executar o projeto.
      
## 2. Executando o Projeto

Após instalação do banco de dados relacional (PostgreSQL) e feita sua configuração conforme passo anterior, dentro da pasta raiz do projeto, executar o seguinte comando:

```sh
$ mvn install
```

Após o comando anterior, para iniciar o projeto, dentro da pasta raiz do projeto, executar o seguinte comando:

```sh
$ mvn spring-boot:run
```

Ou pode executar os seguintes comandos, sem a necessidade de instalação do Apache Maven 3.6.0:

Para sistemas operacionais baseados em Unix, execute os seguintes comandos no Terminal:

```sh
$ ./mvnw clean install
$ ./mvnw spring-boot:run
```

Para o sistema operacional Windows, execute os seguintes comandos no Prompt de Comando:

```sh
$ ./mvnw.cmd clean install
$ ./mvnw.cmd spring-boot:run
```

Por fim o link para acessar o projeto em um navegador será `http://localhost:8080`

## Doações

Caso queria ajudar com alguma contribuição fincanceira para continuação do ONGvd, pode-se realizar

- bitcoin:33LBvec6dsvwGW4TwyRWcokDVgbwa1tSri
# ONGvd

ONGvd é um Projeto de Trabalho de Conclusão de Curso (TCC) realizado no Centro Universitário Una Uberlândia, baseado no desenvolvimento de um sistema web que permite Organizações não Governamentais (ONGs) se registrarem neste sistema, podendo cadastrar serviços para os voluntários, cadastrar pedidos de doação para doadores e eventos solidários onde as pessoas que acessarem poderão visualizar estes serviços voluntários, pedidos de doações e eventos solidários, assim podendo entrar em contato com a ONG que cadastrou os serviços voluntários, pedidos de doações e eventos solidários para poder prosseguir com a execução do serviço voluntário, doação e evento solidário na ONG.

## Tecnologias

Tecnologias utilizadas no desenvolvimento:

- Java SE Development Kit 8 (Java JDK 8)
- Spring Boot 2.1.4
- Thymeleaf
- Bootstrap
- PostgreSQL
- H2
- Flyway
- Apache Maven

O projeto utiliza o banco de dados PostgreSQL para produção e o banco de dados H2 para testes.

## 1. Requisitos e Configurações

Para executar o projeto é necessária a instalação das seguintes tecnologias:

   - Java JDK 8 [Download](https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)
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

A URL para acessar o projeto em um navegador será `http://localhost:8080`

## Doações

Caso queria ajudar com alguma contribuição fincanceira para continuação do sistema web ONGvd,
realizei sua doação de qualquer valor para a carteira de Bitcoin que segue logo abaixo:

- [Doação PagSeguro](https://pag.ae/7U-ExjKg6)

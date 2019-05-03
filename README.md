# ONGvd

Projeto de Trabalho de Conclusão de Curso (TCC) realizado no Centro Universitário Una Uberlândia, baseado no desenvolvimento de um sistema web que permite Organizações não Governamentais (ONGs) se registrarem neste sistema, podendo cadastrar serviços para os voluntários e cadastrar pedidos de doação para doadores onde ambos poderão visualizar estes serviços e pedidos de doação, assim podendo entrar em contato com a ONG que cadastrou os serviços e pedidos de doação para poder prosseguir com a execução do serviço ou doação na ONG.

## Tecnologias

Tecnologias utilizadas no desenvolvimento:

    1. Java SE Development Kit 8 (Java JDK 1.8)
    2. Apache Maven 3.6.0
    3. Spring Boot 2.1.4
    4. Thymeleaf
    5. Bootstrap
    6. PostgreSQL
    7. H2
    8. Flyway

O projeto utiliza o banco de dados PostgreSQL para produção e o banco de dados H2 para testes.

## 1. Requisitos e Configurações

Para executar o projeto é necessária a instalação das seguintes tecnologias:

   - Java JDK 1.8 [Download](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
   - Apache Maven 3.6.0 [Download](https://maven.apache.org/download.cgi)
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

Por fim o link para acessar o projeto em um navegador será `http://localhost:8080`
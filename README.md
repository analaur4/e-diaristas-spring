# Projeto E-Diaristas

Projeto desenvolvido durante a imersão Multi Stack da [TreinaWeb](http://treinaweb.com.br) utilizando Java e Spring Boot.

## Dependências do Projeto

- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Thymeleaf
- Bean Validation

## Dependências de Desenvolvimento

- Spring Boot DevTols
- Lombok

## Requisitos

- Java 11
- Maven 3.8

## Como testar esse projeto local

Clone esse repositório e entre na pasta do projeto.

```shell
git clone https://github.com/analaur4/e-diaristas-spring.git
cd e-diaristas-spring
```

Atualize as configurações de acesso ao banco de dados no arquivo [application.properties](src/main/resources/application.properties)
```properties
spring.datasource.url=jdbc:mysql://host:porta/banco_de_dados
spring.datasource.username=usuario
spring.datasource.password=senha
```

Execute o projeto através do Maven.

```shell
mvn spring-boot:run
```

Acesse a aplicação em [http://localhost:8080/admin/servicos](http://localhost:8080/admin/servicos)

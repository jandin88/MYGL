# MYGL - My Game List

MYGL (My Game List) é um aplicativo que permite aos usuários gerenciar suas listas de jogos, categorizando-os em diferentes status, além de fornecer recomendações de jogos com base no histórico e interesses do usuário.

## Tecnologias Utilizadas

O projeto utiliza as seguintes tecnologias:

- **Java 17**
- **Spring Boot** (Spring Web, Spring Security, Spring Data JPA)
- **PostgreSQL**
- **Swagger** (para documentação da API)
- **JPA (Java Persistence API)**

## Funcionalidades Principais

- **Gerenciamento de Jogos**: Os usuários podem adicionar jogos às suas listas e categorizá-los como "Jogando", "Vão Jogar" ou "Jogados".
- **Sistema de Recomendações**:
    - **Recomendação de Usuário**: Um usuário pode recomendar jogos para outros.
    - **Recomendação Automática**: O sistema sugere jogos com base no histórico e interesses do usuário.
- **Autenticação e Autorizacão**: Proteção de endpoints utilizando Spring Security.

[//]: # (- **Sistema de Avaliações e Reviews**: Usuários podem avaliar e escrever resenhas sobre os jogos.)

## Estrutura do Projeto

O projeto segue uma arquitetura em camadas:

```
mygl-backend/
├── src/main/java/com/mygl/
│   ├── config              # configurações
│   ├── controller/         # Controladores da API
│   ├── domain/             # Camada de serviços
│   │     ├── model/        # Entidades JPA
│   │     ├── repository/   # Repositórios JPA            
│   ├── service/            # Classes de Serviço
│   ├── dto/                # Objetos de Transferência de Dados (DTOs)
├── src/main/resources/
│   ├── application.properties  # Configuração do PostgreSQL e Spring
│   ├── schema.sql              # Script de criação do banco de dados
│   ├── data.sql                # Dados iniciais para testes
├── pom.xml   # Dependências do Maven
├── README.md  # Documentação do projeto
```



## Configuração e Execução

### 1. Configurar o Banco de Dados PostgreSQL

Crie um banco de dados no PostgreSQL e atualize o `application.properties` com suas credenciais:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/mygl_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```
## DIAGRAMA DE CLASSE(idéia)
``` mermaid
classDiagram
    class User {
        +int userId
        +String username
        +String email
        +String password
        +Date createdAt
        +register()
        +login()
        +updateProfile()
        +deleteAccount()
    }

    class Game {
        +int gameId
        +String title
        +String description
        +Date releaseDate
        +String developer
        +String genre
        +addGame()
        +updateGame()
        +deleteGame()
    }

    class UserGameList {
        +int userGameListId
        +int userId
        +int gameId
        +String status
        +int score
        +int progress
        +Date startDate
        +Date finishDate
        +addGameToList()
        +updateGameStatus()
        +removeGameFromList()
    }

    class Review {
        +int reviewId
        +int userId
        +int gameId
        +String reviewText
        +int rating
        +Date createdAt
        +addReview()
        +updateReview()
        +deleteReview()
    }

    class UserRecommendation {
        +int recommendationId
        +int fromUserId
        +int toUserId
        +int gameId
        +String message
        +Date createdAt
        +sendRecommendation()
        +deleteRecommendation()
    }

    class SystemRecommendation {
        +int recommendationId
        +int userId
        +int gameId
        +String reason
        +Date createdAt
        +generateRecommendation()
    }

    User "1" --* "many" UserGameList : has
    Game "1" --* "many" UserGameList : belongs to
    User "1" --* "many" Review : writes
    Game "1" --* "many" Review : has
    User "1" --* "many" UserRecommendation : sends
    User "1" --* "many" UserRecommendation : receives
    Game "1" --* "many" UserRecommendation : recommended
    User "1" --* "many" SystemRecommendation : receives
    Game "1" --* "many" SystemRecommendation : recommended
```



### 2. Executar o Projeto


A API estará disponível em `http://localhost:8080`.



## Documentação da API

A documentação da API pode ser acessada via Swagger:

```
http://localhost:8080/swagger-ui.html
```
`

## Contribuição

Contribuições são bem-vindas! Para contribuir:

1. Fork o repositório
2. Crie uma branch (`git checkout -b feature-nova-funcionalidade`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova funcionalidade'`)
4. Faça push para a branch (`git push origin feature-nova-funcionalidade`)
5. Abra um Pull Request



<br><br><br><br><br><br><br><br>
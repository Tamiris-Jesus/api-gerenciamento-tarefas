# Projeto de Gerenciamento de Tarefas

Este é um projeto simples de gerenciamento de tarefas que permite realizar operações como adicionar, listar, excluir e atualizar tarefas. O projeto foi desenvolvido com Java 17 e usa o banco de dados H2 para armazenamento dos dados.

## Tecnologias Utilizadas

- **Java**: versão 17
- **Spring Boot**: para desenvolvimento da aplicação web
- **H2 Database**: banco de dados em memória
- **Maven**: para gerenciamento de dependências e construção do projeto

## Pré-requisitos

Antes de começar, você precisará ter os seguintes itens instalados:

- [Java 17 ou superior](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/)
- (Opcional) IDE como [IntelliJ IDEA](https://www.jetbrains.com/idea/) ou [Eclipse](https://www.eclipse.org/)

## Configuração do Banco de Dados H2

O banco de dados H2 está configurado para ser executado em memória, o que significa que os dados serão perdidos toda vez que a aplicação for parada. A configuração do H2 está definida no arquivo `application.properties`.

### Testando a API com Swagger

Para facilitar o teste e a documentação da API, a aplicação está configurada para utilizar o **Swagger**. Com o Swagger, você pode visualizar e interagir com todos os endpoints da API diretamente através de uma interface web, sem a necessidade de ferramentas externas como o Postman.

Após iniciar a aplicação, você pode acessar a interface do Swagger no seguinte URL:

http://localhost:8080/swagger-ui.html

Na interface do Swagger, você encontrará todos os endpoints da API listados, podendo testar cada um deles diretamente, fornecendo os parâmetros necessários e visualizando as respostas.



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


# Smart Stock

O projeto Smart Stock, desenvolvido para automatizar o processo de recompra de itens em uma empresa de e-commerce. Atualmente, o processo de recompra é realizado de forma manual: quando a quantidade de um item no estoque cai abaixo do nível mínimo, um analista negocia diretamente com o setor de compras.

## Funcionalidades do sistema

- **Ler um arquivo CSV** gerado por um sistema legado que contém os dados de estoque;
- **Aplicar regras de negócio** para identificar os itens que precisam ser repostos;
- **Integrar com uma API do setor de compras**, utilizando um token de autenticação para garantir a segurança da comunicação;
- **Persistir os dados no MongoDB** para acompanhamento e auditoria.

## Pré-requisitos

Você precisa ter o CLI docker e docker compose (ou docker-compose) disponíveis no seu PATH. A versão mínima suportada do Docker Compose é a 2.2.0.

## Tecnologias Usadas

- Java 21
- Spring Boot
- Spring Data JPA
- OpenCSV
- Spring Cloud OpenFeign
- Docker e Docker Compose
- Banco de dados no SQL **MongoDB** (configurável via Docker Compose)
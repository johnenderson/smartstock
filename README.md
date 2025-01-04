# Smart Stock :blue_heart:

O projeto **Smart Stock**, desenvolvido para automatizar o processo de recompra de itens em uma empresa de e-commerce. Atualmente, o processo de recompra é realizado de forma manual: quando a quantidade de um item no estoque cai abaixo do nível mínimo, um analista negocia diretamente com o setor de compras.

## :clipboard: Funcionalidades do sistema

- **Ler um arquivo CSV** gerado por um sistema legado que contém os dados de estoque;
- **Aplicar regras de negócio** para identificar os itens que precisam ser repostos;
  - **Recompra automática:** Sempre que um item estiver abaixo da quantidade mínima em estoque, uma solicitação de recompra será gerada. 
  - **Margem de segurança:** A quantidade de recompra deverá ser igual à quantidade mínima, acrescida de **20% de margem de segurança** para evitar falta de estoque.
- **Integrar com uma API do setor de compras**, utilizando um token de autenticação para garantir a segurança da comunicação;
- **Persistir os dados no MongoDB** para acompanhamento e auditoria.

## :white_check_mark: Pré-requisitos

Você precisa ter o CLI docker e docker compose (ou docker-compose) disponíveis no seu `PATH`. A versão mínima suportada do Docker Compose é a 2.2.0.

## :technologist: Tecnologias Usadas

- **Java 21**
- Spring Boot
- Spring Data **Mongo DB**
- [OpenCSV](https://opencsv.sourceforge.net/)
- [Mockoon](https://mockoon.com/) (configurável via Docker Compose)
- Spring Cloud OpenFeign
- Docker e Docker Compose
- Banco de dados NoSQL **MongoDB** (configurável via Docker Compose)

## :checkered_flag: Como executar o projeto:

1. Clone este repositório:
```bash
git clone git@github.com:johnenderson/smartstock.git
cd smartstock
```

2. Inicie o container do **Mongo DB** e do **Mockoon**:
> O Container do Mongo DB e o container do Mockoon devem estar de pé para funcionamento correto do projeto.
```bash
cd docker/
docker-compose up
```

3. Execute o projeto:
```bash
cd ..
mvn spring-boot:run
```

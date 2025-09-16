# API FlaBijoux - E-commerce com Spring Boot

![Java](https://img.shields.io/badge/Java-17-blue) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.5-brightgreen) ![Maven](https://img.shields.io/badge/Maven-red) ![MySQL](https://img.shields.io/badge/MySQL-blue)

API RESTful desenvolvida em Java com Spring Boot para o sistema de gerenciamento da loja de bijuterias FlaBijoux.

## Status do Projeto
**Em desenvolvimento.**

## ✨ Funcionalidades Principais

* **Autenticação de Usuários:** Sistema de login com geração de Token JWT.
* **Gerenciamento de Clientes:** CRUD completo para clientes e busca por telefone.
* **Gerenciamento de Produtos:** CRUD completo para produtos, com busca por palavra-chave, paginação e filtro por categoria.
* **Gerenciamento de Categorias:** CRUD completo para as categorias dos produtos.
* **Gerenciamento de Variantes:** CRUD para as variantes de um produto (ex: cor, tamanho).
* **Gerenciamento de Pedidos:** Criação e alteração de pedidos, com busca por data e status.
* **Relatórios:** Endpoint para consulta de faturamento mensal por ano.

## 🛠️ Tecnologias Utilizadas

A análise do projeto (`pom.xml`) revelou o uso das seguintes tecnologias:

* **Linguagem:** Java 17
* **Framework Principal:** Spring Boot 3.5.5
* **Acesso a Dados:** Spring Data JPA / Hibernate
* **Segurança:** Spring Security para autenticação e autorização
* **Tokens:** JSON Web Token (JWT) com a biblioteca `jjwt`
* **Banco de Dados:** MySQL
* **Servidor Web:** Tomcat Embutido
* **Build e Dependências:** Apache Maven
* **Documentação da API:** SpringDoc OpenAPI (Swagger) para documentação interativa

## 🚀 Como Executar o Projeto

Siga os passos abaixo para configurar e executar o projeto em seu ambiente local.

### Pré-requisitos
* JDK 17 ou superior instalado
* Apache Maven instalado
* MySQL Server instalado e em execução
* Um cliente de API como [Postman](https://www.postman.com/) ou [Insomnia](https://insomnia.rest/)

### 1. Clone o Repositório
```bash
git clone <URL_DO_SEU_REPOSITORIO_GIT>
cd ecommerce
```
### 2. Configure o Banco de Dados
1.  Abra seu cliente MySQL e crie um novo banco de dados. O nome utilizado no projeto é `db_flabijoux`.
    ```sql
    CREATE DATABASE db_flabijoux;
    ```
2.  **(Opcional)** Se você possui um script de criação de tabelas (`schema.sql`), execute-o neste banco de dados. Caso contrário, o Hibernate pode gerar as tabelas automaticamente.
3.  Abra o arquivo `src/main/resources/application.properties` e **altere as credenciais do banco de dados** para as suas configurações locais.

    ```properties
    # Altere os valores de acordo com a sua configuração local
    spring.datasource.username=seu_usuario_mysql
    spring.datasource.password=sua_senha_mysql

    # Verifique se a URL e a porta estão corretas
    spring.datasource.url=jdbc:mysql://localhost:3306/db_flabijoux?useTimezone=true&serverTimezone=UTC
    ```

### 3. Execute a Aplicação
Utilize o Maven para compilar e iniciar a aplicação:
```bash
mvn spring-boot:run
```
A API estará disponível em `http://localhost:8080`.

## 📄 Documentação Interativa com Swagger UI

Graças à dependência `springdoc-openapi`, este projeto gera uma documentação interativa da API automaticamente. Com a aplicação rodando, acesse o seguinte endereço no seu navegador:

**[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

Lá você poderá ver todos os endpoints em detalhes, seus parâmetros, e até mesmo testá-los diretamente pelo navegador.

## 🔌 Resumo dos Endpoints da API

Abaixo está um resumo dos principais endpoints disponíveis.

> **Nota:** A maioria dos endpoints requer autenticação. Primeiro, obtenha um token através do endpoint `/login` e adicione-o ao cabeçalho `Authorization` das suas requisições no formato `Bearer <seu_token>`.

### Autenticação
| Funcionalidade | Método HTTP | URI | Requer Autenticação? |
| :--- | :--- | :--- |:--- |
| Cadastrar Usuário | `POST` | `/usuarios` | Não |
| Fazer Login | `POST` | `/login` | Não |

### Clientes
| Funcionalidade | Método HTTP | URI | Requer Autenticação? |
| :--- | :--- | :--- |:--- |
| Listar todos | `GET` | `/clientes` | Sim |
| Buscar por ID | `GET` | `/clientes/{id}` | Sim |
| Buscar por Telefone| `GET` | `/clientes/busca` | Sim |
| Criar novo cliente | `POST` | `/clientes` | Sim |
| Atualizar cliente | `PUT` | `/clientes/{id}` | Sim |

### Produtos
| Funcionalidade | Método HTTP | URI | Requer Autenticação? |
| :--- | :--- | :--- |:--- |
| Listar (com paginação)| `GET` | `/produtos` | Sim |
| Buscar por ID | `GET` | `/produtos/{id}` | Sim |
| Buscar por palavra-chave|`GET`| `/produtos/search` | Sim |
| Buscar por Categoria | `GET` | `/produtos/categoria/{id}` | Sim |
| Criar novo produto | `POST` | `/produtos` | Sim |
| Atualizar produto | `PUT` | `/produtos/{id}` | Sim |

### Pedidos
| Funcionalidade | Método HTTP | URI | Requer Autenticação? |
| :--- | :--- | :--- |:--- |
| Listar todos | `GET` | `/pedidos` | Sim |
| Buscar por ID | `GET` | `/pedidos/{id}` | Sim |
| Buscar por Status | `GET` | `/pedidos/status/{status}` | Sim |
| Buscar por Data | `GET` | `/pedidos/data` | Sim |
| Criar novo pedido | `POST` | `/pedidos` | Sim |
| Atualizar pedido | `PUT` | `/pedidos/{id}` | Sim |
| Ver Faturamento Anual | `GET` | `/pedidos/faturamento/{ano}`| Sim |

### Categorias
| Funcionalidade | Método HTTP | URI | Requer Autenticação? |
| :--- | :--- | :--- |:--- |
| Listar todas | `GET` | `/categorias` | Sim |
| Criar nova categoria | `POST` | `/categorias` | Sim |
| Atualizar categoria | `PUT` | `/categorias/{id}` | Sim |
| Deletar categoria | `DELETE` | `/categorias/{id}` | Sim |

### Variantes
| Funcionalidade | Método HTTP | URI | Requer Autenticação? |
| :--- | :--- | :--- |:--- |
| Buscar por ID | `GET` | `/variantes/{id}` | Sim |
| Buscar por Produto (`?idproduto=...`) | `GET` | `/variantes` | Sim |
| Criar nova variante | `POST` | `/variantes` | Sim |
| Atualizar variante | `PUT` | `/variantes/{id}` | Sim |


## 👨‍💻 Autor

Feito por **Vitor Lopes**.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/vhllopes)
[![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/vhllopes)

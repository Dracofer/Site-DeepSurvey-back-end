🛒 DeepSurvey Suplementos – Backend API

Backend oficial da aplicação DeepSurvey Suplementos, desenvolvido em Java + Spring Boot.
É responsável por gerenciar produtos, categorias, carrinho, checkout e envio do pedido para o WhatsApp, além de fornecer APIs integradas ao frontend React.

🚀 Tecnologias utilizadas

Java 17+

Spring Boot

Spring Web

Spring Data JPA

Spring Security + JWT

MySQL

Maven

Hibernate

🔥 Principais Funcionalidades

Autenticação via JWT

CRUD completo de categorias

CRUD de produtos com suporte a ofertas

Carrinho baseado em sessionId (sem login)

Checkout com cálculo de entrega

Envio automático do pedido para WhatsApp

Busca de produtos

Listagem por categoria

Produtos em oferta (onSale, salePrice)

⚙️ Configuração do Projeto
1️⃣ Criar banco de dados MySQL
CREATE DATABASE lojazap;

2️⃣ Configurar o arquivo src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/lojazap?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Porta da API
server.port=8083

# JWT
app.jwt.secret=VerySecretJwtKeyChangeMe
app.jwt.expirationMs=86400000


⚠️ Se seu MySQL estiver em outra porta (ex.: 3307), ajuste no URL.

3️⃣ Rodar o projeto
Via terminal:
mvn spring-boot:run

Ou via IDE:

IntelliJ IDEA → Run

Eclipse (STS) → Run as Spring Boot App

A API iniciará em:

👉 http://localhost:8083

📡 Endpoints da API
🔐 Autenticação
Método	Endpoint	Descrição
POST	/auth/register	Cria um novo usuário
POST	/auth/login	Autentica e retorna JWT
🗂️ Categorias
Método	Endpoint	Descrição
GET	/categories	Lista categorias
POST	/categories	Cria categoria
DELETE	/categories/{id}	Remove categoria
📦 Produtos
Método	Endpoint	Descrição
GET	/products	Lista todos os produtos
GET	/products/{id}	Detalhes por ID
GET	/products/search?q=	Busca por nome
GET	/products/category/{id}	Busca por categoria
GET	/products/offers	Lista produtos em oferta
POST	/products	Cria um produto
PUT	/products/{id}	Atualiza produto
DELETE	/products/{id}	Remove produto
🛒 Carrinho
Método	Endpoint	Descrição
GET	/cart/{sessionId}	Lista itens
POST	/cart/add	Adiciona item
POST	/cart/update	Atualiza quantidade
POST	/cart/remove	Remove item
🧾 Checkout
Método	Endpoint	Descrição
POST	/orders/checkout	Finaliza pedido e envia para WhatsApp
🔐 Segurança / JWT

O backend utiliza JWT para autenticação de endpoints administrativos.

Chave definida em:

app.jwt.secret=VerySecretJwtKeyChangeMe


⚠️ Altere ESSA chave ao colocar em produção.

📌 Observações finais

Este backend está totalmente integrado ao frontend da loja de suplementos DeepSurvey Suplementos.

O sistema permite expansão simples para dashboard admin, relatórios, controle de estoque e gerenciamento de pedidos.

Carrinho funciona mesmo sem login, baseado em sessionId.

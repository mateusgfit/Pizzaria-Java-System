Pizzaria Java System 🍕
Sistema de gestão de pedidos de alta performance, desenvolvido com arquitetura de camadas e foco em segurança de dados.

🚀 Sobre o Projeto
Este projeto demonstra a integração entre um backend robusto em Java e uma camada de segurança e API moderna em Node.js. O sistema foi desenhado para ser escalável, seguro contra ataques web e fácil de manter.

🏗️ Arquitetura
Core (Java): Responsável pela lógica de negócio, persistência em arquivos e regras de montagem de pizzas (Design Pattern: Builder).

API & Security Layer (Node.js): Atua como uma camada de proteção (Gateway), gerenciando acesso e segurança.

🛡️ Camadas de Segurança Implementadas
O sistema possui uma blindagem completa contra os vetores de ataque mais comuns:

Helmet.js: Proteção automática contra vulnerabilidades de cabeçalhos HTTP (XSS, Clickjacking).

Rate Limiting: Prevenção contra ataques de força bruta (Brute Force e DoS), limitando o número de requisições por IP.

SQL Injection Prevention: Uso de Prepared Statements (mysql2) para garantir que dados de entrada nunca sejam interpretados como código pelo banco de dados.

Autenticação JWT: Sistema de crachás digitais que garante que apenas usuários autenticados acessem rotas críticas (como relatórios administrativos).

📂 Estrutura de Pastas
Plaintext
/Pizzaria-Java-System
├── src/            (Código-fonte Java)
├── PizzariaAPI/    (API Node.js com segurança)
│   ├── server.js           (Rotas e configuração)
│   ├── dbService.js        (Camada de banco de dados segura)
│   ├── authService.js      (Gestão de tokens JWT)
│   ├── package.json        (Gerenciamento de dependências)
└── README.md
🛠️ Como Executar
Pré-requisitos
Java JDK instalado.

Node.js e NPM instalados.

MySQL configurado.

Backend Java
Compile e execute a classe Main.java no seu ambiente Java preferido (IntelliJ, Eclipse, etc).

API de Segurança (Node.js)
Navegue até a pasta da API:

Bash
cd PizzariaAPI
Instale as dependências:

Bash
npm install
Inicie o servidor:

Bash
node server.js
📝 Tecnologias Utilizadas
Java SE: Lógica de negócio e POO.

Node.js / Express: API Gateway.

Segurança: Helmet, Rate-Limit, JSON Web Tokens (JWT).

Persistência: MySQL (mysql2).

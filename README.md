API de Autenticação e Autorização JWT (AV2 - Spring Boot)

Este projeto foi desenvolvido como parte de um desafio acadêmico (AV2) com o objetivo de criar uma aplicação segura e atual utilizando Spring Boot. A API implementa autenticação e autorização com JSON Web Tokens (JWT), promovendo um ambiente stateless, com controle rigoroso de acesso e proteção de dados.

🚀 Destaques do Projeto

✅ Autenticação e Autorização Seguras:Utilização de JWT para validar acesso e proteger endpoints.

✅ Testes Automatizados:Cobertura com JUnit 5 e MockMvc garantindo a integridade do sistema.

✅ Documentação e Monitoramento:Swagger/OpenAPI para exploração dos endpoints e Spring Boot Actuator + Prometheus para monitoramento.

✅ Pronto para Deploy:Compatível com Docker, Render e Railway para implantação gratuita.

🛠️ Tecnologias Aplicadas

Spring Boot Starter Web – Estrutura para construção da API REST.

Spring Boot Starter Security – Segurança e filtros de autenticação.

Spring Boot Starter OAuth2 Resource Server – Validação de JWT.

Spring Boot Starter Data JPA – Persistência com JPA.

H2 Database – Banco em memória para testes.

Lombok – Redução de código repetitivo.

Springdoc OpenAPI – Geração dinâmica da documentação Swagger.

Spring Boot DevTools – Hot reload.

JUnit 5 e Mockito – Testes e simulações.

Auth0 Java JWT – Criação e validação dos tokens.

Spring Boot Actuator – Endpoints para saúde e métricas.

Prometheus – Coleta e armazenamento de métricas.

📂 Organização do Projeto

Model:User – Representação do usuário no banco.

DTOs:AuthenticationRequest, AuthenticationResponse, RegisterRequest – Modelos para login, resposta e cadastro.

Repository:UserRepository – Operações de banco e busca por username.

Service:JwtService, AuthService – Geração e validação de tokens, autenticação e cadastro.

Controller:AuthController, TestProtectedController – Endpoints públicos e protegidos.

Config:SecurityConfig, SwaggerConfig – Regras de segurança e configuração da documentação.

⚙️ Configuração

Configuração no application.yml com:

Porta

Chave secreta JWT

Expiração do token

Banco de dados

👉 Em produção, use variável de ambiente:

# Linux/macOS
export JWT_SECRET=suachavesecreta
# Windows
set JWT_SECRET=suachavesecreta

🔑 Funcionalidades

POST /auth/login → Autentica usuário e retorna JWT.

POST /auth/register → Cadastro de novos usuários.

POST /auth/validate → Verifica validade do token.

Endpoints protegidos com roles e @PreAuthorize.

🌐 Swagger e Monitoramento

Swagger: http://localhost:8080/swagger-ui/index.html#/

Actuator Health: https://av2-constru-o-api-autentica-o-autoriza-o.onrender.com/actuator/health

🧑‍💻 Testes

Login válido → Token gerado

Login inválido → 401 Unauthorized

Acesso sem token → Negado

Acesso com token → Permitido

Restrições por role → Aplicadas corretamente

📈 Testes de Carga

Com JMeter:

200 usuários, ramp-up 20s

10 requisições por usuário

Avaliação de tempo médio, throughput e erros

▶️ Como Executar

git clone https://github.com/seu-usuario/sua-api-jwt.git
cd sua-api-jwt
./mvnw spring-boot:run

ou via IDE.

👤 Usuários Padrão

admin / 123456 → ADMIN

user / password → USER

📌 Conclusão

Projeto acadêmico demonstrando como desenvolver uma API RESTful segura e completa com JWT, monitoramento e documentação. Uma base sólida para sistemas reais e microsserviços.

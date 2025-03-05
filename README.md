Validation Engine

Este projeto é um API de um protótipo validação de dados de usuário, desenvolvida em Spring Boot. Ele simula a validação de informações como CPF, telefone, e-mail, data de nascimento, entre outros, com base em regras pré-definidas, e devolve um score referente aos dados enviados.
📋 Pré-requisitos

Antes de rodar o projeto, certifique-se de que você tem as seguintes ferramentas instaladas:

    Java Development Kit (JDK) 21: O projeto foi desenvolvido usando Java 21.
    Maven: Gerenciador de dependências usado no projeto.
    Git: Para clonar o repositório.
    
    IDE Recomendada: IntelliJ IDEA, Eclipse ou VS Code.

🚀 Como rodar o projeto
1. Clone o repositório

Abra o terminal e execute o seguinte comando para clonar o repositório:


    git clone https://github.com/Shwonck/Validation-Engine.git
    
    cd Validation-Engine


2. Configure o projeto

Certifique-se de que o Maven está configurado corretamente. Se estiver usando uma IDE, ela deve detectar automaticamente as dependências do pom.xml.
3. Instale as dependências

Execute o seguinte comando para instalar as dependências do projeto:

    mvn clean install

4. Execute o projeto

Para rodar o projeto, execute o seguinte comando:

    mvn spring-boot:run


O servidor estará disponível em:

    http://localhost:8080

📚 Documentação da API com Swagger

O projeto utiliza o Swagger para documentar e testar a API. Após rodar o projeto, você pode acessar a interface do Swagger no seguinte endereço:

    http://localhost:8080/swagger-ui.html


Endpoints disponíveis:

    POST /validate: Valida os dados do usuário.

        Exemplo de requisição:

        {
          "cpf": "123.456.789-09",
          "fullName": "João Silva",
          "phone": "(11) 98765-4321",
          "email": "joao.silva@example.com",
          "birthDate": "01-01-2000",
          "address": "Rua ABC, 123",
          "motherName": "Maria Silva"
        }

        Exemplo de resposta:

        {
          "valid": true,
          "confidenceScore": 9.5
        }

🛠️ Estrutura do Projeto

O projeto está organizado da seguinte forma:
    
    src/
    ├── main/
    │   ├── java/
    │   │   ├── com.shwonck.validation_engine/
    │   │   │   ├── controller/          # Controllers da API
    │   │   │   ├── model/               # Modelos de dados
    │   │   │   ├── service/             # Lógica de negócio
    │   │   │   ├── utils/               # Utilitários e validadores
    │   │   │   └── ValidationEngineApplication.java # Classe principal
    │   ├── resources/
    │   │   ├── application.properties   # Configurações do projeto
    └── test/
        └── java/                        # Testes unitários e de integração

📦 Dependências do Projeto

O projeto utiliza as seguintes dependências principais:

    Spring Boot: Framework para desenvolvimento de aplicações Java.

    Spring Web: Para criar endpoints RESTful.

    Spring Validation: Para validação de dados.

    SpringDoc OpenAPI (Swagger): Para documentação e teste da API.

    Lombok: Para reduzir boilerplate code (getters, setters, etc.).

    Spring Boot Actuator: Para monitoramento e gerenciamento da aplicação.

    Spring Boot DevTools: Para facilitar o desenvolvimento.

Você pode ver todas as dependências no arquivo pom.xml.

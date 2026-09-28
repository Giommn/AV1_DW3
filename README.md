# AutoBots - Back-end de Gestão para Manutenção Veicular e Autopeças

O **AutoBots** é um sistema de gestão em arquitetura de microsserviços desenvolvido para atender lojas de manutenção veicular e venda de autopeças. Este repositório contém o microsserviço responsável pelo **CRUD completo de Cadastro de Clientes** e suas entidades agregadas (Documentos, Endereços e Telefones).

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: Java 17 (OpenJDK)
- **Framework**: Spring Boot 3.2.5
- **Persistência**: Spring Data JPA / Hibernate
- **Banco de Dados**: H2 Database (em memória para desenvolvimento)
- **Gerenciador de Dependências**: Apache Maven (com Maven Wrapper `./mvnw`)
- **Testes**: JUnit 5, Spring Boot Test & MockMvc

---

## 🏛️ Arquitetura e Princípios SOLID

O projeto segue a arquitetura em camadas do Spring Framework, separando claramente as responsabilidades:

```
com.autobots.autobots
├── AutobotsApplication.java                 # Classe Principal (Spring Boot Bootstrapper)
├── model                                    # Entidades JPA (Cliente, Documento, Endereco, Telefone)
├── model.updater                            # Camada de Regras de Negócio de Atualização e Seleção (SOLID)
│   ├── StringVerificadorNulo.java           # Validação SRP para strings
│   ├── EnderecoAtualizador.java             # Atualização parcial de Endereço
│   ├── DocumentoAtualizador.java            # Atualização parcial de Documentos
│   ├── TelefoneAtualizador.java             # Atualização parcial de Telefones
│   ├── ClienteAtualizador.java              # Atualizador central com orquestração
│   └── ClienteSelecionador.java             # Seleção de clientes em coleções por ID
├── repository                               # Repositórios Spring Data JPA (JpaRepository)
├── service                                  # Regras de Negócio e Orquestração
├── controller                               # Endpoints REST (HTTP Interface)
└── exception                                # Tratamento Global de Exceções (@ControllerAdvice)
```

### Aplicação das Boas Práticas SOLID:
- **Single Responsibility Principle (SRP)**: Cada atualizador e verificador possui uma única responsabilidade no sistema.
- **Open/Closed Principle (OCP)**: Estrutura modular permitindo inclusão de novos atualizadores de entidades sem alterar os existentes.
- **Dependency Inversion Principle (DIP)**: Injeção de dependência via Spring `@Component` / `@Autowired`.

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
- **Java 17** instalado na máquina.

### Executando Localmente

Clone o repositório e execute no terminal:

```bash
# Executar a aplicação
./mvnw spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

### Executando os Testes

Para rodar a suíte de testes unitários e de integração:

```bash
./mvnw test
```

---

## 🗄️ Acesso ao Banco de Dados (Console H2)

O banco de dados H2 roda em memória durante o desenvolvimento. Para acessar a interface web:

- **URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:autobotsdb`
- **User**: `sa`
- **Password**: *(deixe em branco)*

---

## 📡 Endpoints da API REST

### 1. Cliente (`/clientes`)

| Método | Endpoint | Descrição | Status Sucesso |
|---|---|---|---|
| **GET** | `/clientes` | Listar todos os clientes | `200 OK` |
| **GET** | `/clientes/{id}` | Buscar cliente por ID | `200 OK` |
| **POST** | `/clientes` | Cadastrar novo cliente | `210 Created` |
| **PUT** | `/clientes/{id}` | Atualizar dados do cliente | `200 OK` |
| **DELETE** | `/clientes/{id}` | Deletar cliente por ID | `204 No Content` |

#### Exemplo de Payload JSON (`POST /clientes`):

```json
{
  "nome": "Carlos Eduardo Silva",
  "nomeSocial": "Kadu",
  "dataNascimento": "1990-05-15",
  "endereco": {
    "estado": "SP",
    "cidade": "São Paulo",
    "bairro": "Centro",
    "rua": "Rua Augusta",
    "numero": "500",
    "codigoPostal": "01305-000",
    "informacoesAdicionais": "Apto 42"
  },
  "telefones": [
    {
      "ddd": "11",
      "numero": "987654321"
    }
  ],
  "documentos": [
    {
      "tipo": "CPF",
      "numero": "12345678900"
    }
  ]
}
```

---

### 2. Documento (`/documentos`)

| Método | Endpoint | Descrição |
|---|---|---|
| **GET** | `/documentos` | Listar todos os documentos |
| **GET** | `/documentos/{id}` | Buscar documento por ID |
| **POST** | `/documentos` | Cadastrar novo documento |
| **PUT** | `/documentos/{id}` | Atualizar documento por ID |
| **DELETE** | `/documentos/{id}` | Remover documento |

---

### 3. Endereço (`/enderecos`)

| Método | Endpoint | Descrição |
|---|---|---|
| **GET** | `/enderecos` | Listar todos os endereços |
| **GET** | `/enderecos/{id}` | Buscar endereço por ID |
| **POST** | `/enderecos` | Cadastrar novo endereço |
| **PUT** | `/enderecos/{id}` | Atualizar endereço por ID |
| **DELETE** | `/enderecos/{id}` | Remover endereço |

---

### 4. Telefone (`/telefones`)

| Método | Endpoint | Descrição |
|---|---|---|
| **GET** | `/telefones` | Listar todos os telefones |
| **GET** | `/telefones/{id}` | Buscar telefone por ID |
| **POST** | `/telefones` | Cadastrar novo telefone |
| **PUT** | `/telefones/{id}` | Atualizar telefone por ID |
| **DELETE** | `/telefones/{id}` | Remover telefone |

---

## 🧪 Estrutura de Exceções

As requisições que buscarem recursos inexistentes retornarão formato padronizado com status `404 Not Found`:

```json
{
  "timestamp": "2026-09-27T23:37:41.000+00:00",
  "message": "Cliente não encontrado com o id: 999",
  "details": "uri=/clientes/999"
}
```

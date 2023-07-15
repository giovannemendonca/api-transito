## API de Cadastro de Veículos com Spring Boot

Esta API é desenvolvida com o framework Spring Boot e permite o cadastro de veículos, gerenciamento de proprietários e registro de multas. A API utiliza o FlywayDB para a migração do banco de dados, garantindo uma estrutura consistente e escalável.

### Funcionalidades Principais

1. Cadastro de Veículos: Permite o registro de veículos com informações como marca, modelo, ano e placa.

2. Gerenciamento de Proprietários: Cada veículo é associado a um proprietário através do seu ID. É possível realizar operações como adicionar, atualizar e remover proprietários.

3. Registro de Multas: Os veículos podem receber multas, que são registradas no sistema com detalhes como valor, data e descrição.

### Pré-requisitos

- Java JDK 11 ou superior instalado.
- Maven para gerenciamento de dependências.
- Banco de dados PostgreSQL.

### Configuração do Banco de Dados

1. Crie um banco de dados PostgreSQL.
2. Atualize as informações de conexão com o banco de dados no arquivo `application.properties`.

### Executando a API

1. Clone o repositório: `git clone https://github.com/seu-usuario/api-veiculos-spring-boot.git`
2. Navegue até o diretório do projeto: `cd api-veiculos-spring-boot`
3. Execute o comando `mvn spring-boot:run` para iniciar a aplicação.
4. A API estará disponível em `http://localhost:8080`.

### Endpoints

A API expõe os seguintes endpoints:

- `GET /veiculos`: Retorna todos os veículos cadastrados.
- `GET /veiculos/{id}`: Retorna o veículo com o ID especificado.
- `POST /veiculos`: Cria um novo veículo.
- `PUT /veiculos/{id}`: Atualiza as informações de um veículo existente.
- `DELETE /veiculos/{id}`: Remove um veículo pelo seu ID.

- `GET /proprietarios`: Retorna todos os proprietários cadastrados.
- `GET /proprietarios/{id}`: Retorna o proprietário com o ID especificado.
- `POST /proprietarios`: Cria um novo proprietário.
- `PUT /proprietarios/{id}`: Atualiza as informações de um proprietário existente.
- `DELETE /proprietarios/{id}`: Remove um proprietário pelo seu ID.

- `GET /multas`: Retorna todas as multas registradas.
- `GET /multas/{id}`: Retorna a multa com o ID especificado.
- `POST /multas`: Registra uma nova multa para um veículo.
- `PUT /multas/{id}`: Atualiza as informações de uma multa existente.
- `DELETE /multas/{id}`: Remove uma multa pelo seu ID.


### Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
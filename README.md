# betAS
Projeto da Disciplina Programação Orientada a Objetos Turma 01 - 2025.1

Sistema de apostas desenvolvido em **Java + Spring Boot** com foco em transações(depósitos, saques e controle de clientes), contendo também uma lógica própria de um jogo, com funções relacionadas a aposta(odds e porcentagem de chance).

O projeto inclui camada de persistência em arquivo, padrão de projeto MVC + State, e testes unitários com JUnit 5.

## Índice
- [Visão Geral](#visão-geral)
- [Funcionalidades](#funcionalidades)
- [Motivações](#motivações)
- [Tecnologias](#tecnologias)

## Visão Geral
Este projeto simula um sistema de apostas com funcionalidades de cadastro de clientes, depósitos, saques e histórico de transações.
O backend foi implementado com Spring Boot e organiza a lógica em três camadas principais:
- **Models:** representam entidades como `Cliente`, `Deposito`, `Saque`.
- **Services:** tratam a lógica de negócio e persistência de transações.
- **Controllers:** expõem endpoints REST para interação com o sistema.

## Funcionalidades
- [x] Cadastro de clientes
- [x] Registro de depósitos e saques
- [x] Validação de CPF, email e senha
- [x] Histórico de transações em arquivo
- [x] Endpoints REST (Spring Controller)
- [x] Testes unitários com JUnit 5

## Motivações
- Neste projeto, que possui foco em transações e controle de clientes, a maioria das motivações foi voltada em torno disso, sendo elas:

- Uso do JUnit 5
-   Foi utilizado **JUnit 5** como framework de testes unitários, para criar testes claros, concisos e de fácil manutenção relacionados às classes de transação.
- Isso foi necessário para:
  - Garantir que cada uma das classes de transação (`Deposito` e `Saque`) funcionasse conforme esperado.
  - Facilitar a detecção precoce de erros nesses métodos tão importantes, evitando que falhas em getters/setters ou regras de negócio passassem despercebidas.

- Padrão de Projeto **State**
- Foi escolhido implementar o padrão **State** para representar as mudanças de estado do `Cliente`.
- Essa decisão foi motivada por:
  - Aumentar a flexibilidade no gerenciamento de estados (Normal e Premium).
  - Evitar condições complexas (vários `if/else` ou `switch`) espalhadas no código.
  - Permitir que novos estados sejam adicionados facilmente sem alterar o código existente.

- Persistência de Dados em Arquivo
- A decisão de persistir as informações em arquivos de texto foi motivada pela simplicidade e pela necessidade de manter um histórico confiável das transações.
- Essa escolha foi importante para:
  - Permitir registrar depósitos e saques de forma permanente, sem depender apenas da memória da aplicação.
  - Garantir que, mesmo após reiniciar o sistema, seja possível consultar o histórico de movimentações.
  - Simular, em uma situação hipotética, a forma como o site poderia monitorar perdas e ganhos, fornecendo uma visão clara da saúde financeira do sistema.
  - Ser uma alternativa leve ao uso de banco de dados, suficiente para os objetivos didáticos deste projeto.

## Tecnologias
- Java 17
- Spring Boot 3
- Maven 3.9
- JUnit 5
- VS Code



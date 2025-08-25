package com.bet.service;

import java.io.IOException; // Importa a classe IOException para tratar erros de entrada e saída
import java.math.BigDecimal; // Importa a classe BigDecimal para manipulação de números decimais, nesse caso o valor das transações
import java.nio.file.Files; // Importa as classes do pacote java.nio.file para trabalhar com arquivos e caminhos, nesse caso para tratar do histórico de transações
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.springframework.stereotype.Service; // Importa a anotação Service do Spring para definir a classe como uma classe do tipo serviço

import com.bet.models.Cliente; // Importa a classe Cliente para representar o objeto cliente e tratar diretamente do seu saldo

@Service
public class SaldoServiceImplement implements SaldoService{ // Definição da classe SaldoServiceImplement para implementação dos métodos definidos na interface SaldoService

    private final Path transacoesPath = Path.of("transacoes.txt"); // Define o caminho do arquivo onde o histórico de transações será salvo

    @Override // Anotação da interface para que o método abaixo seja uma implementação do método definido na interface
    public BigDecimal deposito(Cliente cliente, BigDecimal valor){ // Implementação do método de depósito de dinheiro numa conta

        BigDecimal saldoAtual = cliente.getSaldo(); // Chama o método getSaldo da classe Cliente para definir o saldo atual do cliente específico
        BigDecimal novoSaldo = saldoAtual.add(valor); // Através da biblioteca BigDecimal, manipula o valor do saldoAtual, adicionando(add) o valor que foi passado como argumento
        cliente.setSaldo(novoSaldo); // Chama o método setSaldo da classe Cliente para definir o novo saldo do cliente específico

        salvarTransacao(formarTransacao(cliente, "depósito", valor, 0)); // Salva a transação feita em arquivo, nesse caso o arquivo do histórico de transações
        cliente.adicionarTransacao(formarTransacao(cliente, "depósito", valor, 1)); // Salva a transação feita no histórico associado ao cliente específico

        return novoSaldo;
    }

    @Override
    public BigDecimal saque(Cliente cliente, BigDecimal valor){ // Implementação do método de saque de dinheiro numa conta

        BigDecimal saldoAtual = cliente.getSaldo(); // Chama o método getSaldo da classe Cliente para definir o saldo atual do cliente específico
        if(saldoAtual.compareTo(valor) < 0){ // Através da biblioteca BigDecimal, faz uma comparação para verificar se o valor a ser sacado realmente está disponível no saldo atual
            throw new IllegalArgumentException("Saldo insuficiente para saque");
        }
        
        BigDecimal novoSaldo = saldoAtual.subtract(valor); // Através da biblioteca BigDecimal, manipula o valor do saldoAtual, subtraindo(subtract) o valor que foi passado como argumento
        cliente.setSaldo(novoSaldo); // Chama o método setSaldo da classe Cliente para definir o novo saldo do cliente específico
        
        salvarTransacao(formarTransacao(cliente, "saque", valor, 0)); // Salva a transação feita em arquivo, nesse caso o arquivo do histórico de transações
        cliente.adicionarTransacao(formarTransacao(cliente, "saque", valor, 1)); // Salva a transação feita no histórico associado ao cliente específico

        return novoSaldo;
    }

    // HELPERS utilizados com o intuito de abstrair código para reuso

    private static String formarTransacao(Cliente c, String acao, BigDecimal valor, int flag){ // Método utilizado para padronizar transações para dois usos, alternando eles com o uso da flag
        String nome = c.getNome() + " " + c.getSobrenome(); // Chamando métodos da classe Cliente para definir o nome do cliente específico combinando nome e sobrenome
        if(flag == 0) // Verificação de flag para escrever no formato utilizado no histórico geral
            return "%s fez um %s de R$%s".formatted(nome, acao, valor);
        else return "Transação: %s\nId: %s\nR$%.2f".formatted(acao, c.getId(), valor); // Verificação de flag para escrever no formato utilizado no histórico pessoal/específico
    }

    private void salvarTransacao(String transacao){ // Método utilizado para persistir os dados das transações no arquivo
        try { // Bloco try usado porque operações de escrita em arquivo podem lançar exceções (IOException)
            Files.writeString(transacoesPath, transacao + System.lineSeparator(), // Utiliza método do pacote Files para escrever string em arquivo
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND); // Cria o arquivo se ele ainda não existir ou acrescenta a transação no final do arquivo sem sobrescrever
        } catch (IOException e) { // Bloco catch para caso aconteça algum erro de entrada/saída
            throw new RuntimeException("Erro ao salvar transação", e); // Relança a exceção como RuntimeException, com uma mensagem explicativa
        }
    }

}
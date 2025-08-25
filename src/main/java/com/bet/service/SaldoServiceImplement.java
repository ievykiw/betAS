package com.bet.service;

import java.io.IOException; // Importa a classe IOException para tratar erros de entrada e saída
import java.math.BigDecimal; // Importa a classe BigDecimal para manipulação de números decimais, nesse caso o valor das transações
import java.nio.file.Files; // Importa as classes do pacote java.nio.file para trabalhar com arquivos e caminhos, nesse caso para tratar do histórico de transações
import java.nio.file.Path; // Importa a anotação Service do Spring para definir a classe como uma classe do tipo serviço
import java.nio.file.StandardOpenOption; // Importa a classe Cliente para representar o objeto cliente do usuário do sistema

import org.springframework.stereotype.Service;

import com.bet.models.Cliente;

@Service
public class SaldoServiceImplement implements SaldoService{ // Definição da classe SaldoServiceImplement para implementação dos métodos definidos na interface SaldoService

    private final Path transacoesPath = Path.of("transacoes.txt"); // Define o caminho do arquivo onde o histórico de transações será salvo

    @Override // Anotação da interface para que o método abaixo seja uma implementação do método definido na interface
    public BigDecimal deposito(Cliente cliente, BigDecimal valor){ // Implementação do método de depósito de dinheiro numa conta

        BigDecimal saldoAtual = cliente.getSaldo(); // 
        BigDecimal novoSaldo = saldoAtual.add(valor);
        cliente.setSaldo(novoSaldo);

        salvarTransacao(formarTransacao(cliente, "depósito", valor, 0));
        cliente.adicionarTransacao(formarTransacao(cliente, "depósito", valor, 1));

        return novoSaldo;
    }

    @Override
    public BigDecimal saque(Cliente cliente, BigDecimal valor){ // Implementação do método de saque de dinheiro numa conta

        BigDecimal saldoAtual = cliente.getSaldo();
        if(saldoAtual.compareTo(valor) < 0){
            throw new IllegalArgumentException("Saldo insuficiente para saque");
        }
        
        BigDecimal novoSaldo = saldoAtual.subtract(valor);
        cliente.setSaldo(novoSaldo);
        
        salvarTransacao(formarTransacao(cliente, "saque", valor, 0));
        cliente.adicionarTransacao(formarTransacao(cliente, "saque", valor, 1));

        return novoSaldo;
    }



    private static String formarTransacao(Cliente c, String acao, BigDecimal valor, int flag) {
        String nome = c.getNome() + " " + c.getSobrenome();
        if(flag == 0)
            return "%s fez um %s de R$%s".formatted(nome, acao, valor);
        else return "Transação: %s\nId: %s\nR$%.2f".formatted(acao, c.getId(), valor);
    }

    private void salvarTransacao(String transacao) {
        try {
            Files.writeString(transacoesPath, transacao + System.lineSeparator(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar transação", e);
        }
    }

}
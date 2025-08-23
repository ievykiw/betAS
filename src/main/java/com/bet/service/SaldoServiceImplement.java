package com.bet.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.*;

import org.springframework.stereotype.Service;

import com.bet.models.Cliente;

@Service
public class SaldoServiceImplement implements SaldoService{

    private final Path transacoesPath = Path.of("transacoes.txt");

    @Override
    public BigDecimal deposito(Cliente cliente, BigDecimal valor){

        BigDecimal saldoAtual = cliente.getSaldo();
        BigDecimal novoSaldo = saldoAtual.add(valor);
        cliente.setSaldo(novoSaldo);

        salvarTransacao(formarTransacao(cliente, "depósito", valor, 0));

        return novoSaldo;
    }

    @Override
    public BigDecimal saque(Cliente cliente, BigDecimal valor){

        BigDecimal saldoAtual = cliente.getSaldo();
        if(saldoAtual.compareTo(valor) < 0){
            throw new IllegalArgumentException("Saldo insuficiente para saque");
        }

        salvarTransacao(formarTransacao(cliente, "saque", valor, 0));

        BigDecimal novoSaldo = saldoAtual.subtract(valor);
        cliente.setSaldo(novoSaldo);

        return novoSaldo;
    }



    private static String formarTransacao(Cliente c, String acao, BigDecimal valor, int flag) {
        String nome = c.getNome() + " " + c.getSobrenome();
        if(flag == 0)
            return "%s fez um %s de R$%s".formatted(nome, acao, valor);
        else return "Fez um %s de R$%s".formatted(acao, valor);
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

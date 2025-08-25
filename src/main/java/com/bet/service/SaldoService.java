package com.bet.service;

import java.math.BigDecimal;

import com.bet.models.Cliente;

// Definição da interface SaldoService para definir os métodos de execução relacionados ao saldo/transações
public interface SaldoService {
    BigDecimal deposito(Cliente cliente, BigDecimal valor); // Define o método de depósito de dinheiro numa conta
    BigDecimal saque(Cliente cliente, BigDecimal valor); // Define o método de saque de dinheiro numa conta

}

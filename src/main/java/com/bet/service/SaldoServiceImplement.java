package com.bet.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.bet.models.Cliente;

@Service
public class SaldoServiceImplement implements SaldoService{

    @Override
    public BigDecimal deposito(Cliente cliente, BigDecimal valor){

        BigDecimal saldoAtual = cliente.getSaldo();
        BigDecimal novoSaldo = saldoAtual.add(valor);
        cliente.setSaldo(novoSaldo);

        return novoSaldo;
    }

    @Override
    public BigDecimal saque(Cliente cliente, BigDecimal valor){

        BigDecimal saldoAtual = cliente.getSaldo();
        if(saldoAtual.compareTo(valor) < 0){
            throw new IllegalArgumentException("Saldo insuficiente para saque");
        }

        BigDecimal novoSaldo = saldoAtual.subtract(valor);
        cliente.setSaldo(novoSaldo);

        return novoSaldo;
    }

    



}

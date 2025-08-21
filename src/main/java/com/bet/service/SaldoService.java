package com.bet.service;

import java.math.BigDecimal;

import com.bet.models.Cliente;

public interface SaldoService {
    BigDecimal deposito(Cliente cliente, BigDecimal valor);
    BigDecimal saque(Cliente cliente, BigDecimal valor);

}

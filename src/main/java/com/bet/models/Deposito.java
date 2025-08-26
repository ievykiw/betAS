package com.bet.models;

import java.math.BigDecimal;

public class Deposito extends Transacao {

    public Deposito(){
        super();
    }

    public Deposito(BigDecimal valor, String cpf, String email, String senha) {
        super(valor, cpf, email, senha);
    }
}